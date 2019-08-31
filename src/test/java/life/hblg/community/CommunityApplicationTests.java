package life.hblg.community;

import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@MapperScan(basePackages = "com.hblg.community.mapper")
public class CommunityApplicationTests {


    @Test
    public void contextLoads() {

    }

}
