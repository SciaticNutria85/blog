package com.mrnm.sys.blog.service;

public interface IDelete<ID> {
    void delete(ID deletedEntityId) throws Exception;
}
