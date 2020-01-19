package com.ucmed.mybatis.controller;


import com.ucmed.mybatis.mapper.FooMapper;
import com.ucmed.mybatis.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FooController {
    @Autowired
    private FooMapper fooMapper;

    @GetMapping("")
    public List<Foo> selectAll() {
        return fooMapper.selectAll();
    }

    @GetMapping("/{id}")
    public Foo selectById(@PathVariable("id")Integer id) {
        return fooMapper.selectByPrimaryKey(id);
    }
}
