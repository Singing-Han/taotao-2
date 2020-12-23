package cn.edu.sziit.pojo;

import java.io.Serializable;
import java.util.List;

public class TaoResult<T> implements Serializable {

    private long total; // 总的记录数
    private List<T> rows; // 当前这一页的数据集合

    public TaoResult() {
    }

    public TaoResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "TaoResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
