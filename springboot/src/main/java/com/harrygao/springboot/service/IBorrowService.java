package com.harrygao.springboot.service;

import com.harrygao.springboot.controller.request.BaseRequest;
import com.harrygao.springboot.entity.Borrow;
import com.harrygao.springboot.entity.Retur;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface IBorrowService {
    
    List<Borrow> list();

    PageInfo<Borrow> page(BaseRequest baseRequest);

    void save(Borrow obj);

    PageInfo<Retur> pageRetur(BaseRequest baseRequest);

    void saveRetur(Retur obj);

    Borrow getById(Integer id);

    void update(Borrow obj);

    void deleteById(Integer id);

    void deleteReturById(Integer id);

    Map<String, Object> getCountByTimeRange(String timeRange);
}
