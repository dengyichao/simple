package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.SysRole;
import dyc.mybatis.simple.model.SysUser;
import dyc.mybatis.simple.model.SysUserWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {
    SysUserWithBLOBs findById(Long id);

    /**
     * 根据用户id获取用户角色
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);

    /**
     * 根据用户id 和角色的enabled 状态获取用户的角色
     * @param userId
     * @param enabled
     * @return
     */
    List<SysRole> findRolesByUserIdAndRoleEnabled(@Param("userId") Long userId, @Param("enabled") Integer enabled);

    List<SysUserWithBLOBs> findByUser(SysUserWithBLOBs record);

    /**
     * 根据用户id 获取用户信息和用户的角色信息
     * @param id
     * @return
     */
    SysUserWithBLOBs findUserAndRoleById(Long id);

    SysUserWithBLOBs findUserAndRoleById2(long l);

    SysUserWithBLOBs findUserAndRoleByIdSelect(Long id);

    SysUserWithBLOBs findByIdOrUserName(SysUserWithBLOBs record);

    List<SysUserWithBLOBs> findByIdList(List<Long> idList);

    int updateByMap();

    int insertByKey(SysUserWithBLOBs record);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserWithBLOBs record);

    int insertSelective(SysUserWithBLOBs record);

    SysUserWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysUserWithBLOBs record);

    int updateByPrimaryKey(SysUser record);

    int insert3(SysUserWithBLOBs user);


    SysUserWithBLOBs findUserAndRoleListById(long l);

    List<SysUserWithBLOBs> findUserAndRoleListAll();

    List<SysUserWithBLOBs> findAllUserAndRoles();

    SysUserWithBLOBs findRoleAndUserByUserId(long l);
}