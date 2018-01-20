package com.jesses.manage.service.impl;

import com.github.abel533.mapper.Mapper;
import com.jesses.manage.mapper.ItemCatMapper;
import com.jesses.manage.pojo.ItemCat;
import com.jesses.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl extends BaseService<ItemCat> implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat>  queryItemCat(Long pid) {
        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(pid);
        List<ItemCat> list = this.itemCatMapper.select(itemCat);
        return list;
    }

    @Override
    public Mapper<ItemCat> getMapper() {
        return this.itemCatMapper;
    }
}
