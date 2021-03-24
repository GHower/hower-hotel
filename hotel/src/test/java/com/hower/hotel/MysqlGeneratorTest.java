package com.hower.hotel;

import com.hower.hotel.generator.MysqlGenerator;
import org.junit.jupiter.api.Test;

public class MysqlGeneratorTest {
    @Test
    public void generator() {
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        mysqlGenerator.generator("customer_info");
        mysqlGenerator.generator("customer_vip");
        mysqlGenerator.generator("department_info");
        mysqlGenerator.generator("order_info");
        mysqlGenerator.generator("room_info");
        mysqlGenerator.generator("room_type");
        mysqlGenerator.generator("staff_info");
    }
    @Test
    public void generatorBySys() {
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        mysqlGenerator.generator("sys_menu");
        mysqlGenerator.generator("sys_menu_resource");
        mysqlGenerator.generator("sys_resource");
        mysqlGenerator.generator("sys_role");
        mysqlGenerator.generator("sys_role_menu");
        mysqlGenerator.generator("sys_role_resource");
        mysqlGenerator.generator("sys_staff_role");
    }
}
