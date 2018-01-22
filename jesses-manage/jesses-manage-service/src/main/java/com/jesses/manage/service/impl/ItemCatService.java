package com.jesses.manage.service.impl;

import com.github.abel533.mapper.Mapper;
import com.jesses.manage.mapper.ItemCatMapper;
import com.jesses.manage.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatService extends BaseService<ItemCat>  {
    @Autowired
    private ItemCatMapper itemCatMapper;

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
