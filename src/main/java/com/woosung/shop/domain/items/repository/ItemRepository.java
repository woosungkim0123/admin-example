package com.woosung.shop.domain.items.repository;


import com.woosung.shop.domain.items.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Items, Long>, ItemRepositoryCustom {

}
