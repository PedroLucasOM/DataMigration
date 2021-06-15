package com.system.datamigration.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {

    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Integer age;

}
