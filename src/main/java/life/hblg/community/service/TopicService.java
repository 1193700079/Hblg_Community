package life.hblg.community.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import life.hblg.community.dto.PaginationDTO;
import life.hblg.community.dto.TopicDTO;
import life.hblg.community.exception.CustomizeErrorCode;
import life.hblg.community.exception.CustomizeException;
import life.hblg.community.mapper.TopicExtMapper;
import life.hblg.community.mapper.TopicMapper;
import life.hblg.community.mapper.UserExtMapper;
import life.hblg.community.mapper.UserMapper;
import life.hblg.community.model.Topic;
import life.hblg.community.model.TopicExample;
import life.hblg.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
    private UserExtMapper userExtMapper;

    @Autowired
    private TopicMapper topicMapper;

//    TopicMapper的拓展功能
    @Autowired
    private TopicExtMapper topicExtMapper;

    public PaginationDTO getList(Integer pageId, Integer size) {
        //由于数据库中的1 2 3 4 都表示第一页
        //因此currentId(offset) 只有 0 5 10这样的取值
     //   Integer offset = size*(pageId-1);

        //使用插件 实现分页 第1页  2条内容...1
        PageHelper.startPage(pageId, size);
        List <Topic> topicList = topicMapper.selectByExample ( new TopicExample () );
        PageInfo pageInfo = new PageInfo(topicList);

//   List <Topic> topicList = topicMapper.selectByExampleWithRowbounds ( new TopicExample ( ), new RowBounds ( offset, size ) );

        //把topicList 传给 页面就可以了
        List<TopicDTO> topicDTOList = new ArrayList <> ();
        PaginationDTO paginationDTO = new PaginationDTO ( );
        //把每个分页的拿出来...5
        for (Topic topic : topicList){
            //1.通过找到topic表中对应的关联UserID找到User
            User user = userMapper.selectByPrimaryKey ( topic.getCreateId () );
            //2.将找到的User 放入两表的DTO中
            TopicDTO topicDTO=new TopicDTO ();
            BeanUtils.copyProperties ( topic,topicDTO);//3.spring中的工具类 将topic的属性都给topicDTO
            topicDTO.setUser ( user );
            topicDTOList.add ( topicDTO );  //将每个DTO添加到TopicLIST中
        }
        paginationDTO.setPageInfo ( pageInfo );
        paginationDTO.setTopicDTOs( topicDTOList);

//        TopicExample topicExample = new TopicExample ( );
//        Integer totalCount = (int)topicMapper.countByExample ( topicExample );
//        Integer totalCount = topicMapper.Count ();
//        paginationDTO.setPagenation(totalCount,pageId,size);

        return  paginationDTO;
    }

    public PaginationDTO getListByUserId(Integer userId,Integer pageId, Integer size) {


        TopicExample example = new TopicExample ( );
        example.createCriteria ().andCreateIdEqualTo ( userId );

        PageHelper.startPage(pageId, size);
        List <Topic> topicList = topicMapper.selectByExample ( example );

        PageInfo pageInfo = new PageInfo(topicList);

//   List <Topic> topicList = topicMapper.selectByExampleWithRowbounds ( new TopicExample ( ), new RowBounds ( offset, size ) );

        //把topicList 传给 页面就可以了
        List<TopicDTO> topicDTOList = new ArrayList <> ();
        PaginationDTO paginationDTO = new PaginationDTO ( );
        //把每个分页的拿出来...5
        for (Topic topic : topicList){
            //1.通过找到topic表中对应的关联UserID找到User
            User user = userMapper.selectByPrimaryKey ( topic.getCreateId () );
            //2.将找到的User 放入两表的DTO中
            TopicDTO topicDTO=new TopicDTO ();
            BeanUtils.copyProperties ( topic,topicDTO);//3.spring中的工具类 将topic的属性都给topicDTO
            topicDTO.setUser ( user );
            topicDTOList.add ( topicDTO );  //将每个DTO添加到TopicLIST中
        }
        paginationDTO.setPageInfo ( pageInfo );
        paginationDTO.setTopicDTOs( topicDTOList);

        return  paginationDTO;
    }
    //得到某个用户的列表 通过用户的Id值
    public PaginationDTO getListByUserId2(Integer userId, Integer pageId, Integer size) {
        //由于数据库中的1 2 3 4 都表示第一页
        //因此currentId(offset) 只有 0 5 10这样的取值
        Integer offset = size*(pageId-1);


        TopicExample example = new TopicExample ( );
        example.createCriteria ().andCreateIdEqualTo ( userId );

        List <Topic> topicList = topicMapper.selectByExampleWithRowbounds ( example, new RowBounds ( offset, size ) );
    //    List<Topic> topicList = topicMapper.getListByUserId(userId,offset,size);
        List<TopicDTO> topicDTOList = new ArrayList <> ();

        PaginationDTO paginationDTO = new PaginationDTO ( );
        for (Topic topic : topicList) {
            //1.通过找到topic表中对应的关联UserID找到User
            User user = userMapper.selectByPrimaryKey ( topic.getCreateId () );
            //2.将找到的User 放入两表的DTO中
            TopicDTO topicDTO=new TopicDTO ();
            BeanUtils.copyProperties ( topic,topicDTO);//3.spring中的工具类 将topic的属性都给topicDTO
            topicDTO.setUser ( user );
            topicDTOList.add ( topicDTO );  //将每个DTO添加到TopicLIST中
        }
        paginationDTO.setTopicDTOs( topicDTOList);

        TopicExample topicExample = new TopicExample ( );
        topicExample.createCriteria ().andCreateIdEqualTo ( userId );
        Integer totalCount = (int)topicMapper.countByExample ( topicExample );
    //    Integer totalCount = topicMapper.CountByuserId (userId);
//        paginationDTO.setPagenation(totalCount,pageId,size);
        return  paginationDTO;
    }

    //得到详情列表
    public TopicDTO getTopicDetialById(Integer id) {

        Topic topic =topicMapper.selectByPrimaryKey (id);
        if(topic == null){
            throw new CustomizeException ( CustomizeErrorCode.QUSETION_NOT_FOUND);
        }
        TopicDTO topicDTO = new TopicDTO ();
        BeanUtils.copyProperties ( topic,topicDTO);//3.spring中的工具类 将topic的属性都给topicDTO
        User user = userMapper.selectByPrimaryKey ( topic.getCreateId () );
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
            Topic updateTopic = new Topic ( );
            updateTopic.setGmtModify(System.currentTimeMillis ());
            updateTopic.setTitle ( topic.getTitle () );
            updateTopic.setDescription ( topic.getDescription () );
            updateTopic.setTag ( topic.getTag () );

            TopicExample example = new TopicExample ( );
            example.createCriteria ().andIdEqualTo ( topic.getId () );
            int updateID = topicMapper.updateByExampleSelective ( updateTopic, example );
            // updateID为0表示没有更新 为1表示更新成功
            if(updateID == 0 ){
                throw  new CustomizeException ( CustomizeErrorCode.QUSETION_NOT_FOUND );
            }
//            topicMapper.update(topic);
        }
    }

    //阅读数增加
    //1.取出指定ID的Topic
    //2.设置其ViewCount ++ 错误
//    正确做法（自增逻辑 需要在 查询逻辑之前）
//    1.直接在数据库中取出之前 就让数据 自加(这样在高并发的时候不会出错)
    public void incView(Integer id) {
//        TopicExample example = new TopicExample ( );
//        example.createCriteria ().andIdEqualTo ( id );
//
//        Topic topic = topicMapper.selectByPrimaryKey ( id );
//        Topic updateTopic = new Topic ( );
//        updateTopic.setViewCount (topic.getViewCount () + 1 );
//        topicMapper.updateByExampleSelective ( updateTopic,example);
//        上诉代码存在高并发异常的问题
        Topic topic = new Topic ( );
        topic.setId ( id );
        topic.setViewCount ( 1 );
        topicExtMapper.incView ( topic );

    }
}
