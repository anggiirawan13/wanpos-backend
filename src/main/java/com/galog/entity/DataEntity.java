package com.galog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data")
public class DataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "err_code", nullable = false, length = 20, unique = true)
    private String ErrorCode;

    @Column(name = "info_err", nullable = false, length = 255)
    private String InfoError;
}
