package com.github.houkunlin.config;

import com.github.houkunlin.model.FileType;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * 设置信息
 *
 * @author HouKunLin
 * @date 2020/4/3 0003 14:56
 */
@Data
public class Settings {
    /**
     * 项目路径
     */
    private String projectPath;
    /**
     * Java代码路径
     */
    private String javaPath = "src/main/java";
    /**
     * 资源文件路径
     */
    private String resourcesPath = "src/main/resources";
    /**
     * 文件类型
     */
    private List<FileType> fileTypes;

    public String getResourcesPathAt(String filename) {
        return resourcesPath + "/" + filename;
    }

    public String getJavaPathAt(String filename) {
        return javaPath + "/" + filename;
    }

    /**
     * 获取文件路径
     *
     * @param path_    路径
     * @param filename 文件名
     * @return 文件路径
     * @since 2.8.4
     */
    public String getPathAt(String path_, String filename) {
        var path = (path_ == null || path_.isBlank()) ? javaPath : path_;
        return path + "/" + filename;
    }

    public List<FileType> getFileTypes() {
        if (fileTypes == null) {
            initFileTypes();
        }
        return fileTypes;
    }

    private void initFileTypes() {
        fileTypes = Lists.newArrayList(
            FileType.of("entity", "Entity", "com.example.entity"),
            FileType.of("dao", "Repository", "com.example.repository"),
            FileType.of("service", "Service", "com.example.service"),
            FileType.of("serviceImpl", "ServiceImpl", "com.example.service.impl"),
            FileType.of("controller", "Controller", "com.example.controller")
        );
    }
}
