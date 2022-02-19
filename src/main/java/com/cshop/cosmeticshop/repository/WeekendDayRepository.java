package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.Doctor;
import com.cshop.cosmeticshop.domain.entity.WeekendDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WeekendDayRepository extends JpaRepository<WeekendDay, Long> {

    List<WeekendDay> findByDoctor(Doctor doctor);
}