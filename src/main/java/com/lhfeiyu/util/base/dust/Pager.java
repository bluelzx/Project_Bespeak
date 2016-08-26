package com.lhfeiyu.util.base.dust;

/**
 * 分页参数类
 */
public class Pager {

    public static final int DEFAULT_PAGE_SIZE = 20;

    private int pageSize;
    private int currentLhPage;
    private int preLhPage;
    private int nextLhPage;
    private int totalLhPage;
    private int totalCount;

    public Pager() {
        this.currentLhPage = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    /**
     * 
     * @param currentLhPage
     * @param pageSize
     */
    public Pager(int currentLhPage, int pageSize) {
        this.currentLhPage = currentLhPage;
        this.pageSize = pageSize;
    }

    public int getCurrentLhPage() {
        return currentLhPage;
    }

    public void setCurrentLhPage(int currentLhPage) {
        this.currentLhPage = currentLhPage;
    }

    public int getLhPageSize() {
        return pageSize;
    }

    public void setLhPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPreLhPage() {
        return preLhPage;
    }

    public void setPreLhPage(int preLhPage) {
        this.preLhPage = preLhPage;
    }

    public int getNextLhPage() {
        return nextLhPage;
    }

    public void setNextLhPage(int nextLhPage) {
        this.nextLhPage = nextLhPage;
    }

    public int getTotalLhPage() {
        return totalLhPage;
    }

    public void setTotalLhPage(int totalLhPage) {
        this.totalLhPage = totalLhPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
