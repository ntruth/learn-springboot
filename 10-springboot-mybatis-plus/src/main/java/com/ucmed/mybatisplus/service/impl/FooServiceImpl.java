package com.ucmed.mybatisplus.service.impl;

import com.ucmed.mybatisplus.model.Foo;
import com.ucmed.mybatisplus.mapper.FooMapper;
import com.ucmed.mybatisplus.service.FooService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aniver
 * @since 2020-01-19
 */
@Service
public class FooServiceImpl extends ServiceImpl<FooMapper, Foo> implements FooService {

}
