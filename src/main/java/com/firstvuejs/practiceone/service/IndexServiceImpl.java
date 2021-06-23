package com.firstvuejs.practiceone.service;

import java.util.List;
import java.util.Map;

import com.firstvuejs.practiceone.mapper.IndexMapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class IndexServiceImpl implements IndexService {

    @Autowired
    public IndexMapper indexMapper;

    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "com.firstvuejs.practiceone.mapper.IndexMapper.";

    @Override
    public List<Map<String, Object>> selectCityList() throws Exception {
        // TODO Auto-generated method stub
        return sqlSession.selectList(NAMESPACE + "selectCityList");
    }

    
}
