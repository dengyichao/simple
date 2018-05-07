package dyc.mybatis.simple.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;

/**
 * @author dengyichao
 * @Title: Dialect
 * @Package: dyc.mybatis.simple.interfaces
 * @Description:
 * @date 2018/5/7  11:28
 */
@SuppressWarnings("rawtypes")
public interface Dialect {
    /**
     *
     * @param msId 执行的MyBatis 方法全名
     * @param parameterObject 方法参数
     * @param rowBounds 分页参数
     * @return true 跳过，返回默认查询结采， false 则执行分页查询
     */
    boolean skip (String msId , Object parameterObject , RowBounds rowBounds) ;

    /**
     * 执行分页前，返回true 会进行count 查询，返回false 会继续下面的b eforePage 判断
     *
     * @param msId 执行的MyBatis 方法会名
     * @param parameterObject 方法参数
     * @param rowBounds 分页参数
     * @return
     */
    boolean beforeCount( String msId , Object parameterObject , RowBounds rowBounds);

    /**
     *  生成count查询sql
     * @param boundSql 绑定SQL 对象
     * @param parameterObject 方法参数
     * @param rowBounds 分页参数
     * @param countKey count 缓存key
     * @return
     */
    String getCountSql(BoundSql boundSql , Object parameterObject , RowBounds rowBounds , CacheKey countKey );

    /**
     * 执行完count 查询后
     * @param count 查询结采总数
     * @param parameterObject 接口参数
     * @param rowBounds 分页参数
     */
    void afterCount(long count , Object parameterObject , RowBounds rowBounds);

    /**
     *  执行分页前，返回true 会进行分页查询，返回false 会返回默认查询结果
     * @param msId 执行的MyB拭目方法会名
     * @param parameterObject 方法参数
     * @param rowBounds 分页参数
     * @return
     */
    boolean beforePage (String msId, Object parameterObject, RowBounds rowBounds);

    /**
     *  生成分页查询sql
     * @param boundSql  绑定SQL 对象
     * @param parameterObject 方法参数
     * @param rowBounds 分页参数
     * @param pageKey 分页缓存key
     * @return
     */
    String getPageSql (BoundSql boundSql , Object parameterObject , RowBounds rowBounds , CacheKey pageKey) ;

    /**
     *  分页查询后，处理分页结采，拦截器中直接return 该方法的返回值
     * @param pageList  分页查询结果
     * @param parameterObject   方法参数
     * @param rowBounds 分页参数
     * @return
     */
    Object afterPage(List pageList, Object parameterObject , RowBounds rowBounds );

    /**
     *  设置参数
     * @param properties 插件属性
     */
    void setProperties(Properties properties) ;
}
