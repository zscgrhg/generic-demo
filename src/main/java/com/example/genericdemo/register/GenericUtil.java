package com.example.genericdemo.register;

import com.example.genericdemo.beans.GenericStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenericUtil {
    @Autowired
    DefaultListableBeanFactory factory;

    public <T> List<GenericStruct<T>> getBeans(Class<T> t) throws NoSuchMethodException {
        String[] names = factory.getBeanNamesForType(GenericStruct.class);
        ResolvableType target = ResolvableType.forClassWithGenerics(GenericStruct.class, t);

        List<GenericStruct<T>> list = new ArrayList<>();
        for (String name : names) {
            Object candidate = factory.getBean(name);
            if (target.isInstance(candidate)) {
                list.add((GenericStruct<T>) candidate);
                continue;
            }
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            String factoryBeanName = beanDefinition.getFactoryBeanName();
            String factoryMethodName = beanDefinition.getFactoryMethodName();
            Class factoryBeanClass = factory.getBean(factoryBeanName).getClass();
            Class factoryClass = ClassUtils.isCglibProxyClass(factoryBeanClass) ? factoryBeanClass.getSuperclass() : factoryBeanClass;
            Method method = factoryClass.getDeclaredMethod(factoryMethodName);
            ResolvableType resolvableType = ResolvableType.forMethodReturnType(method);
            if (target.isAssignableFrom(resolvableType)) {
                GenericStruct<T> bean = factory.getBean(name, GenericStruct.class);
                list.add(bean);
            }
        }
        return list;
    }
}
