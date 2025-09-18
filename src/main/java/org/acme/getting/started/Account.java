package org.acme.getting.started;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity(name="erp_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 50)
    private String no;
    private Byte status;
    private Integer sort;

}