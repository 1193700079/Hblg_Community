package life.hblg.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import life.hblg.community.cache.TagCache;
import life.hblg.community.dto.*;
import life.hblg.community.exception.CustomizeErrorCode;
import life.hblg.community.exception.CustomizeException;
import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.Topic;
import life.hblg.community.model.User;
import life.hblg.community.service.TopicService;
import life.hblg.community.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(value = "publish路由控制器|PublishController")
@Controller
public class PublishController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @ResponseBody
    @ApiOperation(value="编辑界面 可以拿到未编辑的数据", notes="")
    @GetMapping("/publish/{id}")
    public Object edit(@PathVariable(name = "id")Integer id,
                      HttpServletRequest request){
        //发送话题的用户ID！= 编辑用户的ID
   /*     User user = (User) request.getSession ().getAttribute ( "user" );  //编辑用户的ID
        User topicUser =  topicService.findById(id);//根据id查到发送话题的用户ID
        Boolean flag =( user.getId () == topicUser.getId ());
        if(flag == false){
            throw new CustomizeException ( CustomizeErrorCode.authority_NOT_FOUND);
        }*/
        TopicDTO topic = topicService.getTopicDetialById ( id );
        return topic;
    }

    @ResponseBody
    @ApiOperation(value="标签分类 ", notes="")
    @GetMapping("/publish")
    public ResultDTO publish(){

       /* TagCache tagCache = new TagCache ();
        List<TagDTO> tagDTOS = tagCache.get ();
        return ResultDTO.okOf ( tagDTOS );*/
        return ResultDTO.okOf (  );
    }

    @PostMapping("/publish")   //接受post请求
    @ApiOperation(value="提交话题界面 ", notes="")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "title", value = "话题标题",  dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "description", value = "话题内容", required = false, dataType = "String"),
            @ApiImplicitParam(paramType="query", name = "tag", value = "话题标签", required = false, dataType = "String"),
    })
    public ResultDTO doPublish(@RequestBody PublishDTO publishDTO,
                               HttpServletRequest request
                            ){
        //由于在拦截器中已经添加了user信息
        User user = (User) request.getSession ().getAttribute ( "user" );
        if(user==null){
          //  throw new CustomizeException ( CustomizeErrorCode.NOT_LOGIN_IN );
        }
        /*request.getSession ();
        User user = getUserMsg(request);*/

       /* String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            return ResultDTO.errorOf ( CustomizeErrorCode.INVALID );
        }*/
       String title = publishDTO.getTitle ();
       String description = publishDTO.getDescription ();
       String tag = publishDTO.getTag ();
    //   Topic topic =  topicService.createTopic (title,description,tag,user);
        Topic topic =  topicService.createTopic (title,description,tag);
        topicService.insertOrUpdate (topic);
        return ResultDTO.okOf ();
    }


    //获取User信息
    public  User getUserMsg(HttpServletRequest request){
        Cookie[] cookies=request.getCookies ();
        for (Cookie cookie : cookies) {
            if (cookie.getName ( ).equals ( "token" )) {
                String token = cookie.getValue ( );
                User user = userService.findByToken ( token );
                if (user != null) {
                    request.getSession ().setAttribute ( "user",user );
                    return user;
                }
                break;
            }
        }
        return null;
    }
}


//            if(title == null || title == ""){
////        model.addAttribute ( "error" ,"标题不能为空");
//////            modelMap.addFlashAttribute ( "errorMap","标题不能为空********" );
////        return "publish";
////    }
////        if(description == null || description == ""){
////        model.addAttribute ( "error" ,"内容不能为空");
////        return "publish";
////    }
////        if(tag == null || tag == ""){
////        model.addAttribute ( "error" ,"标签不能为空");
////        return "publish";
////    }