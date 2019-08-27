package life.hblg.community.service;

import life.hblg.community.dto.PaginationDTO;
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

    public PaginationDTO getList(Integer pageId, Integer size) {
        //由于数据库中的1 2 3 4 都表示第一页
        //因此currentId(offset) 只有 0 5 10这样的取值
        Integer offset = size*(pageId-1);

        List<Topic> topicList = topicMapper.getList (offset,size);
        List<TopicDTO> topicDTOList = new ArrayList <> ();

        PaginationDTO paginationDTO = new PaginationDTO ( );
        for (Topic topic : topicList) {
            //1.通过找到topic表中对应的关联UserID找到User
            User user = userMapper.findById ( topic.getCreateId () );
            //2.将找到的User 放入两表的DTO中
            TopicDTO topicDTO=new TopicDTO ();
            BeanUtils.copyProperties ( topic,topicDTO);//3.spring中的工具类 将topic的属性都给topicDTO
            topicDTO.setUser ( user );
            topicDTOList.add ( topicDTO );  //将每个DTO添加到TopicLIST中
        }
        paginationDTO.setTopicDTOs( topicDTOList);
        Integer totalCount = topicMapper.Count ();
        paginationDTO.setPagenation(totalCount,pageId,size);
        return  paginationDTO;
    }

    //得到某个用户的列表 通过用户的Id值
    public PaginationDTO getListByUserId(Integer userId, Integer pageId, Integer size) {
        //由于数据库中的1 2 3 4 都表示第一页
        //因此currentId(offset) 只有 0 5 10这样的取值
        Integer offset = size*(pageId-1);

        List<Topic> topicList = topicMapper.getListByUserId(userId,offset,size);
        List<TopicDTO> topicDTOList = new ArrayList <> ();

        PaginationDTO paginationDTO = new PaginationDTO ( );
        for (Topic topic : topicList) {
            //1.通过找到topic表中对应的关联UserID找到User
            User user = userMapper.findById ( topic.getCreateId () );
            //2.将找到的User 放入两表的DTO中
            TopicDTO topicDTO=new TopicDTO ();
            BeanUtils.copyProperties ( topic,topicDTO);//3.spring中的工具类 将topic的属性都给topicDTO
            topicDTO.setUser ( user );
            topicDTOList.add ( topicDTO );  //将每个DTO添加到TopicLIST中
        }
        paginationDTO.setTopicDTOs( topicDTOList);
        Integer totalCount = topicMapper.CountByuserId (userId);
        paginationDTO.setPagenation(totalCount,pageId,size);
        return  paginationDTO;
    }

    //得到详情列表
    public TopicDTO getTopicDetialById(Integer id) {
        Topic topic =topicMapper.getTopicDetialById(id);
        TopicDTO topicDTO = new TopicDTO ();
        BeanUtils.copyProperties ( topic,topicDTO);//3.spring中的工具类 将topic的属性都给topicDTO
        User user = userMapper.findById ( topic.getCreateId () );
        topicDTO.setUser ( user );
        return  topicDTO;
    }

    //
    public void insertOrUpdate(Topic topic) {
        //这个是在程序中设置的ID 和数据库没有关系
        Integer searchId = topic.getId ();
        if(searchId==null){
            //没有查到 说明是第一次
            topic.setGmtCreate ( System.currentTimeMillis () );
            topic.setGmtModify ( topic.getGmtCreate () );
            topicMapper.insert(topic);
        }else {
            //更新
            topic.setGmtModify(System.currentTimeMillis ());
            topicMapper.update(topic);
        }
    }
}
