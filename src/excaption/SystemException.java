package excaption;

public class SystemException extends CommonException {

    private static final long serialVersionUID = 8453769339737170500L;

    public  SystemException(String code,Object... errorInfo){
        super(code, errorInfo);
    }
    public  SystemException(Throwable throwable,String code,Object... errorInfo){
        super(throwable,code, errorInfo);
    }
    @Override
    public String getExcepLevel() {
        return "sys";
    }
}