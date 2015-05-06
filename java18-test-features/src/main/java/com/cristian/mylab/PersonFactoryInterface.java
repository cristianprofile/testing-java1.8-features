package com.cristian.mylab;


@FunctionalInterface
interface PersonFactoryInterface<P extends Person> {
    P create(String firstName, String lastName);
}
