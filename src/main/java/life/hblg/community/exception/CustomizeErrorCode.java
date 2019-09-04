package life.hblg.community.exception;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NOTATIONDatatypeValidator;

public enum CustomizeErrorCode implements InterfaceCustomizeErrorCode{
    QUSETION_NOT_FOUND(2001," 你找的话题 不存在哟"),
    COMMNET_NOT_FOUND(2002," 没有这个回复 不存在哟~~"),
    authority_NOT_FOUND(2003," 亲亲 您没有权限访问哟 不存在哟~~"),
    NOT_LOGIN_IN( 2004,"没有登录  请先登录 ！！！QAQ"),
    NOT_TOPIC(2005,"没有此话题"),
    NOT_COMMENT(2006,"没有此回复"),
    NOT_COMMENTTYPE(2007,"只有type为1 或者为2 才行哦 1代表一级评论 2代表二级评论"),
    COMMENT_ISNULL (2008,"评论不能为空哟"),
    INVALID(2009,"输入非法标签")
    ; //枚举类的使用

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String msg;
    private Integer code;

//shift + F6
    CustomizeErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    
}
