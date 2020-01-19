package com.ucmed.mybatis.mapper;

import com.ucmed.mybatis.model.Foo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 注:若遇到Invalid bound statement (not found)
 * 则需要在pom.xml配置<resource></resources>相关配置
 */
@Mapper
public interface FooMapper {

    /**
     * 注解形式
     *
     * @param id
     * @return
     */
    @Select("select id, name from foo where id = #{id}")
    Foo selectByPrimaryKey(@Param("id") Integer id);

    /**
     * xml形式
     */
    List<Foo> selectAll();
}
