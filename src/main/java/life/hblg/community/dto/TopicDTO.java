package life.hblg.community.dto;

import life.hblg.community.model.User;
import lombok.Data;

@Data
public class TopicDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer createId;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
