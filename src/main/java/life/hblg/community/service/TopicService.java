package life.hblg.community.service;

import life.hblg.community.dto.TopicDTO;
import life.hblg.community.mapper.TopicMapper;
import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.Topic;
import life.hblg.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Service 层用来组装User和Topic
@Service
public class TopicService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TopicMapper topicMapper;

    public List<TopicDTO> getList() {
        List<Topic> topicList = topicMapper.getList ();
        List<TopicDTO> topicDTOList = new ArrayList <> ();
        for (Topic topic : topicList) {
            //1.通过找到topic表中对应的关联UserID找到User
            User user = userMapper.findById ( topic.getCreateId () );
            //2.将找到的User 放入两表的DTO中
            TopicDTO topicDTO=new TopicDTO ();
            BeanUtils.copyProperties ( topic,topicDTO);//3.spring中的工具类 将topic的属性都给topicDTO
            topicDTO.setUser ( user );
            topicDTOList.add ( topicDTO );  //将每个DTO添加到TopicLIST中
        }
        return  topicDTOList;
    }
}
