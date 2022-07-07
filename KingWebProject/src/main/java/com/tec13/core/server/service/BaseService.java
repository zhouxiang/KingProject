package com.tec13.core.server.service;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.tec13.core.server.domain.BaseDomain;
import com.tec13.core.server.repository.BaseRepository;
import com.tec13.core.server.vo.*;
import com.tec13.generator.web.genobj.VoGenerator;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Optional;


public abstract class BaseService<D extends BaseDomain,V extends BaseVo, ID>  {
    private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
    @Autowired
    private EntityManager entityManager;
    protected abstract BaseRepository<D,ID> getRepository();


    public BaseResult<D> findById(ID id){
        Optional<D> colId = getRepository().findById(id);
        return BaseResult.success(colId.isPresent()?colId.get():null);
    }

    public BaseResult<D> upsert(D d){
        D save = getRepository().save(d);
        return BaseResult.success(d);
    }

    public BaseResult<D> deleteById(ID id){
        getRepository().deleteById(id);
        return BaseResult.success();
    }

    public BaseResult<Iterable<D>> findAll(){
        Iterable<D> all = getRepository().findAll();
        return BaseResult.success(all);
    }

//    public BaseResult<Iterable<D>> findByExample(V vo){
//        Iterable<D> all = getRepository().findAll();
//        return BaseResult.success(all);
//    }

    public BaseResult<List<D>> findByExample(BaseRequst<V>  req, EntityPathBase<D> queryObj){
        JPAQuery<D> query = new JPAQuery<>(entityManager);

        V vo = req.getBody();
        PageInfo pageInfo = req.getPageInfo();
        query.limit(pageInfo.getPageSize()).offset(pageInfo.getOffset());
        List<D> result = (List<D>)  constructEampleQuery(query, vo, queryObj).fetch();
        return BaseResult.success(result);
    }


    protected JPAQuery<?> constructEampleQuery(JPAQuery<?> query, V vo, EntityPathBase<D> queryObj)  {
        try {
//            query.from(queryObj);
           MethodUtils.invokeMethod(query, "from", queryObj);
            List<Field> allFieldsList = FieldUtils.getAllFieldsList(vo.getClass());
            for (Field f : allFieldsList) {
                f.setAccessible(true);
                Object fValue = f.get(vo);

                if (fValue != null) {

                    Object qReusltObj = null;
                    if(isRangeField(f)){
                        String fName = f.getName();
                        String originName = fName.replace(VoGenerator.POSTFIX_VO_START,"")
                                .replace(VoGenerator.POSTFIX_VO_END,"");
                        Field originField = FieldUtils.getField(queryObj.getClass(), originName);
                        Object qFieldObj = originField.get(queryObj);

                        if(isRangeStartField(f)){
                            qReusltObj = MethodUtils.invokeMethod(qFieldObj, "after", fValue);
                        }else if(isRangeEndField(f)){
                            qReusltObj = MethodUtils.invokeMethod(qFieldObj, "before", fValue);
                        }
                    }else{
                        Field qField = FieldUtils.getField(queryObj.getClass(), f.getName());
                        if(!isIgnoreFieldInVo(f.getName())){
                            Object qFieldObj = qField.get(queryObj);
                            qReusltObj = MethodUtils.invokeMethod(qFieldObj, "eq", fValue);
                        }
                    }
                    MethodUtils.invokeMethod(query, "where", qReusltObj);
                }
            }
        }catch (Exception e){
            logger.error("constructEampleQuery error!",e);
        }
        return query;
    }

    private boolean isRangeField(Field f){
        return isRangeStartField(f) || isRangeEndField(f);
    }
    private boolean isRangeStartField(Field f){
        return f.getName().contains(VoGenerator.POSTFIX_VO_START) ;
    }
    private boolean isRangeEndField(Field f){
        return  f.getName().contains(VoGenerator.POSTFIX_VO_END);
    }
    private boolean isIgnoreFieldInVo(String fieldName){
        Class<?> voClass = getVoClass();
        Field field = null;
        boolean isIgnore = false;
        try {

            field =  voClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            isIgnore = field.getAnnotation(KingIgnore.class) != null;
        } catch (Exception e) {
            //没有找到该字段就不处理
            logger.error("isIgnoreFieldInVo",e);
        }
        return isIgnore;
    }

    private Class<?> getVoClass()  {
        TypeVariable<? extends Class<?>>[] typeParameters = this.getClass().getSuperclass().getTypeParameters();
        TypeVariable<? extends Class<?>> voType = typeParameters[1];
        try {
            return Class.forName(voType.getBounds()[0].getTypeName());
        } catch (ClassNotFoundException e) {
           throw  new IllegalStateException(e);
        }
    }
}
