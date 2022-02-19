package com.cshop.cosmeticshop.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "weeknd_days")
@NoArgsConstructor
public class WeekendDay extends BaseEntity {

    private String name;

    private LocalDate date;

    @ManyToOne
    private Doctor doctor;
}