package cn.bptop.device.until;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 转json
 */
public class Json
{
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static String getJson(Object object)
    {
        try
        {
            return MAPPER.writeValueAsString(object);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T jsonToObject(String jsonData, Class<T> beanType)
    {
        try
        {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
