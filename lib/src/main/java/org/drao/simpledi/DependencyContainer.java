package org.drao.simpledi;

import org.drao.simpledi.exceptions.DependencyException;

import java.util.Map;

public final class DependencyContainer {
    private final Map<Class, Object> singletonReferences;

    public DependencyContainer(Map<Class, Object> singletonReferences) {
        this.singletonReferences = singletonReferences;
    }

    public <T> T getService(Class<T> tClass) {
        final var ref = singletonReferences.get(tClass);
        if (ref == null) {
            throw new DependencyException("The class " + tClass.getName() + " was not found in the DI container, make sure to register it!");
        }
        if(!tClass.isInstance(ref)) {
            throw new DependencyException("The stored reference in the DI container for the type " + tClass.getName() + " was not of requested type, this is a bug, please report it!");
        }
        //noinspection unchecked
        return (T) ref;
    }
}
