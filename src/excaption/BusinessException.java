package excaption;


public class BusinessException extends CommonException {

    private static final long serialVersionUID = 3045092346530979760L;

    public  BusinessException(String code,Object... errorInfo){
        super(code, errorInfo);
    }
    public  BusinessException(Throwable throwable,String code,Object... errorInfo){
        super(throwable,code, errorInfo);
    }
    @Override
    public String getExcepLevel() {
        return "busi";
    }
}
