package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.pinyougou.entity.Specification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.SpecificationGroup;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  规格 服务类
 * </p>
 *
 * @author xt
 * @since 2020-01-05
 */
public interface SpecificationService extends IService<Specification> {

    SpecificationGroup getBySpecId(Long id);

    boolean saveGroup(SpecificationGroup specificationGroup);

    boolean updateGroup(SpecificationGroup specificationGroup);

    boolean removeGroupById(Long id);

    boolean removeGroupByIds(List<Long> asList);

    IPage<Specification> selectPage(Integer currentPage, Integer pageNum, Specification specification);

    /**
     * 读取规格列表
     * @return
     */
    List<Map<String, Object>> selectOptionList();
}
