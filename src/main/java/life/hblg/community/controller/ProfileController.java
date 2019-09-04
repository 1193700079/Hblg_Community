package life.hblg.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import life.hblg.community.dto.PaginationDTO;
import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.User;
import life.hblg.community.model.UserExample;
import life.hblg.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "ProfileController |用户的个人界面")
@RestController
public class ProfileController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TopicService topicService;

    @ApiOperation(value="获取指定用户的话题列表信息", notes="通过PageHelper来实现分页")
    @ResponseBody
    @GetMapping("/profile/{action}")
    public String[] profile(@PathVariable(name = "action")String action,
                          @RequestParam(name = "pageId",defaultValue = "1")Integer pageId,
                          @RequestParam(name = "size",defaultValue = "5")Integer size){
        if(action.equals ( "topics" )){
            String section = "topics";
            String sectionName = "我的话题哎呀~";
            String[] result = new String[2];
            result[0] = section;
            result[1] = sectionName;
            return result;
        }else if(action.equals ( "replies" )){
            String section = "replies";
            String sectionName = "这些都是我的回复哟";
            String[] result = new String[2];
            result[0] = section;
            result[1] = sectionName;
            return result;
        }
        return null;
    }

//    有bug user拿不到
    @GetMapping("/profile/topics/pageInfo")
    @ApiOperation(value="分页信息", notes="通过PageHelper来实现分页")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "pageId", value = "页码",  dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "size", value = "每页话题个数", required = false, dataType = "int")
    })
    @ResponseBody
    public Object pageInfo(HttpServletRequest request,
                            @RequestParam(name = "pageId",defaultValue = "1")Integer pageId,
                            @RequestParam(name = "size",defaultValue = "5")Integer size) {
        User user = (User) request.getSession ().getAttribute ( "user" );
        if(user==null){
//            return "redirect:/";
        }
        //拿到分页信息
        PaginationDTO paginationDTO =  topicService.getListByUserId(user.getId (),pageId,size);
        return paginationDTO;
    }

//    //获取User信息
//    public User getUserMsg(HttpServletRequest request){
//        Cookie[] cookies=request.getCookies ();
//        for (Cookie cookie : cookies) {
//            if (cookie.getName ( ).equals ( "token" )) {
//                String token = cookie.getValue ( );
//                //使用 mybatis generator 的方法
//                UserExample userExample = new UserExample ();  //创建实例
//                userExample.createCriteria ().andTokenEqualTo ( token );  //使用方法
//                List<User> users = userMapper.selectByExample ( userExample );
//                if(users.size () != 0){
//                    request.getSession ().setAttribute ( "user",users.get ( 0 ) );
//                }
//                break;
//            }
//        }
//        return null;
//    }
}
