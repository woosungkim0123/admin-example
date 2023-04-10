package com.woosung.shop.domain.items.dto;

import com.querydsl.core.annotations.QueryProjection;

public class ItemSearchDto {
    private String name;
    private int price;

    @QueryProjection
    public ItemSearchDto(String name, int price) {
        this.name = name;
        this.price = price;
    }

}
