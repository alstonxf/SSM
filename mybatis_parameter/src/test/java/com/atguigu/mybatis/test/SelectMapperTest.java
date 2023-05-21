package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Date:2022/6/28
 * Author:ybc
 * Description:
 */
public class SelectMapperTest {

    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> list = mapper.getAllUser();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void testGetCount(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Integer count = mapper.getCount();
        System.out.println(count);
    }

    @Test
    public void testGetUserByIdToMap(){
        //当查询结果只有1条时，map中的key是字段名，Object是对应的字段值
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> map = mapper.getUserByIdToMap(6);
        //{password=123456, gender=男, id=1, age=23, email=12345@qq.com, username=admin}
        if (map.isEmpty()==false){
            for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                System.out.println(map);
                System.out.println(stringObjectEntry.getKey()+":"+stringObjectEntry.getValue());
            }
        }

    }

    @Test
    public void testGetAllUserToMap(){
        //当查询结果有多条时，map中的key是指定的唯一的字段值(注意一定要是唯一值，最好是主键，否则会丢数据)，Object是对应的整条记录。
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        /*List<Map<String, Object>> list = mapper.getAllUserToMap();
        System.out.println(list);*/
        /**
         * {
         * 1={password=123456, gender=男, id=1, age=23, email=12345@qq.com, username=admin},
         * 2={password=123, gender=男, id=2, age=23, email=12345@qq.com, username=zhangsan},
         * 3={password=123456, gender=女, id=3, age=33, email=123@qq.com, username=root},
         * 4={password=123, id=4, username=lisi}
         * }
         */
        Map<Integer, Object> map = mapper.getAllUserToMap();
//        System.out.println(map);

        if (map.isEmpty()==false){
            for (Map.Entry<Integer, Object> stringObjectEntry : map.entrySet()) {
                System.out.println(stringObjectEntry);
//                System.out.println(stringObjectEntry.getKey());
//                System.out.println(stringObjectEntry.getValue());
            }
        }
    }

    @Test
    public  void testGetAllUserToMap2(){
        SqlSessionUtil sqlSessionUtil = new SqlSessionUtil();
        SqlSession sqlSession1 = sqlSessionUtil.getSqlSession();
        SelectMapper mapper1 = sqlSession1.getMapper(SelectMapper.class);
        List<Map<Integer, Object>> allUserToMap2 = mapper1.getAllUserToMap2();
        for (Map<Integer, Object> integerObjectMap : allUserToMap2) {
            System.out.println(integerObjectMap.entrySet());
        }
    }

}
