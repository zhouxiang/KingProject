package com.tec13.core.server.domain;

import java.util.ArrayList;
import java.util.List;

public class NodeDomain extends BaseDomain{
    private Long parentId;
    private NodeDomain parent;
    private List<NodeDomain> children;
    //深度，默认-1，不处理。
    private int level=-1;
    public NodeDomain(){
        children = new ArrayList<>();
    }
    public NodeDomain addChild(NodeDomain c){
        children.add(c);
        return this;
    }

    public NodeDomain setParent(NodeDomain c){
        parent = c;
        return this;
    }

    public boolean isLeaf(){
        return children.isEmpty();
    }

    public boolean isRoot(){
        return parentId == null;
    }

    public void setChildren(List<NodeDomain> children) {
        this.children = children;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public NodeDomain getParent() {
        return parent;
    }

    public List<NodeDomain> getChildren() {
        return children;
    }

    public int getLevel() {
        return level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "NodeDomain{" +
                "parentId=" + parentId +
                ", parent=" + parent +
                ", children=" + children +
                ", level=" + level +
                '}';
    }
}
