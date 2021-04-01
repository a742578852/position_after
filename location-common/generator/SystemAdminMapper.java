package generator;

import com.justiceLeague.model.SystemAdmin;

public interface SystemAdminMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemAdmin record);

    int insertSelective(SystemAdmin record);

    SystemAdmin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemAdmin record);

    int updateByPrimaryKey(SystemAdmin record);
}