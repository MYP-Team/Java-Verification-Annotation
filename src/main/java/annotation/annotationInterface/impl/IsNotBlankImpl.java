package annotation.annotationInterface.impl;

import annotation.annotationInterface.AbstractVerify;
import annotation.annotationInterface.IVerify;
import annotation.annotationInterface.NotBlank;
import excaption.BusinessException;
import excaption.SystemException;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.lang.reflect.Field;

@Builder @AllArgsConstructor
public class IsNotBlankImpl extends AbstractVerify implements IVerify {
    @Override
    public <T> void verify(Field field, T t) {
        try {
            field.setAccessible(true);
            NotBlank notNull = field.getAnnotation(NotBlank.class);
            Object value = field.get(t);
            if(!nullStr.equals(notNull.conditions())&&!conditionsVerify(notNull.conditions(),field,t)){
                return;
            }
            if (null==value) {
                throw new BusinessException(notNull.code(),notNull.message());
            }
        }catch (BusinessException e){
            throw new BusinessException(e.getCode(),e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "非空校验系统异常");
        }
    }

}
