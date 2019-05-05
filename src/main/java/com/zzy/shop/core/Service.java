package com.zzy.shop.core;


import java.util.List;
import java.util.Optional;

/**
 * Service 层 基础接口，其他Service 接口 请继承该接口
 */
public interface Service<T> {
    void deleteById(Long id);//通过主鍵刪除
    void save(T model);//更新
    Optional<T> findById(Long id);//通过ID查找
    List<T> findAll();//获取所有
}
