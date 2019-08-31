package life.hblg.community.exception;

//继承RuntimeException 这样在抛出异常的时候就不需要try catch 了
public class CustomizeException extends RuntimeException {
    private String msg;

    public CustomizeException(CustomizeErrorCode errorCode) {
        this.msg = errorCode.getMsg ();
    }

    public CustomizeException(String msg) {
        this.msg = msg;
    }

    //重写父类方法
    @Override
    public String getMessage() {
        return msg;
    }
}
