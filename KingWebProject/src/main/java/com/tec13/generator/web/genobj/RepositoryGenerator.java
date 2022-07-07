package com.tec13.generator.web.genobj;

import com.squareup.javapoet.*;
import com.tec13.core.server.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.lang.model.element.Modifier;

public class RepositoryGenerator extends BaseGenerator {

    public RepositoryGenerator(Class<?> domainClazz) {
        super(domainClazz,"repository");
    }

    @Override
    protected JavaFile generate()  throws Exception{

        ClassName  idTypeName = ClassName.get(Long.class);
        ClassName  domainTypeName = ClassName .get(domainClazz);
        ParameterizedTypeName baseType = ParameterizedTypeName.get(ClassName.get(BaseRepository.class), domainTypeName,idTypeName);

        TypeSpec.Builder typeBuilder = TypeSpec.interfaceBuilder(domainClazz.getSimpleName()+convertCamelName(genrateType))
                .addSuperinterface(baseType)
                .addAnnotation(Repository.class)
                .addModifiers(Modifier.PUBLIC);

        String servicePackageName = getBasePacke()+DOT+genrateType.toLowerCase();
        JavaFile javaFile = JavaFile.builder(servicePackageName,typeBuilder.build())
                .build();
        javaFile.writeTo(System.out);
        return javaFile;
    }
}
