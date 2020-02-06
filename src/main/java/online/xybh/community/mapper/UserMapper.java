package online.xybh.community.mapper;

import online.xybh.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: XYBH
 * @Description:
 * @Date: Created in 2020/2/6 0006 1:33
 * @Modified:
 */
@Mapper
@Repository
public interface UserMapper {
    @Insert("insert into user(name, account_id, token, gmt_create, gmt_modified, bio) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio})")
    void insert(User user);
}
