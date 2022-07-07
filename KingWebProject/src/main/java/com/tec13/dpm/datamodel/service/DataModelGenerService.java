package com.tec13.dpm.datamodel.service;

import com.tec13.dpm.datamodel.domain.BaseDataModel;
import com.tec13.dpm.datamodel.domain.DlkDatamodel;
import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.ArrayList;
import java.util.List;

public class DataModelGenerService {
    public void generDlkDatamodel(BaseDataModel dlkData){
        String result = genModelDdl(dlkData);
        System.out.println(result);
    }

    private String genModelDdl(BaseDataModel model){
        TemplateEngine tEngine= new TemplateEngine();
//        FileTemplateResolver fileRevolver = new FileTemplateResolver();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("datamodel/hive/");
        templateResolver.setSuffix(".tpl");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCacheable(true);
        tEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("model",model);
        String templateName = model.getClass().getSimpleName();
        switch (model.getClass().getSimpleName()){
            case "DlkDatamodel": templateName="dlk_table_his";break;
            case "DwdDatamodel": templateName="dwd_table_gen_full";break;
        }
        return afterProcess(tEngine.process(templateName, context));
    }

    private String afterProcess(String result){
        String[] resultArr = result.split("\r\n");
        List<String> resultList = new ArrayList<>();
        for (String r : resultArr) {
            if(!StringUtils.isAllEmpty(r)){
                resultList.add(r);
            }
        }

        return StringUtils.join(resultList,"\r\n");
    }

}
