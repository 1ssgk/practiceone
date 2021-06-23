package com.firstvuejs.practiceone.service.users;

import java.util.List;
import java.util.Map;

public interface UserService {
    public List<Map<String,Object>> selectUserList() throws Exception;
    public Integer insertUser(String data) throws Exception;
    public Integer duplChkUser(String userId) throws Exception;
    public Map<String,Object> checkLogin(Map<String,Object> map) throws Exception;
    

}
