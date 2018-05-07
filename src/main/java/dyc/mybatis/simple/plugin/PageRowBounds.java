package dyc.mybatis.simple.plugin;

import org.apache.ibatis.session.RowBounds;

/**
 * @author dengyichao
 * @Title: PageRowBounds
 * @Package: dyc.mybatis.simple.plugin
 * @Description:
 * @date 2018/5/7  14:17
 */
public class PageRowBounds extends RowBounds {
    private long total;

    public PageRowBounds() {
        super();
    }

    public PageRowBounds(int offset, int limit) {
        super(offset, limit);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
