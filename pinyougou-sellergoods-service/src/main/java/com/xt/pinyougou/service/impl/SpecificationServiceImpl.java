package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xt.pinyougou.entity.Specification;
import com.xt.pinyougou.entity.SpecificationOption;
import com.xt.pinyougou.mapper.SpecificationMapper;
import com.xt.pinyougou.pojo.SpecificationGroup;
import com.xt.pinyougou.service.SpecificationOptionService;
import com.xt.pinyougou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  规格 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-05
 */
@Service(timeout = 5000)
public class SpecificationServiceImpl extends ServiceImpl<SpecificationMapper, Specification> implements SpecificationService {

    @Autowired
    private SpecificationOptionService specificationOptionService;

    /**
     * 详情
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public SpecificationGroup getBySpecId(Long id) {
        SpecificationGroup specificationGroup = new SpecificationGroup();
        Specification specification = baseMapper.selectById(id);
        List<SpecificationOption> specificationOptions = specificationOptionService
                .list(new QueryWrapper<SpecificationOption>().lambda().eq(SpecificationOption::getSpecId, specification.getId()));
        specificationGroup.setSpecification(specification).setSpecificationOptions(specificationOptions);
        return specificationGroup;
    }

    /**
     * 保存
     * @param specificationGroup
     * @return
     */
    @Transactional
    @Override
    public boolean saveGroup(SpecificationGroup specificationGroup) {
        Specification specification = specificationGroup.getSpecification();
        List<SpecificationOption> specificationOptions = specificationGroup.getSpecificationOptions();
        // 保存规格
        int result = baseMapper.insert(specification);
//        int i = 10/0;
        if (result == 1) {
            // 设置规格选项的规格ID
            for (SpecificationOption specificationOption : specificationOptions) {
                specificationOption.setSpecId(specification.getId());
            }
            // 批量保存规格选项
            boolean flag = specificationOptionService.saveBatch(specificationOptions);
            return flag;
        }
        return false;
    }

    /**
     * 更新
     * @param specificationGroup
     * @return
     */
    @Transactional
    @Override
    public boolean updateGroup(SpecificationGroup specificationGroup) {
        Specification specification = specificationGroup.getSpecification();
        List<SpecificationOption> specificationOptions = specificationGroup.getSpecificationOptions();
        // 更新规格
        int result = baseMapper.updateById(specification);
        if (result == 1) {
            // 设置规格选项的规格ID
            // 删除原有的规格选项
            specificationOptionService.remove(new QueryWrapper<SpecificationOption>().lambda().eq(SpecificationOption::getSpecId, specification.getId()));
            for (SpecificationOption specificationOption : specificationOptions) {
                specificationOption.setSpecId(specification.getId());
            }
            // 批量保存新的规格选项
            boolean flag = specificationOptionService.saveBatch(specificationOptions);
            return flag;
        }
        return false;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Transactional
    @Override
    public boolean removeGroupById(Long id) {
        int result = baseMapper.deleteById(id);
        if (result == 1) {
            boolean flag = specificationOptionService.remove(new QueryWrapper<SpecificationOption>().lambda().eq(SpecificationOption::getSpecId, id));
            return flag;
        }
        return false;
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public boolean removeGroupByIds(List<Long> ids) {
        int count = baseMapper.deleteBatchIds(ids);
        if (count == ids.size()) {
            boolean flag = specificationOptionService.remove(new QueryWrapper<SpecificationOption>().lambda().in(SpecificationOption::getSpecId, ids));
            return flag;
        }
        return false;
    }

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageNum
     * @param specification
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public IPage<Specification> selectPage(Integer currentPage, Integer pageNum, Specification specification) {
        QueryWrapper<Specification> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(specification.getSpecName())) {
            queryWrapper.lambda().like(Specification::getSpecName, specification.getSpecName());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }

    @Override
    public List<Map<String, Object>> selectOptionList() {
        QueryWrapper<Specification> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "spec_name as text");
        return baseMapper.selectMaps(queryWrapper);
    }
}
