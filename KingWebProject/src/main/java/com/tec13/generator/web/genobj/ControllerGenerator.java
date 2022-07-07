package com.tec13.generator.web.genobj;

import com.squareup.javapoet.*;
import com.tec13.core.server.controller.BaseController;
import com.tec13.core.server.service.BaseService;
import com.tec13.core.server.vo.BaseRequst;
import com.tec13.core.server.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.lang.model.element.Modifier;
import java.util.List;

public class ControllerGenerator extends BaseGenerator {

    public ControllerGenerator(Class<?> domainClazz) {
        super(domainClazz,"controller");
    }

    public ClassName getDomainServiceClassName(){
        return ClassName.get(getBasePacke()+DOT+"controller",domainClazz.getSimpleName()+"Service");
    }
    public ClassName getDomainVoClassName(){
        return ClassName.get(getBasePacke()+DOT+"vo",domainClazz.getSimpleName()+"Vo");
    }

    @Override
    protected JavaFile generate()  throws Exception{

        ClassName  idTypeName = ClassName.get(Long.class);
        ClassName  domainTypeName = ClassName .get(domainClazz);
        ClassName  voTypeName =getDomainVoClassName();
        ParameterizedTypeName baseType = ParameterizedTypeName.get(ClassName.get(BaseController.class),domainTypeName,voTypeName);


        AnnotationSpec controllerAnno = AnnotationSpec.builder(RequestMapping.class).addMember("value","$S","/"+domainTypeName.simpleName().toLowerCase()).build();

        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(domainClazz.getSimpleName()+convertCamelName(genrateType))
                .superclass(baseType)
                .addAnnotation(Controller.class)
                .addAnnotation(controllerAnno)
                .addModifiers(Modifier.PUBLIC);

        FieldSpec domainServiceField = FieldSpec.builder(getDomainServiceClassName(),"service")
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(Autowired.class)
                .build();
        typeBuilder.addField(domainServiceField);
//
//        ParameterizedTypeName getRepositoryReturnType = ParameterizedTypeName.get(ClassName.get(BaseRepository.class), domainTypeName, idTypeName);

        MethodSpec getRepositoryMethod = MethodSpec.methodBuilder("getService")
                .returns(ClassName.get(BaseService.class))
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return "+domainServiceField.name)
                .build();
        typeBuilder.addMethod(getRepositoryMethod);

        ParameterizedTypeName domainListType = ParameterizedTypeName.get(ClassName.get(List.class),domainTypeName);
        ParameterizedTypeName searchReturnType = ParameterizedTypeName.get(ClassName.get(BaseResult.class),domainListType);
        ParameterSpec  searchMethodParameterSpec= ParameterSpec.builder(ParameterizedTypeName.get(ClassName.get(BaseRequst.class),voTypeName),
                        "req").build();

        AnnotationSpec searchMethodAnno = AnnotationSpec.builder(RequestMapping.class).addMember("value","$S","/search").build();

        MethodSpec searchMethod = MethodSpec.methodBuilder("search")
                .returns(searchReturnType)
                .addAnnotation(ResponseBody.class)
                .addAnnotation(searchMethodAnno)
                .addParameter(searchMethodParameterSpec)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return "+domainServiceField.name+".findByExampleEx(req)")
                .build();
        typeBuilder.addMethod(searchMethod);

        String servicePackageName = getBasePacke()+DOT+genrateType.toLowerCase();
        JavaFile javaFile = JavaFile.builder(servicePackageName,typeBuilder.build())
                .build();
        javaFile.writeTo(System.out);
        return javaFile;
    }
}
