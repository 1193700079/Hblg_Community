package life.hblg.community.dto;

import lombok.Data;

@Data
public class GithubUser {

    private String name;
    private Long id;
    private String login;
    private String bio;
    private String avatar_url;

}
