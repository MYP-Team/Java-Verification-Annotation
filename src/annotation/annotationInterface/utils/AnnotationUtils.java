package annotation.annotationInterface.utils;

import annotation.annotationInterface.*;
import annotation.annotationInterface.Number;
import annotation.annotationInterface.enums.ConstEnum;
import annotation.annotationInterface.enums.LogicEnum;
import annotation.annotationInterface.enums.OperatorEnum;
import annotation.annotationInterface.factory.VerifyFactory;
import entrys.Logic;
import excaption.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotationUtils {

    protected static final String or= LogicEnum.Or.getValue();
    protected static final String orBracket= LogicEnum.OrBracket.getValue();
    protected static final String and=LogicEnum.And.getValue();
    protected static final String andBracket=LogicEnum.AndBracket.getValue();
    protected static final String splitOr=ConstEnum.SplitOr.getValue();
    protected static final String equal=OperatorEnum.Equal.getValue();
    protected static final String unequal=OperatorEnum.UnEqual.getValue();
    protected static final String isNotBlank=OperatorEnum.IsNotBlank.getValue();
    protected static final String isBlank=OperatorEnum.IsBlank.getValue();
    protected static final String greater=OperatorEnum.Greater.getValue();
    protected static final String greaterOrEqual=OperatorEnum.GreaterOrEqual.getValue();
    protected static final String less=OperatorEnum.Less.getValue();
    protected static final String lessOrEqual=OperatorEnum.LessOrEqual.getValue();
    protected static final String This=ConstEnum.This.getValue();
    protected static final String nullStr=ConstEnum.NullStr.getValue();


    protected static <T> void verifyClass(T t) throws BusinessException {
        if(t.getClass().isAnnotationPresent(CustomVerify.class)){
            VerifyFactory.getCustomVerify().verify(t);
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


    protected static <T> boolean conditionsVerify(Logic logic,Field field,T t) throws IllegalAccessException {
        String left=logic.getLeft();
        if(StringUtils.isBlank(left)||StringUtils.isBlank(logic.getSymbol())||!left.contains(This)){
            throw new BusinessException("DSF1234","判断语句不合法");
        }
        boolean bool = isNotBlankVerify(logic,field,t);
        return  getLogicByResult(logic,bool,field,t);
    }

    private static <T> boolean isNotBlankVerify(Logic logic,Field field,T t) throws IllegalAccessException  {
        if(isNotBlank.equals(logic.getSymbol())){
            if(StringUtils.isNotBlank(logic.getRight())){
                throw new BusinessException("DSF1234","判断语句不合法");
            }
            return StringUtils.isNotBlank(findField(logic.getLeft().replace(This,""),t));
        }else return isBlankVerify(logic,field,t);
    }

    private static <T> boolean isBlankVerify(Logic logic,Field field,T t) throws IllegalAccessException  {
        if(isBlank.equals(logic.getSymbol())){
            if(StringUtils.isNotBlank(logic.getRight())){
                throw new BusinessException("DSF1234","判断语句不合法");
            }
            return StringUtils.isBlank(findField(logic.getLeft().replace(This,""),t));
        }else return unEqualVerify(logic,field,t);
    }

    private static <T> boolean unEqualVerify(Logic logic,Field field,T t) throws IllegalAccessException  {
        if(unequal.equals(logic.getSymbol())){
            String left = logic.getLeft();
            String right = logic.getRight();
            if(left.contains(This)){
                left=left.replace(This,"");
                left=findField(left,t);
            }
            if(right.contains(This)){
                right=right.replace(This,"");
                right=findField(right,t);
            }
            return !left.equals(right);
        }else return equalVerify(logic,field,t);
    }


    private static <T> boolean equalVerify(Logic logic,Field field,T t) throws IllegalAccessException  {
        if(equal.equals(logic.getSymbol())){
            String left = logic.getLeft();
            String right = logic.getRight();
            if(left.contains(This)){
                left=left.replace(This,"");
                left=findField(left,t);
            }
            if(right.contains(This)){
                right=right.replace(This,"");
                right=findField(right,t);
            }
            return left.equals(right);
        }else return greaterVerify(logic,field,t);
    }

    private static <T> boolean greaterVerify(Logic logic,Field field,T t) throws IllegalAccessException  {
        String symbol=logic.getSymbol();
        if(greater.equals(symbol)||greaterOrEqual.equals(symbol)){
            String left = logic.getLeft();
            String right = logic.getRight();
            if(left.contains(This)){
                left=left.replace(This,"");
                left=findField(left,t);
            }
            if(right.contains(This)){
                right=right.replace(This,"");
                right=findField(right,t);
            }
            double leftValue = 0;
            double rightValue = 0;
            try{
                 leftValue = Double.parseDouble(left);
                 rightValue = Double.parseDouble(right);
            }catch (NumberFormatException e) {
                throw new BusinessException("DSF1234","判断语句不合法,非数字类型不能使用大小与判断");
            }
            if(greaterOrEqual.equals(symbol)){
                return leftValue>=rightValue;
            }else return leftValue>rightValue;
        }else return lessVerify(logic,field,t);
    }


    private static <T> boolean lessVerify(Logic logic,Field field,T t) throws IllegalAccessException  {
        String symbol=logic.getSymbol();
        if(less.equals(symbol)||lessOrEqual.equals(symbol)){
            String left = logic.getLeft();
            String right = logic.getRight();
            if(left.contains(This)){
                left=left.replace(This,"");
                left=findField(left,t);
            }
            if(right.contains(This)){
                right=right.replace(This,"");
                right=findField(right,t);
            }
            double leftValue = 0;
            double rightValue = 0;
            try{
                leftValue = Double.parseDouble(left);
                rightValue = Double.parseDouble(right);
            }catch (NumberFormatException e) {
                throw new BusinessException("DSF1234","判断语句不合法,非数字类型不能使用大小与判断");
            }
            if(lessOrEqual.equals(symbol)){
                return leftValue<=rightValue;
            }else return leftValue<rightValue;
        }else throw new BusinessException("DSF1234","判断语句不合法");
    }

    private static <T> String findField(String condition,T t) throws IllegalAccessException {
        Field[] fields = t.getClass().getDeclaredFields();
        List<Field> fieldList= Arrays.stream(fields).parallel().filter(f -> f.getName().equals(condition)).collect(Collectors.toList());
        if(fieldList.size()==0){
            throw new BusinessException("DSF1234","找不到字段"+condition);
        }else {
            Field field=fieldList.get(0);
            field.setAccessible(true);
            Object result=field.get(t);
            if(null==result){
                return "";
            }else return String.valueOf(result);
        }
    }

    private static <T> boolean getLogicByResult(Logic logic,boolean flag,Field field,T t) throws IllegalAccessException {
        if(flag){
            if(logic.getSucLogic()==null){
                return true;
            }else {
                return conditionsVerify(logic.getSucLogic(),field,t);
            }
        }else {
            if(logic.getFailLogic()==null){
                return false;
            }else {
                return conditionsVerify(logic.getFailLogic(),field,t);
            }
        }
    }

}
