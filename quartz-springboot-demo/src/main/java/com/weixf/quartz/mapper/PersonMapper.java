package com.weixf.quartz.mapper;


import com.weixf.quartz.entity.Person;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personMapper")
public interface PersonMapper {

    @Select("select id,name,age,sex,address,sect,skill,power,create_time createTime,modify_time modifyTime from test_quartz.persons")
    List<Person> queryList();
}
