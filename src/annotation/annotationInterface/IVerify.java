package annotation.annotationInterface;

import java.lang.reflect.Field;

public interface IVerify {
    <T> void verify(Field field, T t);
}
