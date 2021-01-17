package annotation.annotationInterface.factory;


import annotation.annotationInterface.utils.AnnotationUtils;
import excaption.BusinessException;
import excaption.SystemException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class CheckAnnotationFactory extends AnnotationUtils {

    public  static  <T> void verify(T t){
        try {
            verifyClass(t);
            Field[] fields = t.getClass().getDeclaredFields();
            Arrays.stream(fields).parallel().forEach(field-> verifyField(field,t));
        }catch (BusinessException e){
            throw new BusinessException(e.getCode(),e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "参数校验系统异常");
        }
    }

}

