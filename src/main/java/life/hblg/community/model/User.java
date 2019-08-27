package life.hblg.community.model;

import lombok.Data;
import org.springframework.stereotype.Component;

//在数据库的数据交换中 存放在model中
@Component
@Data  //lombok插件 自动生成 get set toSting方法
public class User {


    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModify;
    private String avatarUrl; //图片
    private String bio;


}
