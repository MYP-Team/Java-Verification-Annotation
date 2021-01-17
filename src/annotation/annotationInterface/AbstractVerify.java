package annotation.annotationInterface;

import annotation.annotationInterface.utils.AnnotationUtils;
import excaption.BusinessException;
import excaption.SystemException;

import java.lang.reflect.Field;

public abstract class AbstractVerify extends AnnotationUtils implements IVerify{

    protected static <T> boolean conditionsVerify(String condition, Field field, T t){
        String[] conditions = new String[1];
        try{
            if(condition.contains(or)){
                conditions=condition.split(or);
                conditionsVerify(conditions,field,t,or);
            }else if(condition.contains(and)){
                conditions=condition.split(and);
                conditionsVerify(conditions,field,t,and);
            }else {
                conditions[0]=condition;
            }
            return conditionsVerify(conditions,field,t,"");
        }catch(BusinessException e){
            throw new BusinessException(e.getCode(),e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(e, "DSF1234", "条件校验系统异常");
        }
    }

}
