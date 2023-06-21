package com.wanpos.app.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", unique = true, nullable = false, length = 64)
    private String uuid;

    @Column(name = "status", nullable = false, length = 8)
    private String status;

    @Column(name = "created_by", nullable = false, length = 20)
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Timestamp createdAt;

    @Column(name = "modified_by", nullable = false, length = 20)
    private String modifiedBy;

    @Column(name = "modified_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    private Timestamp modifiedAt;

}
