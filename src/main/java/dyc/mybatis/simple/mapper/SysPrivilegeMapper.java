package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.SysPrivilege;

import java.util.List;

public interface SysPrivilegeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysPrivilege record);

    int insertSelective(SysPrivilege record);

    SysPrivilege selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysPrivilege record);

    int updateByPrimaryKey(SysPrivilege record);

    List<SysPrivilege> findPrivilegeByRoleId(long l);
}