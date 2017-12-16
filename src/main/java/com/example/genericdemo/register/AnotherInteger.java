package com.example.genericdemo.register;

import com.example.genericdemo.beans.GenericStruct;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class AnotherInteger extends GenericStruct<Integer> {
    public AnotherInteger() {
        super(100);
    }
}
