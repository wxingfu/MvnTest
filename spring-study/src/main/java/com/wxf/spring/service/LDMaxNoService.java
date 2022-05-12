package com.wxf.spring.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wxf.spring.entity.LDMaxNo;

/**
 * @author weixf
 * @since 2022-03-07
 */
public interface LDMaxNoService extends IService<LDMaxNo> {

    String CreateMaxNo( String cNoType, int cNoLength);

}
