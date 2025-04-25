package com.github.houkunlin.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

/**
 * @author daiwenzh5
 * @since 1.0
 */
public class FileTypeModel extends AbstractTableModel {

    String[] header = {"类型" , "后缀" , "拓展名" , "包名" , "允许覆盖"};

    Table<Integer, Integer, Object> table = HashBasedTable.create();

    public FileTypeModel(JTable table) {
        init();
        table.setModel(this);
    }

    private void init() {
        table.put(0, 0, "entity");
        table.put(0, 1, "Entity");
        table.put(0, 2, ".java");
        table.put(0, 3, "com.example.entity");
        table.put(0, 4, true);
    }

    @Override
    public int getRowCount() {
        return table.rowKeySet().size();
    }

    @Override
    public int getColumnCount() {
        return table.columnKeySet().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return table.get(rowIndex, columnIndex);
    }

}
