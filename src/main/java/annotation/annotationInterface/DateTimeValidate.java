package annotation.annotationInterface;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface DateTimeValidate {
    String pattern();
    String code();
    String message() default "日期格式错误";
    String conditions() default "";
}
