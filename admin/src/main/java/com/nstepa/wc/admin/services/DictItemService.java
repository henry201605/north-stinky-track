package com.nstepa.wc.admin.services;


import com.baomidou.mybatisplus.service.IService;
import com.nstepa.wc.beans.DictItem;

import java.util.List;

/**
 * <p>
 * 字典数据表 服务类
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
public interface DictItemService extends IService<DictItem> {
	/**
	 * 根据字典类型获取字典数据
	 * @param dictType
	 */
	List<DictItem> getDictItemByType(String dictType);
}
