package com.tec13.generator.web.genobj;

import com.squareup.javapoet.*;
import org.apache.commons.lang3.StringUtils;

import javax.lang.model.element.Modifier;

public abstract class BaseGenerator {
    protected static final String DOT = ".";
    protected void doGenerate(TypeSpec.Builder domainVoTypeBuilder) throws Exception{

    };
    protected String genrateType;
    protected Class<?> domainClazz;

    public BaseGenerator(Class<?> domainClazz, String genrateType) {
        this.domainClazz = domainClazz;
        this.genrateType = genrateType;
    }

    protected JavaFile generate()  throws Exception{
        TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(domainClazz.getSimpleName()+convertCamelName(genrateType))
                .addModifiers(Modifier.PUBLIC);

        doGenerate(typeBuilder);

        String servicePackageName = getBasePacke()+DOT+genrateType.toLowerCase();
        JavaFile javaFile = JavaFile.builder(servicePackageName,typeBuilder.build())
                .build();
        javaFile.writeTo(System.out);
        return javaFile;
    }
    protected String getBasePacke(){
        String packageName = domainClazz.getPackage().getName();
        return packageName.substring(0,packageName.lastIndexOf("."));
    }


    protected String convertCamelName(String c){
        String firstLetterCapital = StringUtils.upperCase(c.charAt(0)+"");
        return firstLetterCapital + c.substring(1);
    }

    protected String convertCamelNameLower(String c){
        String firstLetterCapital = StringUtils.lowerCase(c.charAt(0)+"");
        return firstLetterCapital + c.substring(1);
    }

}
