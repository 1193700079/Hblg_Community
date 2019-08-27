package life.hblg.community.model;

import lombok.Data;

@Data
public class Topic {
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

}
