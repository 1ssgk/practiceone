package com.firstvuejs.practiceone.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface IndexMapper {
    List<Map<String, Object>> selectCityList();
    
}