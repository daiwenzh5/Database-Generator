package com.github.houkunlin.ui.win.table;

import com.github.houkunlin.config.Settings;
import com.github.houkunlin.model.ColumnSpec;
import com.github.houkunlin.model.FileType;
import com.github.houkunlin.model.GenericTableModel;
import com.github.houkunlin.util.PluginUtils;
import com.intellij.openapi.actionSystem.ActionToolbarPosition;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.table.JBTable;
import com.intellij.util.ui.JBFont;
import com.intellij.util.ui.components.JBComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.util.List;

/**
 * 文件类型表格
 *
 * @author daiwenzh5
 * @since 1.0
 */
public class FileTypeTable implements JBComponent<FileTypeTable> {

    private final JPanel container;

    private final GenericTableModel<FileType> tableModel;

    private final Settings settings;

    public FileTypeTable(Settings settings) {
        this.settings = settings;
        var table = new JBTable();
        this.tableModel = createFileTypeTableModel(settings.getFileTypes(), table);
        this.container = createPanel(table);
    }

    private @NotNull JPanel createPanel(JBTable table) {
        return ToolbarDecorator.createDecorator(table)
                               .setToolbarPosition(ActionToolbarPosition.TOP)
                               .setAddAction(btn -> tableModel.addRows(FileType.of("", "", "", ".java", settings.getJavaPath(), true)))
                               .setRemoveAction(btn -> {
                                   if (table.getSelectedRows().length == 0) {
                                       return;
                                   }
                                   tableModel.removeRows(table.getSelectedRows());
                               })
                               .createPanel();
    }


    private @NotNull GenericTableModel<FileType> createFileTypeTableModel(List<FileType> list, JBTable table) {
        var editorTableCellEditor = new EditorTableCellEditor();
        var extTableCellEditor = new ComboBoxTableCellEditor<>(".java", ".kt", ".xml");
        var pathTableCellEditor = new ComboBoxTableCellEditor<>(settings.getJavaPath(), settings.getResourcesPath(), true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        return new GenericTableModel<>(list)
            .addColumn(ColumnSpec.of("类型", String.class, FileType::getType, FileType::setType))
            .addColumn(ColumnSpec.of("后缀", String.class, FileType::getSuffix, FileType::setSuffix))
            .addColumn(ColumnSpec.of("包名", String.class, FileType::getPackageName, FileType::setPackageName)
                                 .withWidth(120)
                                 .withCellEditor(editorTableCellEditor))
            .addColumn(ColumnSpec.of("拓展名", String.class, FileType::getExt, FileType::setExt)
                                 .withCellEditor(extTableCellEditor)
                                 .withWidth(50))
            .addColumn(ColumnSpec.of("存储路径", String.class, FileType::getPath, FileType::setPath)
                                 .withCellEditor(pathTableCellEditor)
                                 .withPlaceholder("默认为存储路径")
                                 .withWidth(200))
            .addColumn(ColumnSpec.of("允许覆盖", Boolean.class, FileType::isOverride, FileType::setOverride)
                                 .withWidth(30))
            .bindTable(table);
    }

    @Override
    public FileTypeTable withBorder(Border border) {
        container.setBorder(border);
        return this;
    }

    @Override
    public FileTypeTable withFont(JBFont font) {
        container.setFont(font);
        return this;
    }

    @Override
    public FileTypeTable andTransparent() {
        container.setOpaque(false);
        return this;
    }

    @Override
    public FileTypeTable andOpaque() {
        container.setOpaque(true);
        return this;
    }

    public JComponent getComponent() {
        return container;
    }

    /**
     * 重置表格数据
     */
    public void reset() {
        var list = PluginUtils.loadConfig()
                              .getSettings()
                              .getFileTypes();
        if (list == null || list.isEmpty()) {
            this.tableModel.clear();
            return;
        }
        this.tableModel.clear()
                       .addRows(list.toArray(new FileType[]{}));
    }
}
