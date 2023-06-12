package com.webproject.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webproject.api.cart.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartById(String userId);
}
