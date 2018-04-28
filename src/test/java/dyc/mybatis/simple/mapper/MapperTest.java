package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.Country;
import dyc.mybatis.simple.model.SysPrivilege;
import dyc.mybatis.simple.model.SysRole;
import dyc.mybatis.simple.model.SysUserWithBLOBs;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.management.relation.Role;
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

public class MapperTest {
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
    public void testFindAll(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<Country> list = sqlSession.selectList("findAll");
//            list.forEach(e -> System.out.println(e));
            Assert.assertEquals(5,list.size());
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void test2() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
        SysUserWithBLOBs sysUserWithBLOBs = sysUserMapper.findById(1l);
        System.out.println(sysUserWithBLOBs);
        sqlSession.close();
    }


    @Test
    public void testFindRolesByUserId() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysRole> list = sysUserMapper.findRolesByUserId(1L);
        list.forEach(e -> System.out.println(e));
        sqlSession.close();
    }

    @Test
    public void testFindRolesByUserIdAndRoleEnabled() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysRole> list = sysUserMapper.findRolesByUserIdAndRoleEnabled(1L,1);
        list.forEach(e -> System.out.println(e));
        sqlSession.close();
    }

    @Test
    public void testFindByUser() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
        SysUserWithBLOBs user = new SysUserWithBLOBs();
        user.setUserName("ad");
        user.setUserEmail("admin@mybatis.dyc");
        List<SysUserWithBLOBs> list = sysUserMapper.findByUser(user);
        list.forEach(e -> System.out.println(e));
        sqlSession.close();
    }
    @Test
    public void findByIdOrUserNameTest() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
        SysUserWithBLOBs user = new SysUserWithBLOBs();
        user.setId(1L);
        SysUserWithBLOBs userInfo = sysUserMapper.findByIdOrUserName(user);
        System.out.println(userInfo);
        sqlSession.close();
    }

    @Test
    public void findByIdListTest() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<Long> idList = Arrays.asList(1L,1001L);
            List<SysUserWithBLOBs> list = sysUserMapper.findByIdList(idList);
            Assert.assertEquals(2,list.size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
       try {
           SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
           SysUserWithBLOBs user = new SysUserWithBLOBs();
           user.setUserName("test1");
           user.setUserPassword("1234567");
           user.setUserEmail("test@mybatis.dyc");
           user.setUserInfo("test info");
           //正常来说应该是图片
           user.setHeadImg(new byte[]{1,2,3});
           user.setCreateTime(new Date());
           int result = sysUserMapper.insert(user);
           Assert.assertEquals(1,result);
           Assert.assertNull(user.getId());
       } finally {
           sqlSession.rollback();
           sqlSession.close();
       }
    }

    @Test
    public void testInsert2() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUserWithBLOBs user = new SysUserWithBLOBs();
            user.setUserName("test1");
            user.setUserPassword("1234567");
            user.setUserEmail("test@mybatis.dyc");
            user.setUserInfo("test info");
            //正常来说应该是图片
            user.setHeadImg(new byte[]{1,2,3});
            user.setCreateTime(new Date());
            int result = sysUserMapper.insert3(user);
            System.out.println(user.getId());
            Assert.assertEquals(1,result);
        } finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void findUserAndRoleById(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUserWithBLOBs user = sysUserMapper.findUserAndRoleById(1001L);
            Assert.assertNotNull(user);
//            System.out.println(user.getSysRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void findUserAndRoleById2(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUserWithBLOBs user = sysUserMapper.findUserAndRoleById2(1001L);
            Assert.assertNotNull(user);
            System.out.println(user.getUserEmail());
//            System.out.println(user.getSysRole());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void findUserAndRoleByIdSelectTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUserWithBLOBs user = sysUserMapper.findUserAndRoleByIdSelect(1001L);
            Assert.assertNotNull(user);
            System.out.println(user.getUserEmail());
            //实现懒加载，如果不getSysRole不会执行第二条查询语句，具体可以去xml看
//            System.out.println(user.getSysRole());
        }finally {
            sqlSession.close();
        }
    }
    @Test
    public void findUserAndRoleListByIdTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            SysUserWithBLOBs user = sysUserMapper.findUserAndRoleListById(1L);
            Assert.assertNotNull(user);
//            Assert.assertEquals(2,user.getRoleList().size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void findUserAndRoleListAllTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUserWithBLOBs> list = sysUserMapper.findUserAndRoleListAll();
            Assert.assertEquals(2,list.size());
//            Assert.assertEquals(2,user.getRoleList().size());
        }finally {
            sqlSession.close();
        }
    }

    /**
     *
        这里要特别注意sys_privilege 表中列的别名，因为sys_privilege 嵌套在
        rolePrivilegeListMap 中，而rolePrivilegeListMap 的前缀是“ role_”，所以
        rolePrivilegeListMap中sysPrivilegeList的前缀就变成了“role_privilege_,在嵌套
        中，这个前缀需要叠加， 一定不要写错
     */
    @Test
    public void findAllUserAndRolesTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            List<SysUserWithBLOBs> list = sysUserMapper.findAllUserAndRoles();

            Assert.assertEquals(2,list.size());
//            Assert.assertEquals(2,user.getRoleList().size());
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void findRoleAndUserByUserIdTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            SysUserMapper sysUserMapper = sqlSession.getMapper(SysUserMapper.class);
            //根据日志很显然能看出来懒加载成功
            SysUserWithBLOBs user = sysUserMapper.findRoleAndUserByUserId(1L);
            System.out.println("用户名 ： " + user.getUserName());
            System.out.println("  拥有的权限角色");
            List<SysRole> sList = user.getRoleList();
            sList.forEach(e->{
                System.out.println("  "+e.getRoleName());
                System.out.println("    角色所拥有的权限");
                List<SysPrivilege> pList = e.getSysPrivilegeList();
                pList.forEach(a -> {
                    System.out.println("                "+a.getPrivilegeName() );
                });
            });
            Assert.assertNotNull(user);
//            Assert.assertEquals(2,user.getRoleList().size());
        }finally {
            sqlSession.close();
        }
    }




}
