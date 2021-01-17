package excaption;

import java.util.HashMap;
import java.util.Map;

import org.drools.core.util.StringUtils;

public class ExceptionBuilder {

    private Map<String,String> cache = new HashMap<String,String>();

    public void setCache(Map<String,String> data){
        cache.clear();
        cache.putAll(data);
    }

    private static ExceptionBuilder instance ;
    public static ExceptionBuilder getInstance(){
        if(instance == null){
            instance = new ExceptionBuilder();
        }
        return instance;
    }

    private ExceptionBuilder(){

    }

    /**
     * 通过错误码和语言信息构建、错误信息。<br>
     * 错误码于语言的匹配规则是 错误码key。<br>
     * 使用key从缓存中获取错误信息，错误信息的配置是采用站位符的标准，如一个信息中使用 {0}，{1} 来白哦啊是错误信息的替换位置<br>
     *
     * @param code
     * @param language
     * @param msgParams
     * @return
     */
    public String buildErrorMsg(String code, Object...msgParams){
        String errorMsg = cache.get(code);
        // 如果错误信息为诶空
        if(StringUtils.isEmpty(errorMsg)){
            StringBuffer msg=new StringBuffer();
            if(msgParams != null && msgParams.length > 0){
                for(Object msgParam:msgParams){
                    msg.append(msgParam.toString());
                }
                return msg.toString();
            }
            return "";
        }
        // 替换错误信息码中的{0},{1}
        int index = 0;
        if(msgParams == null || msgParams.length <= 0){
            return errorMsg;
        }
        for(Object msgParam:msgParams){
            String replaceStr = "\\{"+index+"\\}";
            errorMsg=errorMsg.replaceAll(replaceStr, msgParam.toString());
            index ++;
        }
        return errorMsg;
    }

}
