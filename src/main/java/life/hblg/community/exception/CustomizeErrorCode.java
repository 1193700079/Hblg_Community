package life.hblg.community.exception;

public enum CustomizeErrorCode implements InterfaceCustomizeErrorCode{
    QUSETION_NOT_FOUND(" 你找的话题 不存在哟"); //枚举类的使用

    @Override
    public String getMsg() {
        return msg;
    }
    private String msg;

    CustomizeErrorCode(String msg) {
        this.msg = msg;
    }
}
