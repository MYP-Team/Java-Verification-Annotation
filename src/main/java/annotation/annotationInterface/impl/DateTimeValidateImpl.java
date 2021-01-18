package annotation.annotationInterface.impl;

import annotation.annotationInterface.AbstractVerify;
import annotation.annotationInterface.DateTimeValidate;
import annotation.annotationInterface.IVerify;
import excaption.BusinessException;
import excaption.SystemException;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Builder @AllArgsConstructor
public class DateTimeValidateImpl extends AbstractVerify implements IVerify {
    @Override
    public <T> void verify(Field field, T t) {
        DateTimeValidate timeValidate = field.getAnnotation(DateTimeValidate.class);
        if (!nullStr.equals(timeValidate.conditions()) && !conditionsVerify(timeValidate.conditions(), field, t)) {
            return;
        }
        try {
            field.setAccessible(true);
            String value = String.valueOf(field.get(t));
            String pattern = timeValidate.pattern();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            simpleDateFormat.parse(value);
        }catch (ParseException e){
            throw new BusinessException(timeValidate.code(),timeValidate.message());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "时间校验系统异常");
        }
    }

}
