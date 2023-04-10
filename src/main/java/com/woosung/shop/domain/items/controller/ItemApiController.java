package com.woosung.shop.domain.items.controller;

import com.woosung.shop.domain.items.dto.ItemSearchCondition;
import com.woosung.shop.domain.items.dto.ItemSearchDto;
import com.woosung.shop.domain.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/api/v1/items")
    public Page<ItemSearchDto> items(ItemSearchCondition condition, Pageable pageable) {
        return itemService.searchItems(condition, pageable);
    }
}
