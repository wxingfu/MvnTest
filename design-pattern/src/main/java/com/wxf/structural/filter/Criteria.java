package com.wxf.structural.filter;

import java.util.List;

/*
 *
 * @author weixf
 * @date 2022-08-25
 */
public interface Criteria {

    List<Person> meetCriteria(List<Person> persons);

}
