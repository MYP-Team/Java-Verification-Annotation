package annotation.annotationInterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Select {
    String[] value() default {};
    String code();
    String message();
    String conditions() default "";
}
