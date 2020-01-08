package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xt.pinyougou.entity.TypeTemplate;
import com.xt.pinyougou.mapper.TypeTemplateMapper;
import com.xt.pinyougou.service.TypeTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public List<Map<String, Object>> selectOptionList() {
        QueryWrapper<TypeTemplate> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name as text");
        return baseMapper.selectMaps(queryWrapper);
    }
}
