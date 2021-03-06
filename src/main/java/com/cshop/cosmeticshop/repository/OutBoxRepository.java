package com.cshop.cosmeticshop.repository;

import com.cshop.cosmeticshop.domain.entity.OutBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for OutBox entity.
 *
 * @author Pave1Pal
 */
@Repository
public interface OutBoxRepository extends JpaRepository<OutBox, Long> {

    /**
     * Find all OutBox using pageable interface.
     *
     * @param pageable page parameters
     * @return Page of OutBox
     */
    Page<OutBox> findAll(Pageable pageable);

}
