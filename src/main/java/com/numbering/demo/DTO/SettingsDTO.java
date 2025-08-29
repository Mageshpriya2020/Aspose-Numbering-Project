package com.numbering.demo.DTO;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;

/**
 *
 * @author Ysr
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "settings")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class SettingsDTO extends BaseDTO implements Serializable {
    @Id
    @Column(name = "id")
    @TableGenerator(name = "sequenceStore", table = "SEQUENCE_STORE", pkColumnName = "SEQ_NAME", pkColumnValue = "SETTINGS_SEQ", valueColumnName = "SEQ_VALUE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequenceStore")
    private long id;

    @Column(name = "xkey")
    private String  key;

    @Column(name = "xvalue")
    private String  value;

    @Column(name = "xlabel")
    private String label;

    //    @JsonIgnore
    @Column(name = "blobValue",columnDefinition = "LONGBLOB")
    private byte[] blobValue;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MODULE_ID", referencedColumnName = "ID", columnDefinition="bigint default 1")
    private ModulesDTO modules;

}


