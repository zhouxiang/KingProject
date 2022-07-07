package com.tec13.generator.web.genobj;

import com.squareup.javapoet.*;
import com.tec13.core.server.vo.BaseVo;
import com.tec13.core.server.vo.KingRangeValue;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.lang.model.element.Modifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

public class VoGenerator extends BaseGenerator {

    public static final String POSTFIX_VO_START = "VoStart";
    public static final String POSTFIX_VO_END = "VoEnd";

    public VoGenerator(Class<?> domainClazz) {
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

            for (Annotation fAnnotation : f.getAnnotations()) {

                if(fAnnotation.annotationType().equals(KingRangeValue.class)){
                    FieldSpec startField = FieldSpec.builder(f.getType(),f.getName()+POSTFIX_VO_START)
                            .addModifiers(Modifier.PRIVATE).build();

                    FieldSpec endField = FieldSpec.builder(f.getType(),f.getName()+POSTFIX_VO_END)
                            .addModifiers(Modifier.PRIVATE).build();

                    domainVoTypeBuilder.addField(startField);
                    domainVoTypeBuilder.addField(endField);

                    domainVoTypeBuilder.addMethod(generateVoGetter(f.getType(),startField.name));
                    domainVoTypeBuilder.addMethod(generateVoSetter(f.getType(),startField.name));
                    domainVoTypeBuilder.addMethod(generateVoGetter(f.getType(),endField.name));
                    domainVoTypeBuilder.addMethod(generateVoSetter(f.getType(),endField.name));
                }
            }

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
