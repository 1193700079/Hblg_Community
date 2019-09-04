package life.hblg.community.dto;

import life.hblg.community.model.User;
import lombok.Data;

/*
* 在后端传输的DTO
* */
@Data
public class CommentDTO {
    private Integer id;

    private Integer topicId;

    private Integer commentorId;

    private Integer type;

    private Integer likeCount;

    private Long gmtCreate;

    private Long gmtModify;

    private String commentContent;

    private User user;

    private Integer commentCount;
}
