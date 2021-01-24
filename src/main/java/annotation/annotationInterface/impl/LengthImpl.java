package annotation.annotationInterface.impl;

import annotation.annotationInterface.AbstractVerify;
import annotation.annotationInterface.IVerify;
import annotation.annotationInterface.Length;
import excaption.BusinessException;
import excaption.SystemException;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.lang.reflect.Field;
@Builder @AllArgsConstructor
public class LengthImpl extends AbstractVerify implements IVerify {
    @Override
    public <T> void verify(Field field, T t) {
        field.setAccessible(true);
        try {
            String value = String.valueOf(field.get(t));
            Length length = field.getAnnotation(Length.class);
            if(!nullStr.equals(length.conditions())&&!conditionsVerify(length.conditions(), field, t)){
                return;
            }
            int min = length.min();
            int max = length.max();
            if(value.length()<min||value.length()>max){
                throw new BusinessException(length.code(),length.message());
            }
        }catch (BusinessException e){
            throw new BusinessException(e.getCode(),e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "长度校验系统异常");
        }
    }

}
