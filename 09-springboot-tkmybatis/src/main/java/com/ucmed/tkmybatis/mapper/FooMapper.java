package com.ucmed.tkmybatis.mapper;

import com.ucmed.tkmybatis.model.Foo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface FooMapper extends Mapper<Foo> {
}