package com.springframework.core.convert;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public interface ConversionService {

    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    <T> T convert(Object source, Class<T> targetType);
}
