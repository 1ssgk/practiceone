package com.firstvuejs.practiceone.service.users;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import org.apache.ibatis.session.SqlSession;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "userMapper.";

    
    @Override
    public List<Map<String, Object>> selectUserList() throws Exception {
        // TODO Auto-generated method stub
        System.err.println("UserServiceImpl");
        return sqlSession.selectList(NAMESPACE + "selectUserList");
    }

    @Override
    public Integer insertUser(String data) throws Exception {
        // TODO Auto-generated method stub
        Gson gson = new Gson();
        try {
            System.err.println("insertUser service 들어옴");
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(data, new TypeReference<Map<String, String>>() {});
            
            Map<String,Object> paramMap = Maps.newHashMap();

            // 유저 중복체크 Go
            int dupleChkUser = this.duplChkUser(map.get("id"));
            int insertUser = 0;

            if( dupleChkUser == 1 ){  // 중복 O
                insertUser = 0;
            }else{                  // 중복 X
                paramMap.put("id"       , map.get("id"));
                paramMap.put("name"     , map.get("name"));
                paramMap.put("password" , map.get("password"));
                paramMap.put("rem"      , map.get("rem"));

                insertUser = sqlSession.insert(NAMESPACE + "insertUser",paramMap);

                System.out.println("insertUser : "+insertUser);
            }
            return insertUser;
        } catch (Exception ex) {
            return 0;
        }
    }

    // 유저 중복체크
    @Override
    public Integer duplChkUser(String userId) {
        // TODO Auto-generated method stub
        /*  
        ID 중복체크 확인
        return
            1 = id 중복 O
            0 = id 중복 X
        */   
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("id", userId);

        int userCnt = sqlSession.selectOne(NAMESPACE + "duplChkUser",paramMap);
        
        return userCnt;
    }

    // 로그인 할 경우
    @Override
    public Map<String, Object> checkLogin(Map<String, Object> map) throws Exception {
        // TODO Auto-generated method stub

        return sqlSession.selectOne(NAMESPACE + "checkLogin",map);
    }
    
}
