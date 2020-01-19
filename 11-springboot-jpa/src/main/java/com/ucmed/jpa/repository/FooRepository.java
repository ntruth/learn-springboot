package com.ucmed.jpa.repository;

import com.ucmed.jpa.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FooRepository extends JpaRepository<Foo, Integer> {
    /*
     * 我们在这里直接继承 JpaRepository
     * 这里面已经有很多现场的方法了
     * 这也是JPA的一大优点
     *
     * */


    /**
     * 根据名称查询对应记录
     * @param name
     * @return
     */
    Foo findFooByName(String name);
}
