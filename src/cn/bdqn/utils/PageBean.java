package cn.bdqn.utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体
 * @param <T> 对实体T进行分页
 * @author 余晓文
 * @version 1.0 2018-12-08
 */
public class PageBean<T> implements Serializable {

    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 页面容量
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 当前页码
     */
    private int pageIndex;
    private List<T> list;

    public PageBean() {
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        if (totalCount<0){
            this.totalCount = 0;
        }else {
            this.totalCount = totalCount;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize<0){
            this.pageSize = 5;
        }else if (pageSize>totalCount){
            this.pageSize = this.totalCount;
        }else {
            this.pageSize = pageSize;
        }
        setPageCount();
    }

    public int getPageCount() {
        return pageCount;
    }

    private void setPageCount() {
        this.pageCount = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        if (pageIndex<0){
            this.pageIndex = 1;
        }else if (pageIndex>this.pageCount){
            this.pageIndex = this.pageCount;
        }else {
            this.pageIndex = pageIndex;
        }
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getStartRow(){
        return (pageIndex-1)*pageSize;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", pageIndex=" + pageIndex +
                ", list=" + list +
                '}';
    }
}
