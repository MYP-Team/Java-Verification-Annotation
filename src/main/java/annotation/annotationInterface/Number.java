package annotation.annotationInterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Number {
    int min() default 0;
    int max() default 0;
    String message() default "";
    String code() default "";
    String conditions() default "";
}
