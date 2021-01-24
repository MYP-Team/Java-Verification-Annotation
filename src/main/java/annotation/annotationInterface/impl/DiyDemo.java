package annotation.annotationInterface.impl;

import annotation.annotationInterface.AbstractClassVerify;
import annotation.annotationInterface.IVerify;

import java.lang.reflect.Field;

public class DiyDemo extends AbstractClassVerify implements IVerify {
    @Override
    public <T> void verify(Field field, T t) {
        System.out.println("自定义方法校验");
    }

    @Override
    public <T> void verify(T t) {
        System.out.println("自定义类校验");
    }
}
