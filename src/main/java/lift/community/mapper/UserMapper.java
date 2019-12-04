package lift.community.mapper;

import lift.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
@Mapper
@Component
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modift) values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
