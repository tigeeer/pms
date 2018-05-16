package com.wangjx.pms.constant;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/23
 * Time: 16:15
 */
public enum SystemProperties {
    JAVA_VERSION("java.version"),
    OS_NAME("os.name"),
    OS_VERSION("os.version"),
    DATABASE_NAME("database.name"),
    DATABASE_VERSION("database.version");

    private String propertyName;

    SystemProperties(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
