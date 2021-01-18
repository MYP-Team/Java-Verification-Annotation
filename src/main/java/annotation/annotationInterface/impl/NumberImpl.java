package annotation.annotationInterface.impl;

import annotation.annotationInterface.AbstractVerify;
import annotation.annotationInterface.IVerify;
import excaption.BusinessException;
import excaption.SystemException;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.lang.reflect.Field;

@Builder @AllArgsConstructor
public class NumberImpl extends AbstractVerify implements IVerify {
    @Override
    public <T> void verify(Field field, T t) {
        field.setAccessible(true);
        try {
            long intValue = GetValueInField(field, t);
            annotation.annotationInterface.Number number = field.getAnnotation(annotation.annotationInterface.Number.class);
            if (!nullStr.equals(number.conditions()) && !conditionsVerify(number.conditions(), field, t)) {
                return;
            }
            int min = number.min();
            int max = number.max();
            if (intValue < min || intValue > max) {
                throw new BusinessException(number.code(), number.message());
            }
        } catch (BusinessException e) {
            throw new BusinessException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "数字校验系统异常");
        }
    }

    private <T> long GetValueInField(Field field, T t) throws Exception {
        String value = String.valueOf(field.get(t));
        return Long.parseLong(value);
    }

}
