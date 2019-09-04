package life.hblg.community;


//评论类型
public enum CommentType {
    TOPIC_TYPE(1),
    COMMENT_TYPE(2)
    ;
    private Integer type;

    CommentType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
