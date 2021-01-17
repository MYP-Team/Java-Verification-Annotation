package annotation.annotationInterface.factory;

import annotation.annotationInterface.impl.*;

public class VerifyFactory {

    public static IsNotBlankImpl getIsNotBlank(){
        return IsNotBlankImpl.builder().build();
    }

    public static CustomVerifyImpl getCustomVerify(){
        return CustomVerifyImpl.builder().build();
    }

    public static DateTimeValidateImpl getDateTimeValidate(){
        return DateTimeValidateImpl.builder().build();
    }

    public static LengthImpl getLength(){
        return LengthImpl.builder().build();
    }

    public static NumberImpl getNumber(){
        return NumberImpl.builder().build();
    }

    public static SelectImpl getSelect(){
        return SelectImpl.builder().build();
    }
}
