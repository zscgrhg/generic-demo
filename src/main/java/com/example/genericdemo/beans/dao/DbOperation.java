package com.example.genericdemo.beans.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class DbOperation<T> {
    private final List<BiFunction<? extends Map<String,Object>,?,?>> functions=new ArrayList<>();

    public DbOperation() {

    }

    public void add(BiFunction f){
        functions.add(f);
    }
}
