package com.example.service;

import org.junit.jupiter.api.Test;

class GrayCodeSupplierTest {

    @Test
    void generate() {
        new GrayCodeSupplier(5, 10).get().forEach(t -> t.getElements().forEach(System.out::println));
    }

}