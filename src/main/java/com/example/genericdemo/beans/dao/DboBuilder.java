package com.example.genericdemo.beans.dao;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DboBuilder {

    private final DbOperation<Void> dbOperation;

    public DboBuilder() {
        this(new DbOperation<>());
    }

    public DboBuilder(DbOperation dbOperation) {
        this.dbOperation = dbOperation;
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


    public DboBuilder withDb(Consumer<Map<String, Object>> function) {
        BiFunction<? extends Map<String, Object>, ?, ?> biFunction = (Map<String, Object> context, Object t) -> {
            function.accept(context);
            return null;
        };
        dbOperation.add(biFunction);
        return new DboBuilder(dbOperation);
    }


}
