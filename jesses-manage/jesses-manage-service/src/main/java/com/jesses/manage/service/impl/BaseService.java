package com.jesses.manage.service.impl;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通用的service
 */
public abstract class BaseService<T> {

    /*
    如何给通用Service注入Mapper，
    直接注入ItemCatMapper?
    错误!无法确定使用什么类型的Mapper。

    解决方法：创建一个抽象的返回值为泛型Mapper的getMapper方法，
    抽象是因为不想让通用Service创建对象
    子类继承通用Service，就必须实现getMapper方法，直接在其中返回明确类型的Mapper，
    通用Service使用this.getMapper()，因为BaseService是抽象类，创建不了对象，
    this代表的是子类对象，只能调用子类中的getMapper()，
    */
    public abstract Mapper<T> getMapper();

    private Class<T> clazz;

    {
            //因子类继承本父类，当调用getGenericSuperclass方法时，就回返回子类的父类的泛型类型。
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
            //获取泛型的父类类型。返回父类类型。
            //父类泛型中是有可能存在多个的，所以采用getActualTypeArguments方法获取一个数组。
            //当然，这里只需要一个，直接拿第一个。
        clazz= (Class<T>) type.getActualTypeArguments()[0];
            //注意：这种方法有局限，它只能获取编译期的泛型。如父类编译时就获取到了泛型类型。（编译期）
            //new ArrayList<T>();想获取这样的泛型则不行，它是运行时的。
            // 不过可以写成new ArrayList<T>(){ },这样就成了一个匿名内部类，可以在编译时期就生成字节码文件。
    }


    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T queryById(Long id){
       return this.getMapper().selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     * @return
     */
    public List<T> queryAll(){
        //return this.getMapper().select(null);
        return this.queryListByWhere(null);
    }

    /**
     * 查询一个
     */
    public T queryOne(T record){
        return this.getMapper().selectOne(record);
    }

    /**
     * 根据条件查询多个
     * @param record
     * @return
     */
    public List<T> queryListByWhere(T record){
        return this.getMapper().select(record);
    }

    /**
     * 根据条件查询多个，并且分页。
     * @param record
     * @param page
     * @param rows
     * @return
     */
    public PageInfo<T> queryPageListByWhere(T record, Integer page, Integer rows){
        PageHelper.startPage(page,rows);
        List<T> list = this.getMapper().select(record);
        return new PageInfo<T>(list);//做分页不光想要list，还想返回总条数，可以返回PageInfo
    }

    /**
     * 根据多个id进行查询
     * @param property
     * @param ids
     * @return
     */
    public List<T> queryByIds(String property,List<Object> ids){
        //不应该把父类的泛型手写传入，也无法T.class,要用子类结合反射的方式获取。
        Example example = new Example(clazz);
        //andIn的第一个参数类中对应是表中主键id的属性，但无法确定它一定就叫id，
        // 所以不要写死，应采用参数传递的方式传入。
        example.createCriteria().andIn(property,ids);
        return this.getMapper().selectByExample(example);
    }


//1、	queryById
//2、	queryAll
//3、	queryOne
//4、	queryListByWhere
//5、	queryPageListByWhere
//6、	save
//7、	update
//8、	deleteById
//9、	deleteByIds
//10、deleteByWhere
}
