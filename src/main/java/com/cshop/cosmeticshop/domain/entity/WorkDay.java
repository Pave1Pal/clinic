package com.cshop.cosmeticshop.domain.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

/**
 * Day entity
 *
 * @author PavelPa1
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "work_days")
public class WorkDay extends BaseEntity {

    @NonNull
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @NotNull
    private boolean workDay;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime workStartAt;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime workFinishAt;

    @OneToMany(fetch = LAZY,
            mappedBy = "workDay",
            cascade = {MERGE, PERSIST, REFRESH, DETACH})
    private List<TreatmentPeriod> treatmentPeriods;

    @Transient
    private LocalDate date;

    @ManyToOne(targetEntity = WorkWeek.class)
    @JoinTable(name = "day_of_week_mapping", joinColumns = @JoinColumn(name = "work_day_id"))
    private WorkWeek workWeek;

    public boolean addTreatmentPeriod(TreatmentPeriod period) {
        period.setWorkDay(this);
        return treatmentPeriods.add(period);
    }
}
