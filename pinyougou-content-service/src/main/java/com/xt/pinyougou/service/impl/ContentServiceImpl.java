package com.xt.pinyougou.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xt.pinyougou.entity.Content;
import com.xt.pinyougou.mapper.ContentMapper;
import com.xt.pinyougou.service.ContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * <p>
 *  广告 服务实现类
 * </p>
 *
 * @author xt
 * @since 2020-01-11
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements ContentService {

}
