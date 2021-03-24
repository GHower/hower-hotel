package com.hower.hotel.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * <p>
 * Mysql代码生成器
 * </p>
 */
public class MysqlGenerator extends SuperGenerator {
    /**
     * <p>
     * MySQL generator
     * </p>
     */
    public void generator(String tableName) {

        // 代码生成器
        AutoGenerator mpg = getAutoGenerator(tableName);
        mpg.execute();
        if (tableName == null) {
            System.err.println(" Generator Success !");
        } else {
            System.err.println(" TableName【 " + tableName + " 】" + "Generator Success !");

        }
    }

}
