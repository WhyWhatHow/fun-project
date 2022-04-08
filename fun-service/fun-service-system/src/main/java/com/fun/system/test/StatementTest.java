package com.fun.system.test;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-03-20 23:43
 **/
public class StatementTest {
    public static void main(String[] args) {
//        sqlTest();
        String name = "fun-service-demo-mybatis";
        int i = name.lastIndexOf("fun-service-");
        System.out.println(name.replace("fun-service-", ""));
        System.out.println(name.substring(i));
    }

    private static void sqlTest() {
        DataSource dataSource = getDataSource();
        Object result = null;
        String sql = "select m.* from sys_role_menu rm left join sys_menu m on rm.menu_id = m.menu_id where rm.role_id=? and m.del_flag = 0";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, 1l);
//           preparedStatement.set
            ResultSet resultSet = preparedStatement.executeQuery();
            // 处理结果集, 对结果集 与xml方法 返回值做映射,
            int cnt =0 ;
            while (resultSet.next()) {
                long aLong = resultSet.getLong(1);
                cnt++;
            }
            System.out.println(cnt);
            int row = resultSet.getRow();
//            preparedStatement
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private static DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername("root");
        dataSource.setPassword("aa12321.");

        dataSource.setUrl("jdbc:mysql://centos-product:3306/fun_system?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true");
        return dataSource;
    }

}

