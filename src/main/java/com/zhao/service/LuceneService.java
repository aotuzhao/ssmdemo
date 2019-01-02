package com.zhao.service;

import com.zhao.entity.Product;

import java.util.List;

/**
 * @author aotu
 * @date 2019/1/2 17:04
 */
public interface LuceneService {
    void add(Product product);

    List<Product> search(String k);
}
