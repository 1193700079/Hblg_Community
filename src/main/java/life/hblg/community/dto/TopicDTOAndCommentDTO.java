package life.hblg.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class TopicDTOAndCommentDTO {
    TopicDTO topicDTO;
    List<CommentDTO> commentDTO;
}
