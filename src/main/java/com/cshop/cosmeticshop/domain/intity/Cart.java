package com.cshop.cosmeticshop.domain.intity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Cart {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long totalPrice;
    @ManyToMany
    @JoinTable(name="cart_service",
            joinColumns = @JoinColumn(name="cart_id"),
            inverseJoinColumns = @JoinColumn(name="service_id"))
    private List<Treatment> treatments = new ArrayList<>();

}
