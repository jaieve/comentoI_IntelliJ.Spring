package com.example.comento.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//JacksonUtil 클래스는 Object를 받아서 JSON으로 return 해주는 클래스이다. Test에서 사용
public class JacksonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }
}
