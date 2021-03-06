package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.Country;
import dyc.mybatis.simple.model.SysRole;
import dyc.mybatis.simple.model.SysUserWithBLOBs;
import dyc.mybatis.simple.plugin.PageRowBounds;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author dengyichao
 * @Title: CountryMapperTest
 * @Package: dyc.mybatis.simple.mapper
 * @Description:
 * @date 2018/4/25  15:11
 */

public class SysRoleMapperTest {
    private static SqlSessionFactory sqlSessionFactory;
    @BeforeClass
    public static void init(){
        try {
            Reader reader = Resources.getResourceAsReader ("mybatis-config.xml") ;
            sqlSessionFactory =new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch ( IOException ignore) {
            ignore.printStackTrace();
        }
    }
    @Test
    public void findAllRoleAndPrivilegesTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> list = sysRoleMapper.findAllRoleAndPrivileges();
            Assert.assertEquals(2,list.size());
//            Assert.assertEquals(2,user.getRoleList().size());
        }finally {
            sqlSession.close();
        }
    }


    @Test
    public void selectByPrimaryKeyTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole role = sysRoleMapper.selectByPrimaryKey(1L);
            Assert.assertNotNull(role);
//            Assert.assertEquals(2,user.getRoleList().size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void findRoleByUserIdTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            List<SysRole> list = sysRoleMapper.findRoleByUserId(1001L);
            Assert.assertEquals(1,list.size());
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 测试分页
     */
    @Test
    public void findAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            RowBounds rowBounds = new RowBounds(0,1);
            List<SysRole> list = sysRoleMapper.findAll(rowBounds);
            for (SysRole role : list){
                System.out.println("角色名：" + role.getRoleName());
            }
            //使用PageRowBounds会查询总数
            PageRowBounds pageRowBounds =new PageRowBounds(0, 1) ;
            list = sysRoleMapper.findAll(pageRowBounds);
            System.out.println("查询总数 ： " + pageRowBounds.getTotal());
            for (SysRole role : list){
                System.out.println("角色名：" + role.getRoleName());
            }
            //获取第二个角色
            pageRowBounds =new PageRowBounds(1, 1) ;
            list = sysRoleMapper.findAll(pageRowBounds);
            System.out.println("查询总数 ： " + pageRowBounds.getTotal());
            for (SysRole role : list){
                System.out.println("角色名：" + role.getRoleName());
            }
        }finally {
            sqlSession.close();
        }
    }
}
