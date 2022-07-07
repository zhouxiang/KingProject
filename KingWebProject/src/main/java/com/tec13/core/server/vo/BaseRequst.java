package com.tec13.core.server.vo;

public class BaseRequst<D> {
    private D body;
    private PageInfo pageInfo = new PageInfo();


    public D getBody() {
        return body;
    }

    public void setBody(D body) {
        this.body = body;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
