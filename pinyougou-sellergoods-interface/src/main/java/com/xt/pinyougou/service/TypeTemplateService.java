package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.pinyougou.entity.TypeTemplate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  类型模板 服务类
 * </p>
 *
 * @author xt
 * @since 2020-01-06
 */
public interface TypeTemplateService extends IService<TypeTemplate> {

    IPage<TypeTemplate> selectPage(Integer currentPage, Integer pageNum, TypeTemplate typeTemplate);

    List<Map<String, Object>> selectOptionList();
}
