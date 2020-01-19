package com.ucmed.mybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ucmed.mybatisplus.model.Foo;
import com.ucmed.mybatisplus.service.FooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author aniver
 * @since 2020-01-19
 */
@RestController
@RequestMapping("/foo")
public class FooController {
    @Autowired
    private FooService fooService;

    @GetMapping
    public List<Foo> selectAll(){
        return fooService.list();
    }

    @GetMapping("/{name}")
    public Foo selectByPrimaryKey(@PathVariable("name")String name) {
        QueryWrapper<Foo> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Foo::getName, name);
        return fooService.getOne(queryWrapper);
    }
}
