package com.ucmed.jpa.controller;

import com.ucmed.jpa.model.Foo;
import com.ucmed.jpa.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("foo")
@RestController
public class FooController {

    @Autowired
    private FooRepository fooRepository;

    @GetMapping
    public List<Foo> selectAll() {
        return fooRepository.findAll();
    }

    @GetMapping("{id}")
    public Foo selectByPrimaryKey(@PathVariable("id")Integer id) {
        Optional<Foo> fooOptional = fooRepository.findById(id);

        return fooOptional.orElse(new Foo());
    }

    @GetMapping("name/{name}")
    public Foo selectByName(@PathVariable("name")String name) {
        return fooRepository.findFooByName(name);
    }
}
