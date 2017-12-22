package com.example.genericdemo.beans.dao;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class BinaryDboBuilder<R> extends DboBuilder {
    private final DbOperation<R> dbOperation;

    public BinaryDboBuilder(DbOperation<R> dbOperation) {
        this.dbOperation = dbOperation;
    }

    public <T> BinaryDboBuilder<T> withDb(BiFunction<Map<String, Object>, R, T> function) {
        dbOperation.add(function);
        return (BinaryDboBuilder<T>) new BinaryDboBuilder(dbOperation);
    }


    public DboBuilder withDb(BiConsumer<Map<String, Object>, R> function) {
        BiFunction<Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, R t) -> {
            function.accept(context, t);
            return null;
        };
        dbOperation.add(biFunction);
        return new DboBuilder(dbOperation);
    }
}
