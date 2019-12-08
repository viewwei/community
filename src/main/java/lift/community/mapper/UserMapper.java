package lift.community.mapper;
import lift.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modift,avatar_url) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{avatar_url})")
    void insert(User user);
    @Select("select * from user where token =#{token}")
    User findByToken(@Param("token") String token);
    @Select("select*from user where id =#{id}")
    User findByID(@Param("id") Integer id);
}
