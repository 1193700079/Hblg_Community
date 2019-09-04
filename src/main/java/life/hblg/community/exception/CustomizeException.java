package life.hblg.community.exception;

//继承RuntimeException 这样在抛出异常的时候就不需要try catch 了
public class CustomizeException extends RuntimeException {
    private String msg;
    private Integer code;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.code = errorCode.getCode ();
        this.msg = errorCode.getMsg ();
    }

    //重写父类方法  !!
    @Override
    public String getMessage() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
