package com.fun.system.test;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-03-20 23:43
 **/
public class StatementTest {
    public static void main(String[] args) {
        testArrayList();
//        treeNodeTest();

//        System.out.println(Integer.MAX_VALUE>>20);
//        sqlTest();
//        String name = "fun-service-demo-mybatis";
//        int i = name.lastIndexOf("fun-service-");
//        System.out.println(name.replace("fun-service-", ""));
//        System.out.println(name.substring(i));
    }

    private static void testArrayList() {
        ArrayList<String> list = new ArrayList<>(0);
        //1,2,3
        List<String> list1 = Arrays.asList();
        list1.add("a");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("i");
        list.add("j");
        System.out.println(list.toString());

    }

    private static void treeNodeTest() {
        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();

        nodeList.add(new TreeNode<>("1", "0", "系统管理", 5));
        nodeList.add(new TreeNode<>("11", "1", "用户管理", 222222));
        nodeList.add(new TreeNode<>("111", "11", "用户添加", 0));
        nodeList.add(new TreeNode<>("2", "0", "店铺管理", 1));
        nodeList.add(new TreeNode<>("21", "2", "商品管理", 44));
        nodeList.add(new TreeNode<>("221", "2", "商品管理2", 2));
        // 0表示最顶层的id是0
        List<Tree<String>> treeList = TreeUtil.build(nodeList, "0");
        System.out.println(treeList.toString());

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
            int cnt = 0;
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


    void test() {
        List<String> list = new ArrayList<>();
        list.add("a");
        AtomicReference<StringBuilder> sb = new AtomicReference<>(new StringBuilder());
        AtomicInteger count = new AtomicInteger(0);
        list.forEach(val -> {
                    if (count.get() == 0) {
                        // insert into tb values
                        sb.get().append("insert into tb values");
                    }
                    sb.get().append("(").append(val).append(")").append(",");
                    //save To DB
                    if (count.get() == 1024) {
                        saveToDb(sb.get().toString());
                        sb.set(new StringBuilder());
                    }
                }
        );
        //
        sb.get().substring(0, sb.get().length() - 1);
        saveToDb(sb.get().toString());
    }

    private void saveToDb(String toString) {
    }

}

