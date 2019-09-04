package life.hblg.community.dto;

import lombok.Data;

/*从前端接受过来的DTO
* */
@Data
public class CommentCreateDTO {
    private Integer topicId;
    private String commentContent;
    private Integer type;
    private Integer commentorId;
}
