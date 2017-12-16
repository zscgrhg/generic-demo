package com.example.genericdemo.beans;

public class GenericStruct<T> {
   private final T t;

    public GenericStruct(T t) {
        this.t = t;
    }
}
