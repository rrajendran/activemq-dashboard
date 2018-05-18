package com.capella.mvc.example.exceptions;

import java.util.concurrent.Callable;

public class Exceptions {
    public Exceptions() {
    }

    public static <T> T uncheck(Callable<T> function) {
        try {
            return function.call();
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <T> T ignore(Callable<T> function) {
        try {
            return function.call();
        } catch (Exception var2) {
            return null;
        }
    }
}