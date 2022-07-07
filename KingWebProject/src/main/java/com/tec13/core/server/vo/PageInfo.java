package com.tec13.core.server.vo;

public class PageInfo {
    @KingIgnore
    private Long page = 0l;
    @KingIgnore
    private Long pageSize = 20l;

    public Long getOffset(){
        return page * pageSize;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
