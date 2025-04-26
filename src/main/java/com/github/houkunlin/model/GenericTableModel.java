package com.github.houkunlin.model;

import lombok.Getter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

/**
 * 通用的表格模型
 *
 * @author daiwenzh5
 * @since 2025/4/26
 */
@SuppressWarnings({"UnusedReturnValue"})
public class GenericTableModel<T> extends AbstractTableModel {

    private final List<ColumnSpec<T, ?>> columns = new ArrayList<>();

    @Getter
    private final List<T> data;

    public GenericTableModel(List<T> data) {
        this.data = data;
    }

    /**
     * 添加行数据
     *
     * @param item 行数据
     * @return 当前表格模型
     */
    @SafeVarargs
    public final GenericTableModel<T> addRows(T... item) {
        var startRow = data.size();
        data.addAll(List.of(item));
        var endRow = data.size() - 1;
        fireTableRowsInserted(startRow, endRow);
        return this;
    }

    /**
     * 根据行号删除数据
     *
     * @param rows 行号
     * @return 当前表格模型
     */
    public final GenericTableModel<T> removeRows(int... rows) {
        // 将rows按降序排序
        Arrays.sort(rows);
        for (var i = rows.length - 1; i >= 0; i--) {
            this.data.remove(rows[i]);
        }
        fireTableRowsInserted(rows[0], this.data.size());
        return this;
    }

    /**
     * 添加列
     *
     * @param column 列信息
     * @return 当前表格模型
     */
    public final GenericTableModel<T> addColumn(ColumnSpec<T, ?> column) {
        columns.add(column);
        return this;
    }

    /**
     * 绑定表格
     *
     * @param table 表格
     * @return 当前表格模型
     */
    public final GenericTableModel<T> bindTable(JTable table) {
        table.setModel(this);
        return this;
    }

    @Override
    public final int getRowCount() {
        return data.size();
    }

    @Override
    public final int getColumnCount() {
        return columns.size();
    }

    @Override
    public final Object getValueAt(int row, int col) {
        T item = data.get(row);
        return columns.get(col)
                      .getter()
                      .apply(item);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void setValueAt(Object value, int row, int col) {
        var item = data.get(row);
        //noinspection rawtypes
        final BiConsumer setter = columns.get(col)
                                         .setter();
        setter.accept(item, value);
    }

    @Override
    public final String getColumnName(int column) {
        return columns.get(column)
                      .name();
    }

    @Override
    public final Class<?> getColumnClass(int columnIndex) {
        return columns.get(columnIndex)
                      .type();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return columns.get(column)
                      .editable();
    }

    /**
     * 清空数据
     *
     * @return 当前表格模型
     */
    public GenericTableModel<T> clear() {
        if (!this.data.isEmpty()) {
            data.clear();
            fireTableDataChanged();
        }
        return this;
    }
}
