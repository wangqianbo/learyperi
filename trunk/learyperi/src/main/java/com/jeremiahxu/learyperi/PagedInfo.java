package com.jeremiahxu.learyperi;

/**
 * 分页信息
 * 
 * @author Jeremiah Xu
 * 
 */
public class PagedInfo {
    /**
     * 页码
     */
    private int pageNumber = 1;
    /**
     * 每页记录数
     */
    private int numberPerPage = 20;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getNumberPerPage() {
        return numberPerPage;
    }

    public void setNumberPerPage(int numberPerPage) {
        this.numberPerPage = numberPerPage;
    }

}
