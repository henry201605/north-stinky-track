package com.nstepa.wc.admin.services.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.nstepa.wc.admin.services.DictItemService;
import com.nstepa.wc.beans.DictItem;
import com.nstepa.wc.mybatis.support.mappers.DictItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务实现类
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements DictItemService {

	@Autowired
	private DictItemMapper dictItemMapper;
	@Override
	public List<DictItem> getDictItemByType(String dictType) {
		return dictItemMapper.getDictItemByType(dictType);
	}
}
