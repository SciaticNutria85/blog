package com.mrnm.sys.blog.service;

import java.util.List;

public interface IRead<T, IDType> {
    T read(IDType id) throws Exception;
    List<T> readAll();
}
