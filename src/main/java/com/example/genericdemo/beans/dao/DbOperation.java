package com.example.genericdemo.beans.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class DbOperation<T> {
    private final List<BiFunction<Map<String,Object>,?,?>> functions=new ArrayList<>();

    public DbOperation() {

    }

    public void add(BiFunction<Map<String,Object>,?,?> f){
        functions.add(f);
    }
}
