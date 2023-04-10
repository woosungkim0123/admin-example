package com.woosung.shop.domain.items.repository;

import com.woosung.shop.domain.items.dto.ItemSearchCondition;
import com.woosung.shop.domain.items.dto.ItemSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    public Page<ItemSearchDto> searchItems(ItemSearchCondition condition, Pageable pageable);
}
