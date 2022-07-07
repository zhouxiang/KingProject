package com.tec13.core.server.controller;

import com.tec13.core.server.domain.BaseDomain;
import com.tec13.core.server.service.BaseService;
import com.tec13.core.server.vo.BaseResult;
import com.tec13.core.server.vo.BaseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public abstract class BaseController<D extends BaseDomain,V extends BaseVo> {
    protected abstract BaseService getService();

//    @GetMapping("/get")
//    @ResponseBody
//    public BaseResult<V> get(@RequestBody  D d){
//        return getService().findById(d.getId());
//    }
//
//    @GetMapping("/list")
//    @ResponseBody
//    public BaseResult<List<V>> list(@RequestBody  D d){
//        return getService().findAll();
//    }
//
//    @GetMapping("/del")
//    @ResponseBody
//    public BaseResult<V> del(@RequestBody  D d){
//        return getService().deleteById(d);
//    }

}
