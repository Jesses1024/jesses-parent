package com.jesses.manage.controller;

import com.jesses.manage.pojo.Item;
import com.jesses.manage.pojo.ItemCat;
import com.jesses.manage.pojo.ItemDesc;
import com.jesses.manage.service.impl.ItemCatService;
import com.jesses.manage.service.impl.ItemDescService;
import com.jesses.manage.service.impl.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> saveItem(Item item,@RequestParam("desc")String desc){
        try {
            if (StringUtils.isEmpty(item.getTitle())){//标题不为空
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);//数据不符合类型，返回400
            }
            if (StringUtils.isEmpty(item.getPrice())//价格不为空 且为 正数
                    &&!Pattern.matches("/^[+]{0,1}(\\d+)$/",item.getPrice()+"")){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (StringUtils.isEmpty(item.getNum())//价格不为空 且为 正整数
                    &&!Pattern.matches("/^[+]{0,1}(\\d+)$/",item.getNum()+"")){
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }

            this.itemService.saveItem(item,desc);

            //保存成功 返回201
            return new ResponseEntity<Void>(HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
    }
}
