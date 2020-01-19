package com.ucmed.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Foo {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
}
