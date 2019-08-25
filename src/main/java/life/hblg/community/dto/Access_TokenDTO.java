package life.hblg.community.dto;

import lombok.Data;

//只是一个单纯的Access_Token数据封装类 体现了oop的思想
@Data
public class Access_TokenDTO {
    private  String client_id;
    private  String client_secret;
    private  String code;
    private  String redirect_uri;
    private  String state;

}
