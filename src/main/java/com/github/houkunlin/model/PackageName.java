package com.github.houkunlin.model;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.jetbrains.annotations.NotNull;

/**
 * 一个描述包名的字符序列类型，用于解决根据类型覆盖{@link javax.swing.JTable}中的默认编辑器的场景。
 *
 * @author daiwenzh5
 * @since 1.0
 */
@AllArgsConstructor
@Setter
public class PackageName implements CharSequence {

    @Delegate
    private String value;

    @Override
    public @NotNull String toString() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PackageName other) {
            return this.value.equals(other.value);
        }
        return value.equals(obj);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
