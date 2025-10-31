package com.mrnm.sys.blog.service;

import java.util.List;

public interface IQuery<T, C> {
    List<T> queryObject(C criteria);
}
