package com.github.houkunlin.config;

import com.google.common.base.CaseFormat;
import lombok.Data;

/**
 * 构建参数
 *
 * @author HouKunLin
 * @date 2020/4/3 0003 21:57
 */
@Data
public class Options {
    /**
     * 覆盖Java文件
     */
    private boolean overrideJava = true;
    /**
     * 覆盖XML文件
     */
    private boolean overrideXml = true;
    /**
     * 覆盖其他文件
     */
    private boolean overrideOther = true;
    /**
     * 数据库字段风格类型
     */
    private int dbFieldStyleType = 0;

    public CaseFormat obtainCaseFormat() {
        if (dbFieldStyleType == 0) {
            return CaseFormat.LOWER_UNDERSCORE;
        } else if (dbFieldStyleType == 1) {
            return CaseFormat.UPPER_UNDERSCORE;
        } else if (dbFieldStyleType == 2) {
            return CaseFormat.LOWER_CAMEL;
        } else if (dbFieldStyleType == 3) {
            return CaseFormat.UPPER_CAMEL;
        } else if (dbFieldStyleType == 4) {
            return CaseFormat.LOWER_HYPHEN;
        } else {
            return CaseFormat.LOWER_UNDERSCORE;
        }
    }
}
