package com.cristian.mylab;


@FunctionalInterface
interface PersonFactoryOneParameterInterface<P extends Person> {
    P create(String firstName);
}
