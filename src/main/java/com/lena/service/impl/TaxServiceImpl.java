package com.lena.service.impl;

import com.lena.entity.Tax;
import com.lena.mapper.TaxMapper;
import com.lena.service.ITaxService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lena
 * @since 2019-03-07
 */
@Service
public class TaxServiceImpl extends ServiceImpl<TaxMapper, Tax> implements ITaxService {

}
