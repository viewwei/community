package lift.community.mapper;

import lift.community.Model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface QuesstionMapper {
    @Insert(("insert into question(title,description,gmt_create,gmt_modified,creator,tag)" +
            "values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})"))
      void  pushishQuestion(Question question);
    @Select("select * from question")
    List<Question> list();
}
