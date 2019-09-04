package life.hblg.community.enums;

public enum CommentTypeEnum {
    TOPIC_TYPE(1),
    COMMNET_TYPE(2);
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
