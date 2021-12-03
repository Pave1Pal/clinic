package com.cshop.cosmeticshop.service.impl;

import com.cshop.cosmeticshop.domain.intity.Order;
import com.cshop.cosmeticshop.domain.intity.Cart;
import com.cshop.cosmeticshop.repository.OrderRepo;
import com.cshop.cosmeticshop.domain.intity.User;
import com.cshop.cosmeticshop.service.CartService;
import com.cshop.cosmeticshop.service.EmailService;
import com.cshop.cosmeticshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CartService cartService;
    private final EmailService emailService;

    @Autowired
    public OrderServiceImpl(OrderRepo orderRepo, CartService cartService, EmailService emailService) {
        this.orderRepo = orderRepo;
        this.cartService = cartService;
        this.emailService = emailService;
    }

    public Order saveOrder(Order order, Cart cart, User user) {
        var savedCart = cartService.saveCart(cart);
        order.setCart(savedCart);
        order.findFinishTime();
        order.setUser(user);
        emailService.sendMessage(order, user);
        return orderRepo.save(order);
    }

}