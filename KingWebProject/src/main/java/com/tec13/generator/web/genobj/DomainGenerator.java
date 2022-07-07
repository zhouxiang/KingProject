package com.tec13.generator.web.genobj;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import com.tec13.core.server.vo.BaseVo;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Field;
import java.util.List;

public class DomainGenerator extends BaseGenerator {



    public DomainGenerator(Class<?> domainClazz) {
        super(domainClazz,"vo");
    }

    protected void doGenerate(TypeSpec.Builder domainVoTypeBuilder) throws Exception {
        domainVoTypeBuilder.superclass(BaseVo.class);

        List<Field> allFieldsList = FieldUtils.getAllFieldsList(domainClazz);
        for (Field f: allFieldsList) {
            FieldSpec fieldSpec = FieldSpec.builder(f.getType(),f.getName())
                    .addModifiers(Modifier.PRIVATE).build();
            domainVoTypeBuilder.addField(fieldSpec);

            domainVoTypeBuilder.addMethod(generateVoGetter(f.getType(),f.getName()));
            domainVoTypeBuilder.addMethod(generateVoSetter(f.getType(),f.getName()));

        }

    }

    private MethodSpec generateVoSetter(Class<?> fieldType,String fieldName){
       ParameterSpec setterParam = ParameterSpec.builder(fieldType,fieldName).build();
       MethodSpec setter = MethodSpec.methodBuilder("set"+convertCamelName(fieldName))
               .addModifiers(Modifier.PUBLIC)
               .addParameter(setterParam)
//               .returns(fieldType)
               .addStatement("this."+fieldName+" = "+fieldName)
               .build();
       return setter;
    }

    private MethodSpec generateVoGetter(Class<?> fieldType,String fieldName){
        MethodSpec getter = MethodSpec.methodBuilder("get"+convertCamelName(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(fieldType)
                .addStatement("return this."+fieldName)
                .build();
        return getter;
    }


}
