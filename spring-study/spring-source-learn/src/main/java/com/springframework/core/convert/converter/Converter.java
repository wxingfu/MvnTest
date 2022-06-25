package com.springframework.core.convert.converter;

/*
 * 类型转换抽象接口
 * @author weixf
 * @date 2022-06-24
 */
public interface Converter<S, T> {

    /**
     * 类型转换
     */
    T convert(S source);
}