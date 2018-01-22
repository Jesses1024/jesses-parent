package com.jesses.manage.service.impl;

import com.github.abel533.mapper.Mapper;
import com.jesses.manage.mapper.ItemMapper;
import com.jesses.manage.pojo.Item;
import com.jesses.manage.pojo.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品的Service
 */
@Service
public class ItemService extends BaseService<Item>{
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Mapper<Item> getMapper() {
        return this.itemMapper;
    }

    @Autowired
    private ItemDescService itemDescService;

    /**
     * 保存商品和商品详情
     * @param item
     * @param desc
     */
    public void saveItem(Item item, String desc) {
        //设置初始状态值为1
        item.setStatus(1);
        //设置id为空，又数据库自增生成
        item.setId(null);
        //保存商品
        super.save(item);

        ItemDesc itemDesc = new ItemDesc();
        //设置商品详情的id和商品id一致
        itemDesc.setItemId(item.getId());
        //设置商品详情内容
        itemDesc.setItemDesc(desc);
        //保存商品详情
        this.itemDescService.save(itemDesc);
    }
}
