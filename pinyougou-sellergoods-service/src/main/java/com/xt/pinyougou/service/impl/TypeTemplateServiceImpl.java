package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.pinyougou.entity.SpecificationOption;
import com.xt.pinyougou.entity.TypeTemplate;
import com.xt.pinyougou.mapper.TypeTemplateMapper;
import com.xt.pinyougou.service.SpecificationOptionService;
import com.xt.pinyougou.service.TypeTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  类型模板 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-06
 */
@Service(timeout = 5000)
public class TypeTemplateServiceImpl extends ServiceImpl<TypeTemplateMapper, TypeTemplate> implements TypeTemplateService {

    @Autowired
    private SpecificationOptionService specificationOptionService;

    @Transactional(readOnly = true)
    @Override
    public IPage<TypeTemplate> selectPage(Integer currentPage, Integer pageNum, TypeTemplate typeTemplate) {
        QueryWrapper<TypeTemplate> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(typeTemplate.getName())) {
            queryWrapper.lambda().like(TypeTemplate::getName, typeTemplate.getName());
        }
        return baseMapper.selectPage(new Page<>(currentPage, pageNum), queryWrapper);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Map> findSpecList(Long id) {
        TypeTemplate typeTemplate = baseMapper.selectById(id);
        List<Map> maps = JSON.parseArray(typeTemplate.getSpecIds(), Map.class);
        for (Map map : maps) {
            List<SpecificationOption> options = specificationOptionService.list(new QueryWrapper<SpecificationOption>()
                    .lambda().eq(SpecificationOption::getSpecId, map.get("id")));
            map.put("options", options);
        }
        return maps;
    }

}
