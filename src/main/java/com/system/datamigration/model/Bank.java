package com.system.datamigration.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Bank {

    private Integer id;
    private Integer bank;
    private Integer account;
    private Integer agency;
    private Integer personId;

}
