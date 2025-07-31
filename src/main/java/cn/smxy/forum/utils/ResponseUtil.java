package cn.smxy.forum.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    /**
     *
     * @param response 响应对象
     * @param data 要返回的对象
     * @throws IOException
     */
    public static void responseToWeb(HttpServletResponse response, Object data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 使用jackson的对象转JSON字符串
        String resultStr = mapper.writeValueAsString(data);
        // 设置返回的数据类型和编码格式
        response.setContentType("application/json;charset=utf-8");
        // 通过写出流发送给调用者
        response.getWriter().write(resultStr);
    }
}
