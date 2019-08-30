package life.hblg.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Integer topicId;
    private String commentContent;
    private Integer type;
    private Integer commentorId;
}
