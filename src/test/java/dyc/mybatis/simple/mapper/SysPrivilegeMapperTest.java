package dyc.mybatis.simple.mapper;

import dyc.mybatis.simple.model.SysPrivilege;
import dyc.mybatis.simple.model.SysRole;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author dengyichao
 * @Title: CountryMapperTest
 * @Package: dyc.mybatis.simple.mapper
 * @Description:
 * @date 2018/4/25  15:11
 */

public class SysPrivilegeMapperTest {
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
            SysPrivilegeMapper sysPrivilegeMapper = sqlSession.getMapper(SysPrivilegeMapper.class);
            List<SysPrivilege> list = sysPrivilegeMapper.findPrivilegeByRoleId(1L);
            Assert.assertNotNull(list);
            Assert.assertEquals(3,list.size());
        }finally {
            sqlSession.close();
        }
    }
}
