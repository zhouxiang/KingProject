package com.tec13.generator.web.genobj;

import com.squareup.javapoet.*;
import com.tec13.core.server.repository.BaseRepository;
import com.tec13.core.server.service.BaseService;
import com.tec13.core.server.vo.BaseRequst;
import com.tec13.core.server.vo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.lang.model.element.Modifier;
import java.util.List;

public class ServiceGenerator extends BaseGenerator {

    public ServiceGenerator(Class<?> domainClazz) {
        super(domainClazz,"service");
    }

    public ClassName getDomainRepositoryClassName(){
        return ClassName.get(getBasePacke()+DOT+"repository",domainClazz.getSimpleName()+"Repository");
    }
    public ClassName getDomainVoClassName(){
        return ClassName.get(getBasePacke()+DOT+"vo",domainClazz.getSimpleName()+"Vo");
    }

    @Override
    protected JavaFile generate()  throws Exception{

        ClassName  idTypeName = ClassName.get(Long.class);
        ClassName  domainTypeName = ClassName .get(domainClazz);
        ClassName  voTypeName =getDomainVoClassName();
        ParameterizedTypeName baseType = ParameterizedTypeName.get(ClassName.get(BaseService.class),domainTypeName,voTypeName, idTypeName);

        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(domainClazz.getSimpleName()+convertCamelName(genrateType))
                .superclass(baseType)
                .addAnnotation(Service.class)
                .addModifiers(Modifier.PUBLIC);

        FieldSpec domainRepositoryField = FieldSpec.builder(getDomainRepositoryClassName(),getDomainRepositoryClassName().simpleName())
                .addModifiers(Modifier.PRIVATE)
                .addAnnotation(Autowired.class)
                .build();
        typeBuilder.addField(domainRepositoryField);
//
//        ParameterizedTypeName getRepositoryReturnType = ParameterizedTypeName.get(ClassName.get(BaseRepository.class), domainTypeName, idTypeName);

        MethodSpec getRepositoryMethod = MethodSpec.methodBuilder("getRepository")
                .returns(ClassName.get(BaseRepository.class))
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return "+domainRepositoryField.name)
                .build();


        typeBuilder.addMethod(getRepositoryMethod);

        ParameterizedTypeName domainListType = ParameterizedTypeName.get(ClassName.get(List.class),domainTypeName);
        ParameterizedTypeName reutnType = ParameterizedTypeName.get(ClassName.get(BaseResult.class),domainListType);

        ParameterSpec parametterSpec = ParameterSpec.builder(ParameterizedTypeName.get(ClassName.get(BaseRequst.class),voTypeName),"req").build();
        MethodSpec findByExampleExMethod = MethodSpec.methodBuilder("findByExampleEx")
                .returns(reutnType)
                .addParameter(parametterSpec)
                .addModifiers(Modifier.PUBLIC)
                .addStatement("return "+"findByExample(req, Q"+domainTypeName.simpleName()+"."+convertCamelNameLower(domainTypeName.simpleName())+")")
                .build();


        typeBuilder.addMethod(findByExampleExMethod);

        String servicePackageName = getBasePacke()+DOT+genrateType.toLowerCase();
        JavaFile javaFile = JavaFile.builder(servicePackageName,typeBuilder.build())
                .build();
        javaFile.writeTo(System.out);
        return javaFile;
    }
}
