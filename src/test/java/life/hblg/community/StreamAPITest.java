package life.hblg.community;


import life.hblg.community.cache.TagCache;
import life.hblg.community.dto.TagDTO;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

public class StreamAPITest {

    /*迭代  forEach实现了消费者接口只接受T 然后返回void
    *无限流
    * limit是中间操作
    * forEach 是终止操作
    * 同时还结合了方法引用 类实例::方法名
    * */

    @Test
    public void test(){
        /*迭代数据*/
        Stream.iterate ( 0,t->t+2 ).limit ( 10 ).forEach ( System.out::println );
        /*生成  造数据*/
        Stream.generate ( Math::random ).limit ( 10 ).forEach ( System.out::println );


    }

    @Test
    public void test2(){

        /*查询目录名为生活日常的tagDTOS*/
        List<TagDTO> tagDTOS = TagCache.get();
        tagDTOS.stream ().filter ( t-> t.getCategoryName ().equals ( "生活日常" )).forEach ( System.out::println );

        /*查询tagDTOS第一个目录名的所有信息*/
       // tagDTOS.stream ().limit ( 1 ).forEach ( System.out::println );


        //和limit互补
        tagDTOS.stream ().skip ( 1 ).forEach ( System.out::println );

        //去重
        tagDTOS.stream ().distinct ().forEach ( System.out::println );
    }

}
