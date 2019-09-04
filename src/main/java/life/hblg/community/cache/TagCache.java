package life.hblg.community.cache;

import life.hblg.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;
import sun.security.krb5.internal.ccache.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList <> (  );
        TagDTO life = new TagDTO ();
        life.setCategoryName ( "生活日常" );
        life.setTags ( Arrays.asList ( "二手生活用品","闲置","兼职招收") );
        tagDTOS.add ( life );

        TagDTO study = new TagDTO ();
        study.setCategoryName ( "学习" );
        study.setTags ( Arrays.asList ( "二手书","学习资料") );
        tagDTOS.add ( study );

        return  tagDTOS;
    }

    /*判断标签是否有效*/
    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect( Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> StringUtils.isBlank(t) || !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }

}
