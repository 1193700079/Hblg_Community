package life.hblg.community.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import life.hblg.community.dto.PaginationDTO;
import life.hblg.community.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController //处理URL请求的
@Api(value = "IndexController|首页的控制器")
public class IndexController {

    @Autowired
    private TopicService topicService;

   @ResponseBody
   @ApiOperation(value="获取话题列表信息", notes="通过PageHelper来实现分页")
   @ApiImplicitParams({
           @ApiImplicitParam(paramType="query", name = "pageId", value = "页码",  dataType = "int"),
           @ApiImplicitParam(paramType="query", name = "size", value = "每页话题个数", required = false, dataType = "int")
   })
    @GetMapping("")
    public Object indexList(@RequestParam(name = "pageId",defaultValue = "1")Integer pageId,
                            @RequestParam(name = "size",defaultValue = "10")Integer size){
//        拿到分页信息
        PaginationDTO paginationDTO = topicService.getList(pageId, size);

        return  paginationDTO;
    }
}