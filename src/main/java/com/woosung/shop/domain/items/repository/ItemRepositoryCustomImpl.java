package com.woosung.shop.domain.items.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.woosung.shop.domain.items.QItems;
import com.woosung.shop.domain.items.dto.ItemSearchCondition;
import com.woosung.shop.domain.items.dto.ItemSearchDto;
import com.woosung.shop.domain.items.dto.QItemSearchDto;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.woosung.shop.domain.items.QItems.*;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<ItemSearchDto> searchItems(ItemSearchCondition condition, Pageable pageable) {
        List<ItemSearchDto> content = queryFactory
                .select(new QItemSearchDto(
                        items.name,
                        items.price
                ))
                .from(items)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(items.count())
                .from(items)
                .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
