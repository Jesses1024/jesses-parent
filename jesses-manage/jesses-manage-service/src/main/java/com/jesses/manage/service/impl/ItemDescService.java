package com.jesses.manage.service.impl;

import com.github.abel533.mapper.Mapper;
import com.jesses.manage.mapper.ItemDescMapper;
import com.jesses.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品描述的Service
 */
@Service
public class ItemDescService extends BaseService<ItemDesc>  {
    @Autowired
    private ItemDescMapper itemDescMapper;


    @Override
    public Mapper<ItemDesc> getMapper() {
        return this.itemDescMapper;
    }

}
