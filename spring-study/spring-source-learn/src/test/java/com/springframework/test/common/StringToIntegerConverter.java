package com.springframework.test.common;

import com.springframework.core.convert.converter.Converter;

/*
 *
 * @author weixf
 * @date 2022-06-24
 */
public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {
        return Integer.valueOf(source);
    }
}

