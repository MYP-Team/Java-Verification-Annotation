package annotation.annotationInterface.impl;

import annotation.annotationInterface.AbstractVerify;
import annotation.annotationInterface.IVerify;
import entrys.Person;

import java.lang.reflect.Field;

public class DiyDemo extends AbstractVerify implements IVerify {
    @Override
    public <T> void verify(Field field, T t) {
        System.out.println("123456789");
    }

}
