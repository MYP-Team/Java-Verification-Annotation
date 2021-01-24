package annotation.annotationInterface;

import annotation.annotationInterface.utils.AnnotationUtils;
import annotation.annotationInterface.utils.ConditionsUtils;
import entrys.Logic;
import excaption.BusinessException;
import excaption.SystemException;

import java.lang.reflect.Field;

public abstract class AbstractVerify extends AnnotationUtils implements IVerify{

    protected static <T> boolean conditionsVerify(String condition, Field field, T t){
        Logic logic=ConditionsUtils.handleContentLogic(condition);
        try{
            boolean bool=conditionsVerify(logic,field,t);
            System.out.println(bool);
            return bool;
        }catch(BusinessException e){
            throw new BusinessException(e.getCode(),e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "条件校验系统异常");
        }
    }

}
