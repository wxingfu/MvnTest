package com.wxf.spring.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wxf.spring.entity.LDMaxNo;
import com.wxf.spring.mapper.LDMaxNoMapper;
import com.wxf.spring.service.LDMaxNoService;
import com.wxf.spring.utils.PubFun;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author weixf
 * @since 2022-03-07
 */
@Service
public class LDMaxNoServiceImpl extends ServiceImpl<LDMaxNoMapper, LDMaxNo> implements LDMaxNoService {

    @Transactional
    @Override
    public String CreateMaxNo(String cNoType, int cNoLength) {
        String cNoLimit = "SN";
        cNoType = cNoType.toUpperCase();
        QueryWrapper<LDMaxNo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("NOTYPE", cNoType).eq("NOLIMIT", cNoLimit);
        LDMaxNo ldMaxNo = baseMapper.selectOne(queryWrapper);
        if (ldMaxNo == null) {
            ldMaxNo = new LDMaxNo();
            ldMaxNo.setNoType(cNoType);
            ldMaxNo.setNoLimit(cNoLimit);
            ldMaxNo.setMaxNo(new BigDecimal(1));
            baseMapper.insert(ldMaxNo);
        } else {
            BigDecimal maxNo = ldMaxNo.getMaxNo();
            BigDecimal newMaxNo = new BigDecimal(maxNo.intValue() + 1);
            UpdateWrapper<LDMaxNo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("NOTYPE", cNoType).eq("NOLIMIT", cNoLimit).set("MAXNO", newMaxNo);
            baseMapper.update(null, updateWrapper);
        }
        String maxNo = ldMaxNo.getMaxNo().toString();
        return PubFun.LCh(maxNo, "0", cNoLength).trim();
    }
}
