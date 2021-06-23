package com.firstvuejs.practiceone.service.login;

import java.util.List;
import java.util.Map;

import com.firstvuejs.practiceone.mapper.UserMapper;
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
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "loginMapper.";

    // 로그인 할 경우
    @Override
    public Map<String, Object> selectLoginData(Map<String, Object> map) throws Exception {
        // TODO Auto-generated method stub

        return sqlSession.selectOne(NAMESPACE + "selectLoginData",map);
    }
    
}
