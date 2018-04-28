package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.SysRolePrivilege;

public interface SysRolePrivilegeMapper {
    int insert(SysRolePrivilege record);

    int insertSelective(SysRolePrivilege record);
}