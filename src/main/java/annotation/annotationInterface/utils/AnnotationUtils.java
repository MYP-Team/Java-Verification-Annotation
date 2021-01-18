package annotation.annotationInterface.utils;

import annotation.annotationInterface.*;
import annotation.annotationInterface.Number;
import annotation.annotationInterface.factory.VerifyFactory;
import excaption.BusinessException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationUtils {

    protected static final String or="||";
    protected static final String and="&&";
    protected static final String equal="==";
    protected static final String unequal="!=";
    protected static final String isNotBlank=".IsNotBlank";
    protected static final String isBlank=".IsBlank";
    protected static final String This="this.";
    protected static final String nullStr="";


    protected static <T> void verifyClass(T t) throws BusinessException {
        if(t.getClass().isAnnotationPresent(CustomVerify.class)){
            VerifyFactory.getCustomVerify().classVerify(t);
        }
    }

    protected static <T> void verifyField(Field field, T t) throws BusinessException{
        if (field.isAnnotationPresent(NotBlank.class)) {
            VerifyFactory.getIsNotBlank().verify(field,t);
        }
        select(field,t);
    }

    private static <T> void select(Field field,T t)throws BusinessException{
        if(field.isAnnotationPresent(Select.class)){
            VerifyFactory.getSelect().verify(field,t);
        }
        length(field,t);
    }

    private static <T> void length(Field field,T t)throws BusinessException{
        if(field.isAnnotationPresent(Length.class)){
            VerifyFactory.getLength().verify(field,t);
        }
        dateTimeValidate(field,t);
    }

    private static <T> void dateTimeValidate(Field field,T t)throws BusinessException{
        if(field.isAnnotationPresent(DateTimeValidate.class)){
            VerifyFactory.getDateTimeValidate().verify(field,t);
        }
        number(field,t);
    }

    private static <T> void number(Field field,T t)throws BusinessException{
        if(field.isAnnotationPresent(Number.class)){
            VerifyFactory.getNumber().verify(field,t);
        }
        customVerify(field,t);
    }

    private static <T> void customVerify(Field field,T t)throws BusinessException{
        if(field.isAnnotationPresent(CustomVerify.class)){
            VerifyFactory.getCustomVerify().verify(field,t);
        }
    }


    protected static <T> boolean conditionsVerify(String[] conditions,Field field,T t,String logic) throws IllegalAccessException {
        Boolean result = false;
        switch (logic) {
            case or:
                for (String singleIf : conditions) {
                    if (isNotBlankVerify(singleIf, field, t)) {
                        result = true;
                        break;
                    }
                }
                break;
            case and: {
                result = true;
                for (String singleIf : conditions) {
                    if (!isNotBlankVerify(singleIf, field, t)) {
                        result = false;
                        break;
                    }
                }
            }
            break;
            default:
                isNotBlankVerify(conditions[0], field, t);
                break;
        }

        return result;
    }


    private static <T> Object findField(String condition,T t) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        List<Field> fieldList= Arrays.stream(fields).parallel().filter(f -> f.getName().equals(condition)).collect(Collectors.toList());
        if(fieldList.size()==0){
            throw new BusinessException("DSF1234","找不到字段"+condition);
        }else {
            Field field=fieldList.get(0);
            field.setAccessible(true);
            return field.get(t);
        }
    }


    private static <T> boolean isNotBlankVerify(String condition,Field field,T t) throws IllegalAccessException {
        if(condition.contains(isNotBlank)){
            if(!condition.contains(This)){
                throw new BusinessException("DSF1234","判断语句不合法");
            }
            condition=condition.replace(isNotBlank,"").replace(This,"");
            Object value=findField(condition,t);
            return null != value;
        }else return isBlankVerify(condition,field,t);
    }

    private static <T> boolean isBlankVerify(String condition,Field field,T t) throws IllegalAccessException {
        if(condition.contains(isBlank)){
            if(!condition.contains(This)){
                throw new BusinessException("DSF1234","判断语句不合法");
            }
            condition=condition.replace(isNotBlank,"").replace(This,"");
            Object value=findField(condition,t);
            return null == value;
        }else return unequalVerify(condition,field,t);
    }

    private static <T> boolean unequalVerify(String condition,Field field,T t) throws IllegalAccessException {
        if(condition.contains(unequal)){
            Object value1 = null;
            Object value2 = null;
            String[] conditions= condition.split(unequal);
            if(conditions[0].contains(This)){
                conditions[0]=conditions[0].replace(This,"");
                value1=findField(conditions[0],t);
            }
            if(conditions[1].contains(This)){
                conditions[1]=conditions[1].replace(This,"");
                value2=findField(conditions[1],t);
            }
            return value1!=value2;
        }else return equalVerify(condition,field,t);
    }

    private static <T> boolean equalVerify(String condition,Field field,T t) throws IllegalAccessException {
        if(condition.contains(equal)){
            Object value1 = null;
            Object value2 = null;
            String[] conditions= condition.split(equal);
            if(conditions[0].contains(This)){
                conditions[0]=conditions[0].replace(This,"");
                value1=findField(conditions[0],t);
            }
            if(conditions[1].contains(This)){
                conditions[1]=conditions[1].replace(This,"");
                value2=findField(conditions[1],t);
            }
            return value1==value2;
        }else throw new BusinessException("DSF1234","判断语句不合法");
    }
}
