package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.SysRole;
import dyc.mybatis.simple.model.SysUserWithBLOBs;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

/**
 * @author dengyichao
 * @Title: CacheMapperTest
 * @Package: dyc.mybatis.simple.mapper
 * @Description:
 * @date 2018/4/28  9:41
 */
public class CacheMapperTest {
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


    /**
     *  一级缓存的作用
     */
    @Test
    public void testL1Cache1(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserWithBLOBs user = null;
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            user = sysUserMapper.findById(1L);
            user.setUserName("New Name");
            SysUserWithBLOBs user2 = sysUserMapper.findById(1L);
            //虽然没有更新数据库，但是这个用户名和user 重新赋值的名字相同
            Assert.assertEquals("New Name",user2.getUserName());
            // 无论如何，user2 和user完全就是同一个实例
            Assert.assertEquals(user,user2);
        }finally {
            sqlSession.close();
        }

        System.out.println("开启一个新的session");

        sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            //调用findById方法，查询id = 1 的用户
            SysUserWithBLOBs user2 = sysUserMapper.findById(1L);
            //第二个session 获取的用户名仍然是admin
            Assert.assertNotEquals("New Name",user2.getUserName());
            //不同的实例
            Assert.assertNotEquals(user,user2);
            /**
             * 执行了一个deleteByid 操作，然后使用相同的方法和参数获取了user3 实例，
             从日志和结果来看， user3 和user2 也是完全不同的两个对象。这是因为任何的INSERT 、
             UPDATE 、DELET E 操作都会清空一级缓存，所以查询user3 的时候由于缓存不存在，就会再
             次执行数据库查询获取数据。
             */
            sysUserMapper.deleteByPrimaryKey(2L);
            SysUserWithBLOBs user3 = sysUserMapper.findById(1L);
            //这里的user2和user3是两个不同的实例
            Assert.assertNotEquals(user2 , user3);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * 二级缓存
     */
    @Test
    public void testL2Cache1(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRole role = null;
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            role = sysRoleMapper.selectByPrimaryKey(1L);
            role.setRoleName ("New Name") ;
            SysRole role2 = sysRoleMapper.selectByPrimaryKey(1L);
            Assert.assertEquals("New Name",role2.getRoleName());
            Assert.assertEquals(role,role2);
        }finally {
            sqlSession.close();
        }

        sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole role2 = sysRoleMapper.selectByPrimaryKey(1L);
            // 第二个session 获取的用户名是New Name
            Assert.assertEquals("New Name",role2.getRoleName());
            // 这里的role2 和前一个session 查询的结果是两个不同的实例
            Assert.assertNotEquals(role,role2);
            // 获取role3
            SysRole role3 = sysRoleMapper.selectByPrimaryKey(1L);
            // 这里的role2和role3是两个不同的实例
            Assert.assertNotEquals(role2 , role3) ;
        }finally {
            sqlSession.close();
        }
    }


    /**
     * 使用EhCache缓存框架后
     */
    @Test
    public void tesEhCache1(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRole role = null;
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            role = sysRoleMapper.selectByPrimaryKey(1L);
            role.setRoleName ("New Name") ;
            SysRole role2 = sysRoleMapper.selectByPrimaryKey(1L);
            Assert.assertEquals("New Name",role2.getRoleName());
            Assert.assertEquals(role,role2);
        }finally {
            sqlSession.close();
        }

        sqlSession = sqlSessionFactory.openSession();
        try {
            SysRoleMapper sysRoleMapper = sqlSession.getMapper(SysRoleMapper.class);
            SysRole role2 = sysRoleMapper.selectByPrimaryKey(1L);
            // 第二个session 获取的用户名是New Name
            Assert.assertEquals("New Name",role2.getRoleName());
            // 这里的role2 和前一个session 查询的结果是两个不同的实例
            Assert.assertNotEquals(role,role2);
            // 获取role3
            SysRole role3 = sysRoleMapper.selectByPrimaryKey(1L);
            // 这里的role2和role3是两个不同的实例
            Assert.assertNotEquals(role2 , role3) ;
        }finally {
            sqlSession.close();
        }
    }
}
