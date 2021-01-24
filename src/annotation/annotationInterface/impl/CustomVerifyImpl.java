package annotation.annotationInterface.impl;

import annotation.annotationInterface.AbstractClassVerify;
import annotation.annotationInterface.CustomVerify;
import annotation.annotationInterface.IVerify;
import excaption.BusinessException;
import excaption.SystemException;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.lang.reflect.Field;

@Builder @AllArgsConstructor
public class CustomVerifyImpl extends AbstractClassVerify implements IVerify {
    @Override
    public <T> void verify(Field field, T t) {
        CustomVerify customVerify=field.getAnnotation(CustomVerify.class);
        String className=customVerify.className();
        String methodName=customVerify.methodName();
        if(!nullStr.equals(customVerify.conditions())&&!conditionsVerify(customVerify.conditions(), field, t)){
            return;
        }
        try{
            field.setAccessible(true);
            Class<?> clazz=Class.forName(className);
            clazz.getMethod(methodName, field.getClass(),Object.class).invoke(clazz.newInstance(),field,t);
        }catch (BusinessException e){
            throw new BusinessException(customVerify.code(),customVerify.message());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "自定义校验系统异常");
        }
    }

    @Override
    public <T> void verify(T t) {
        Class<?> clazz=t.getClass();
        CustomVerify customVerify=clazz.getAnnotation(CustomVerify.class);
        String className=customVerify.className();
        String methodName=customVerify.methodName();
        try{
            Class<?> inputClazz=Class.forName(className);
            inputClazz.getMethod(methodName,Object.class).invoke(inputClazz.newInstance(),t);
        }catch (BusinessException e){
            throw new BusinessException(customVerify.code(),customVerify.message());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "自定义校验系统异常");
        }
    }
}
