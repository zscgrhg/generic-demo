package com.example.genericdemo.beans.dao;

import java.util.Map;
import java.util.function.*;

public class BinaryDboBuilder<R> {
    private final DbOperation<R> dbOperation;

    public BinaryDboBuilder(DbOperation<R> dbOperation) {
        this.dbOperation = dbOperation;
    }

    public <T> BinaryDboBuilder<T> withDb(BiFunction<Map<String, Object>, R, T> function) {
        dbOperation.add(function);
        return (BinaryDboBuilder<T>) new BinaryDboBuilder(dbOperation);
    }

    public <T> BinaryDboBuilder<T> withDb(Function<Map<String, Object>, T> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, Object t) -> function.apply(context);
        dbOperation.add(biFunction);
        return (BinaryDboBuilder<T>) new BinaryDboBuilder(dbOperation);
    }

    public <T> BinaryDboBuilder<T> withDb(Supplier<T> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, Object t) -> function.get();
        dbOperation.add(biFunction);
        return (BinaryDboBuilder<T>) new BinaryDboBuilder(dbOperation);
    }

    public DboBuilder withDb(BiConsumer<Map<String, Object>, R> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, R t) -> {
            function.accept(context, t);
            return null;
        };
        dbOperation.add(biFunction);
        return new DboBuilder(dbOperation);
    }

    public DboBuilder withDb(Consumer<Map<String, Object>> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, Object t) -> {
            function.accept(context);
            return null;
        };
        dbOperation.add(biFunction);
        return new DboBuilder(dbOperation);
    }
}
