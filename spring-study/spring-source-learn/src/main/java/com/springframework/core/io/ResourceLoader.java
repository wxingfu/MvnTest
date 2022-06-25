package com.springframework.core.io;

/*
 *
 * @author weixf
 * @date 2022-06-23
 */
public interface ResourceLoader {

    Resource getResource(String location);
}

