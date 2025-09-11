package com.mrnm.sys.blog.service;

public interface IUpdate<T> {
    T update(T updatedEntity) throws Exception;
}
