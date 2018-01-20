package com.jesses.manage.service;

import com.jesses.manage.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> queryItemCat(Long pid);
}
