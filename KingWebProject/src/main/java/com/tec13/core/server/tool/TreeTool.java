package com.tec13.core.server.tool;

import com.tec13.core.server.domain.NodeDomain;
import com.tec13.etltool.blooddepend.ScheduleNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeTool {
    public static List<NodeDomain> compositeTree(List<NodeDomain> nodelist){
        List<NodeDomain> rootList =new ArrayList<>();
        compositeTree(rootList,nodelist);
        return rootList;
    }

    public static void compositeTree(List<NodeDomain> parentList,List<NodeDomain> nodelist){
        Iterator<NodeDomain> iter = nodelist.iterator();
        List<NodeDomain> nextLevelParent = new ArrayList<>();
        boolean isCompositeRoot=parentList.isEmpty();
        while(iter.hasNext()){
            NodeDomain cur = iter.next();
            //父列表为空代表第一次遍历取root节点
            if(isCompositeRoot && cur.isRoot()){
                cur.setLevel(0);
                nextLevelParent.add(cur);
                iter.remove();
            }else{
                for (NodeDomain pNode : parentList) {
                    System.out.println(pNode);
                    System.out.println(cur);
                    if(pNode.getId().equals(cur.getParentId())){
                        cur.setLevel(pNode.getLevel()+1);
                        pNode.addChild(cur);
                        iter.remove();

                        nextLevelParent.add(cur);
                    }
                }
            }
        }

        if(isCompositeRoot){
            parentList.addAll(nextLevelParent);
        }
        if(!nextLevelParent.isEmpty()){
            compositeTree(nextLevelParent,nodelist);
        }
    }

    public static void main(String[] args){
        List<NodeDomain> nodelist = new ArrayList<>();
        ScheduleNode r1 = new ScheduleNode();
        r1.setName("r1");
        r1.setId(0l);
//        r1.setParentId();
        nodelist.add(r1);

        ScheduleNode c1 = new ScheduleNode();
        c1.setName("c1");
        c1.setParentId(0l);
        c1.setId(1l);
        nodelist.add(c1);

        ScheduleNode c11 = new ScheduleNode();
        c11.setName("c11");
        c11.setParentId(1l);
        c11.setId(11l);
        nodelist.add(c11);

        ScheduleNode c111 = new ScheduleNode();
        c111.setName("c111");
        c111.setParentId(11l);
        c111.setId(111l);
        nodelist.add(c111);

        ScheduleNode c1111 = new ScheduleNode();
        c1111.setName("c1111");
        c1111.setParentId(111l);
        c1111.setId(1111l);
        nodelist.add(c1111);

        ScheduleNode c11111 = new ScheduleNode();
        c11111.setName("c11111");
        c11111.setParentId(1111l);
        c11111.setId(11111l);
        nodelist.add(c11111);

        ScheduleNode c111111 = new ScheduleNode();
        c111111.setName("c111111");
        c111111.setParentId(11111l);
        c111111.setId(111111l);
        nodelist.add(c111111);

        ScheduleNode c2 = new ScheduleNode();
        c2.setName("c2");
        c2.setParentId(0l);
        c2.setId(2l);
        nodelist.add(c2);


        ScheduleNode c21 = new ScheduleNode();
        c21.setName("c21");
        c21.setParentId(2l);
        c21.setId(21l);
        nodelist.add(c21);

        ScheduleNode c22 = new ScheduleNode();
        c22.setName("c22");
        c22.setParentId(2l);
        c22.setId(22l);
        nodelist.add(c22);

        ScheduleNode c221 = new ScheduleNode();
        c221.setName("c221");
        c221.setParentId(22l);
        c221.setId(221l);
        nodelist.add(c221);

        ScheduleNode c222 = new ScheduleNode();
        c222.setName("c222");
        c222.setParentId(22l);
        c222.setId(222l);
        nodelist.add(c222);

        ScheduleNode c223 = new ScheduleNode();
        c223.setName("c223");
        c223.setParentId(22l);
        c223.setId(223l);
        nodelist.add(c223);

        ScheduleNode c2231 = new ScheduleNode();
        c2231.setName("c2231");
        c2231.setParentId(223l);
        c2231.setId(2231l);
        nodelist.add(c2231);

        ScheduleNode c22311 = new ScheduleNode();
        c22311.setName("c22311");
        c22311.setParentId(2231l);
        c22311.setId(22311l);
        nodelist.add(c22311);

        ScheduleNode c223111 = new ScheduleNode();
        c223111.setName("c223111");
        c223111.setParentId(22311l);
        c223111.setId(223111l);
        nodelist.add(c223111);

        ScheduleNode c223112 = new ScheduleNode();
        c223112.setName("c223112");
        c223112.setParentId(22311l);
        c223112.setId(223112l);
        nodelist.add(c223112);

        ScheduleNode c223113 = new ScheduleNode();
        c223113.setName("c223113");
        c223113.setParentId(22311l);
        c223113.setId(223113l);
        nodelist.add(c223113);


        ScheduleNode r2= new ScheduleNode();
        r2.setName("r12");
        r2.setId(10l);
//        r1.setParentId();
        nodelist.add(r2);

        List<NodeDomain> rootList = compositeTree(nodelist);
        System.out.println(rootList);
    }
}
