package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.SysUserRole;

public interface SysUserRoleMapper {
    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);
}