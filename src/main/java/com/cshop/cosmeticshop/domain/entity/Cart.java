package com.cshop.cosmeticshop.domain.entity;

import com.cshop.cosmeticshop.domain.entity.constants.CartStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

/**
 * Cart entity
 *
 * @author Pave1Pal
 */
@Getter
@Setter
@Entity
@Table(name = "carts")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Cart extends BaseEntity {

    private Long totalPrice;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationTime;

    @LastModifiedDate
    private LocalDateTime lastModifiedTime;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "treatment_cart",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id"))
    private List<Treatment> treatments = new ArrayList<>();

    @OneToOne
    @CreatedBy
    private User user;

    @Enumerated(value = EnumType.STRING)
    private CartStatus status;
}
