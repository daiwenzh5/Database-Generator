package com.github.houkunlin.ui.win;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileTypesPanel extends JBPanel<FileTypesPanel> {
    private final JBTable table;
    private final DefaultTableModel tableModel;

    public FileTypesPanel() {
        // 初始化表格模型和列
        tableModel = new DefaultTableModel();
        tableModel.addColumn("类型");
        tableModel.addColumn("后缀");
        tableModel.addColumn("拓展名");
        tableModel.addColumn("是否覆盖");
        tableModel.addColumn("包名");

        // 创建表格和滚动面板
        table = new JBTable(tableModel);
        JBScrollPane scrollPane = new JBScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        // 创建工具栏按钮
        JButton addButton = new JButton("添加");
        JButton deleteButton = new JButton("删除");

        // 设置按钮事件监听器
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 添加新行
                Object[] newRow = {"" , "" , "" , "false" , ""};
                tableModel.addRow(newRow);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 删除选中行
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        // 布局设置
        setLayout(new BorderLayout());
        add(createToolBar(addButton, deleteButton), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // 创建工具栏的方法
    private JToolBar createToolBar(JButton... buttons) {
        JToolBar toolBar = new JToolBar();
        for (JButton btn : buttons) {
            toolBar.add(btn);
        }
        return toolBar;
    }
}
