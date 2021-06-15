package com.system.datamigration.model;

import lombok.*;
import org.apache.logging.log4j.util.Strings;

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

    public boolean isValid() {
        return !Strings.isBlank(name) && !Strings.isBlank(email) && birthDate != null;
    }

}
