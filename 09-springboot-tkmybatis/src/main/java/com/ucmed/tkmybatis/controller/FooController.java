package com.ucmed.tkmybatis.controller;

import com.ucmed.tkmybatis.mapper.FooMapper;
import com.ucmed.tkmybatis.model.Foo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@RestController
@RequestMapping("foo")
public class FooController {
    @Autowired
    private FooMapper fooMapper;

    @GetMapping("")
    public List<Foo> selectAll() {
        return fooMapper.selectAll();
    }

    @GetMapping("{id}")
    public Foo selectByPrimaryKey(@PathVariable("id") Integer id) {
//        example写法
//        Example example = new Example(Foo.class);
//        example.createCriteria().andEqualTo("id", id);
//
//        return fooMapper.selectOneByExample(example);

//        使用tkmybatis封装的根据主键查询数据
        return fooMapper.selectByPrimaryKey(id);
    }
}
