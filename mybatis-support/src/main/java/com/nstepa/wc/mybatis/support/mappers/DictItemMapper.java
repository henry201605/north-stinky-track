package com.nstepa.wc.mybatis.support.mappers;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.nstepa.wc.beans.DictItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典数据表 Mapper 接口
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
public interface DictItemMapper extends BaseMapper<DictItem> {

	List<DictItem> getDictItemByType(@Param("dictType") String dictType);
}
