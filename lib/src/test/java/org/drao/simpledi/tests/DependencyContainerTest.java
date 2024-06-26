package org.drao.simpledi.tests;

import org.drao.simpledi.DependencyContainer;
import org.drao.simpledi.exceptions.DependencyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DependencyContainerTest {
    private DependencyContainer container;
    private Map<Class, Object> singletonDependencies;

    @BeforeEach
    void beforeEach() {
        singletonDependencies = new HashMap<>();
        singletonDependencies.put(TestService01.class, new TestService01());
        container = new DependencyContainer(singletonDependencies);
    }

    @Test
    void shouldFetchTheSingletonReferenceIfTheProvidedMapHasItStored() {
        final var ref1 = container.getService(TestService01.class);
        final var ref2 = container.getService(TestService01.class);

        assertThat(ref1 == ref2).isTrue();
    }

    @Test
    void shouldThrowDependencyExceptionIfTheProvidedTypeIsNotRegistered() {
        assertThatThrownBy(() -> container.getService(TestService02.class))
                .isInstanceOf(DependencyException.class)
                .hasMessageContaining(TestService02.class.getName());
    }

    @Test
    void shouldThrowDependencyExceptionIfTheProvidedTypeIsNotOfTheRequestedType() {
        singletonDependencies.put(TestService02.class, new TestService01());
        assertThatThrownBy(() -> container.getService(TestService02.class))
                .isInstanceOf(DependencyException.class)
                .hasMessageContaining(TestService02.class.getName())
                .hasMessageContaining("this is a bug, please report it!");
    }
}
