package com.serdmannwi.practiceprograms.alulasbakery.exceptions;

public class TestNotFoundException extends RuntimeException {
    public TestNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
