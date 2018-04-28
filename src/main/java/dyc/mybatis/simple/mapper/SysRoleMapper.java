package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.SysRole;
import org.apache.ibatis.annotations.CacheNamespaceRef;

import java.util.List;

//注解缓存和xml缓存只能引用一个，如果想同时支持两种，必须设置xml或注解类型的引用另一方的配置
//如这里引用了xml的缓存配置
@CacheNamespaceRef(SysRoleMapper.class)
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> findAllRoleAndPrivileges();

    List<SysRole> findRoleByUserId(long userId);
}