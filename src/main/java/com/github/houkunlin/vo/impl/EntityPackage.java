package com.github.houkunlin.vo.impl;

import com.github.houkunlin.model.FileType;
import lombok.Getter;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * 实体类对象的包信息
 *
 * @author HouKunLin
 * @date 2020/7/5 0005 15:12
 */
@Getter
public class EntityPackage extends BaseTypeMap<EntityPackageInfo, EntityName> {
    /**
     * 实体类字段所需要导入的包列表
     */
    private final HashSet<String> list = new HashSet<>();
    private String toString = "";

    public void add(String fullPackageName) {
        if (fullPackageName.startsWith("java.lang.")) {
            return;
        }
        list.add(fullPackageName);
    }

    @Override
    public EntityPackageInfo mapping(FileType fileType, EntityName entityName) {
        return new EntityPackageInfo(fileType.getPackageName()
                                             .toString(), entityName.get(fileType.getType()));
    }

    public void clear() {
        list.clear();
        toString = "";
    }

    @Override
    public String toString() {
        if (toString == null || toString.isBlank()) {
            toString = list.stream().map(item -> String.format("import %s;\n", item)).collect(Collectors.joining());
        }
        return toString;
    }

}
