package com.example.genericdemo.beans.dao;

import java.util.Map;
import java.util.function.*;

public class BiFunctionDaoBuilder<R> {
    private final DbOperation<R> dbOperation;

    public BiFunctionDaoBuilder(DbOperation<R> dbOperation) {
        this.dbOperation = dbOperation;
    }

    public <T> BiFunctionDaoBuilder<T> withDb(BiFunction<Map<String, Object>, R, T> function) {
        dbOperation.add(function);
        return (BiFunctionDaoBuilder<T>) new BiFunctionDaoBuilder(dbOperation);
    }

    public <T> BiFunctionDaoBuilder<T> withDb(Function<Map<String, Object>, T> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, Object t) -> function.apply(context);
        dbOperation.add(biFunction);
        return (BiFunctionDaoBuilder<T>) new BiFunctionDaoBuilder(dbOperation);
    }

    public <T> BiFunctionDaoBuilder<T> withDb(Supplier<T> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, Object t) -> function.get();
        dbOperation.add(biFunction);
        return (BiFunctionDaoBuilder<T>) new BiFunctionDaoBuilder(dbOperation);
    }

    public FunctionDaoBuilder withDb(BiConsumer<Map<String, Object>, R> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, R t) -> {
            function.accept(context, t);
            return null;
        };
        dbOperation.add(biFunction);
        return new FunctionDaoBuilder(dbOperation);
    }

    public FunctionDaoBuilder withDb(Consumer<Map<String, Object>> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, Object t) -> {
            function.accept(context);
            return null;
        };
        dbOperation.add(biFunction);
        return new FunctionDaoBuilder(dbOperation);
    }
}
