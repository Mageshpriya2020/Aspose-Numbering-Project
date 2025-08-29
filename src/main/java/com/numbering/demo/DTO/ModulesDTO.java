package com.numbering.demo.DTO;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
@Table(name = "zyl_modules")
public class ModulesDTO implements Serializable {
    @Id
    @Column(name = "ID")
    @TableGenerator(name = "sequenceStore", table = "SEQUENCE_STORE", pkColumnName = "SEQ_NAME", pkColumnValue = "ZYLMODULES_SEQ", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceStore")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE")
    private Boolean active;
}

