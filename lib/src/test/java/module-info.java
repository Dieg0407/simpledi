module simpledi.lib.test {
    requires org.drao.simpledi;
    requires org.junit.jupiter.api;
    requires org.assertj.core;

    exports org.drao.simpledi.tests;
    opens org.drao.simpledi.tests to org.junit.platform.commons;
}