package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.HomeAreaService;
import com.nstepa.wc.beans.HomeArea;
import com.nstepa.wc.mybatis.support.mappers.HomeAreaMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Service
public class HomeAreaServiceImpl extends ServiceImpl<HomeAreaMapper, HomeArea> implements HomeAreaService {

}
