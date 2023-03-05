package com.zty.framework.third.converter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 自定义的响应转换器，适用于返回报文不规则的场景（空内容、纯文字等）
 * @author: Tianyi.Zeng
 * @create: 14/5/2021 - 上午 11:17
 */
public class StringConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        // 进行条件判断，如果传进来的Type不是String，则匹配失败，失败会使用后面的转换器
        if (!(type instanceof Class<?>) || !type.getTypeName().equals(String.class.getTypeName())) {
            // 返回空则表示没匹配上
            return null;
        }
        return new StringConverter();
    }

    public class StringConverter implements Converter<ResponseBody, String> {

        @Override
        public String convert(ResponseBody responseBody) throws IOException {
            try {
                return responseBody.string();
            } catch (IOException e) {
                return "";
            }
        }
    }
}