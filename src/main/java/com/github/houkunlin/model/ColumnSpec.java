package com.github.houkunlin.model;

import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 通用的列信息
 *
 * @param name     列名
 * @param type     列类型
 * @param getter   获取列数据的方法
 * @param setter   设置列数据的方法
 * @param editable 是否可编辑
 * @author daiwenzh5
 * @since 1.0
 */
public record ColumnSpec<T, V>(
    String name,
    Class<V> type,
    Function<T, V> getter,
    BiConsumer<T, V> setter,
    boolean editable) {

    public ColumnSpec(String name, Class<V> type, Function<T, V> getter, BiConsumer<T, V> setter) {
        this(name, type, getter, setter, true);
    }

}
