package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SpecialSQLMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.*;
import java.util.List;

/**
 * Date:2022/6/29
 * Author:ybc
 * Description:
 */
public class SpecialSQLMapperTest {

    @Test
    public void testGetUserByLike(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> list = mapper.getUserByLike("a");
                for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testDeleteMoreUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        mapper.deleteMoreUser("6,10");
    }

    @Test
    public void testGetUserList(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        List<User> list = mapper.getUserList("t_user");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);
        User user = new User(null,"xiaoming","123456",23,"男","123@qq.com");
        mapper.insertUser(user);
        System.out.println(user);
    }


    public void testJDBC(){
        try {
            Class.forName("");
            Connection connection = DriverManager.getConnection("","","");
            /*String sql = "select * from t_user where username like '%?%'";
            PreparedStatement ps = connection.prepareStatement(sql);*/
            //ps.setString(1, "a");
            String sql = "insert into t_user values()";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testJDBC2() {
        //测试获取自增的主键
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssm", "root", "123456");

            // 按需修改以下代码段
            String sql = "insert into t_user (id,username,password,age,gender,email) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,null);
            ps.setString(2,"xiaohong");
            ps.setString(3,"123456");
            ps.setString(4,"23");
            ps.setString(5,"女");
            ps.setString(6,"1235@qq.com");
            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(1);

            System.out.println("Generated ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
