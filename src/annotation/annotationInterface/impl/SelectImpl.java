package annotation.annotationInterface.impl;

import annotation.annotationInterface.AbstractVerify;
import annotation.annotationInterface.IVerify;
import annotation.annotationInterface.Select;
import excaption.BusinessException;
import excaption.SystemException;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.lang.reflect.Field;
import java.util.Arrays;


@Builder @AllArgsConstructor
public class SelectImpl extends AbstractVerify implements IVerify {
    @Override
    public <T> void verify(Field field, T t) {
        try {
            field.setAccessible(true);
            String valueStr=String.valueOf(field.get(t));
            Select select = field.getAnnotation(Select.class);
            if(!nullStr.equals(select.conditions())&&!conditionsVerify(select.conditions(),field,t)){
                return;
            }
            String[] valueArray=select.value();
            if(Arrays.stream(valueArray).parallel().noneMatch(valueStr::equals)){
                throw new BusinessException(select.code(),select.message());
            }
        }catch (BusinessException e){
            throw new BusinessException(e.getCode(),e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "选择校验系统异常");
        }
    }

}
