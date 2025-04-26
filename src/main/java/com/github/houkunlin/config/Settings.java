package com.github.houkunlin.config;

import com.github.houkunlin.model.FileType;
import lombok.Data;

import java.util.ArrayList;
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
    private List<FileType> fileTypes = new ArrayList<>();

    public String getResourcesPathAt(String filename) {
        return resourcesPath + "/" + filename;
    }

    public String getJavaPathAt(String filename) {
        return javaPath + "/" + filename;
    }
}
