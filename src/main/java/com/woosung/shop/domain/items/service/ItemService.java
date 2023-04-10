package com.woosung.shop.domain.items.service;



import com.woosung.shop.domain.items.Items;
import com.woosung.shop.domain.items.dto.ItemSearchCondition;
import com.woosung.shop.domain.items.dto.ItemSearchDto;
import com.woosung.shop.domain.items.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Page<ItemSearchDto> searchItems(ItemSearchCondition condition, Pageable pageable) {
        return itemRepository.searchItems(condition, pageable);
    }
}