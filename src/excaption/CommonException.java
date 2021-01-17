package excaption;


public class CommonException extends RuntimeException {

    private static final long serialVersionUID = 6546356312111399611L;
    // 错误编码
    private String code;
    // 语言版
    private String language ;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    // 错误级别      busi-业务异常；sys-系统、技术异常。
    public String getExcepLevel() {
        return null;
    }

    public CommonException(String code, Object...msgParams) {
        super(ExceptionBuilder.getInstance()
                .buildErrorMsg(code, msgParams));
        this.code = code;
    }

    public CommonException(Throwable throwable,String code, Object...msgParams) {
        super(ExceptionBuilder.getInstance()
                .buildErrorMsg(code, msgParams),throwable);
        this.code = code;
    }

}
