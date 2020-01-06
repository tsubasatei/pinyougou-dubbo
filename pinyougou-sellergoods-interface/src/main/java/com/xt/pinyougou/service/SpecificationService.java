package com.xt.pinyougou.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xt.pinyougou.entity.Specification;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xt.pinyougou.pojo.SpecificationGroup;

import java.util.List;

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
}
