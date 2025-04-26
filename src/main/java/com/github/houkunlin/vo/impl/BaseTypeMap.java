package com.github.houkunlin.vo.impl;

import com.github.houkunlin.vo.ITypeMap;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link com.github.houkunlin.vo.ITypeMap} 的抽象实现
 *
 * @author daiwenzh5
 */
public abstract class BaseTypeMap<T, V> implements ITypeMap<T, V> {

    @Getter
    private final Map<String, T> map = new HashMap<>();

    @Override
    public final T get(String type) {
        return map.get(type);
    }

}
