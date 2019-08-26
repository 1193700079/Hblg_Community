package life.hblg.community.dto;

import life.hblg.community.model.Topic;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//页面的页码信息  （面向对象）
@Data
public class PaginationDTO {

    //js 可以写 不过拿程序练练手
    private List<TopicDTO> topicDTOs;
    private Boolean isBackFirstPage;//是否能返回第一页 <<
    private Boolean isBackEndPage; //是否能返回最后一页  >>
    private Boolean isPrevious; //是否能向前 <
    private Boolean isNext; //是否能向后  >
    private Integer CurrentPageId; // 当前页
    private List<Integer> pages =  new ArrayList <> (); //所有页
    private Integer totalPage; //总页数


    public void setPagenation(Integer totalCount, Integer pageId, Integer size) {
        totalPage = totalCount;
        if(pageId<1){
            pageId = 1;
        }
        //求总页数
        Integer totalPage = (totalCount/size == 0) ? (totalCount/size) : (totalCount/size + 1);
        if(pageId>totalPage){
            pageId = totalPage;
        }
        //当前页是(因为之前的可能改变了)
        CurrentPageId = pageId;

        pages.add ( pageId );
        for(int i=1;i<=3;i++){
            if(pageId-i>0){
                pages.add ( 0,pageId-i );
            }
            if(pageId+i<=totalPage){
                pages.add ( pageId+i );
            }
        }

        //判断显示上一页
        if(pageId == 1){
            isPrevious = false;
        }else{
            isPrevious = true;
        }

        //判断显示下一页
        if(pageId == totalPage){
            isNext = false;
        }else{
            isNext = true;
        }

        //判断显示回到第一页
        if(pages.contains (1)){
            isBackFirstPage = false;
        }else {
            isBackFirstPage = true;
        }

        //判断显示回到最后一页
        if(pages.contains ( totalPage )){
            isBackEndPage = false;
        }else{
            isBackEndPage = true;
        }
    }
}
