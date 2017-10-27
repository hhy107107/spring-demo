package me.smallyellow.hhy.config.db;

/**
 * 作用：
 * 1、保存一个线程安全的DatabaseType容器
 * @author hhy
 *
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }
}
