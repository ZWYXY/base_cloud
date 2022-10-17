package com.zr.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class JacksonConfig {

// 需求 null数组需要转为[] null字符串需要转为""
        @Bean
        @Primary
        @ConditionalOnMissingBean(ObjectMapper.class)
        public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder){
            ObjectMapper objectMapper = builder.createXmlMapper(false).build();
            objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new SerializerModifier()));
            return objectMapper;
        }

        static class SerializerModifier extends BeanSerializerModifier{

            @Override
            public List<BeanPropertyWriter> changeProperties
                (SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
                // 循环所有的beanPropertyWriter
                for (BeanPropertyWriter writer : beanProperties) {
                    // 判断字段的类型，如果是array，list，set则注册nullSerializer
                    if (isArrayType(writer)) {
                        //给writer注册一个自己的nullSerializer
                        writer.assignNullSerializer(new MyNullArrayJsonSerializer());
                    }
                    if (isStringType(writer)) {
                        writer.assignNullSerializer(new MyNullStringJsonSerializer());
                    }

                }
                return beanProperties;
            }

            protected boolean isArrayType(BeanPropertyWriter writer) {
                Class<?> clazz = writer.getType().getRawClass();
                return clazz.isArray() || clazz.equals(List.class) || clazz.equals(Set.class) || clazz.equals(HashSet.class);
            }

            protected boolean isStringType(BeanPropertyWriter writer) {
                Class<?> clazz = writer.getType().getRawClass();
                return clazz.equals(String.class);
            }


        }

        static class MyNullStringJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException{
                if (value == null) {
                    jgen.writeString("");
                }
            }
        }

        static class MyNullArrayJsonSerializer extends JsonSerializer<Object> {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException{
                if (value == null) {
                    jgen.writeStartArray();
                    jgen.writeEndArray();
                } else {
                    jgen.writeObject(value);
                }
            }
        }

}
