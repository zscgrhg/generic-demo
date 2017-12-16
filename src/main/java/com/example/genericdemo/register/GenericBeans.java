package com.example.genericdemo.register;

import com.example.genericdemo.beans.GenericStruct;
import com.example.genericdemo.beans.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class GenericBeans {
    @Bean
    @Order(3)
    public GenericStruct<Integer> integerGenericStruct1() {
        return new GenericStruct(3);
    }

    @Bean
    @Order(1)
    public GenericStruct<Integer> integerGenericStruct0() {
        return new GenericStruct(1);
    }

    @Bean
    public GenericStruct<String> stringGenericStruct() {
        return new GenericStruct("abc");
    }
    @Bean
    public GenericStruct<User> userGenericStruct() {
        return new GenericStruct(new User("obama", 33));
    }
}
