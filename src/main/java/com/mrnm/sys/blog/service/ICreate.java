package com.mrnm.sys.blog.service;

public interface ICreate<T> {
    T create(T createdEntity) throws Exception;
}
