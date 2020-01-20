package com.company.project.configurer.shiro.cache;


import com.alibaba.fastjson.JSONObject;
import com.company.project.core.util.LoggerUtils;

import java.io.*;

/**
 * 序列化与反序列化
 */
@SuppressWarnings("unchecked")
public class SerializerUtils {
    static final Class<?> CLAZZ = SerializerUtils.class;


    //序列化为字节
    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (Exception e) {
            LoggerUtils.fmtError(CLAZZ, e, "serialize error %s", value.toString());
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }

    //序列化为json字符串
    public static String jsonSerialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }

        String rv = null;
        try {
            rv = JSONObject.toJSONString(value);
        } catch (Exception e) {
            LoggerUtils.fmtError(CLAZZ, e, "serialize error %s", value.toString());
        }
        return rv;
    }


    //反序列化为json字符串
    public static <T> T jsonDeserialize(String value, Class<T>... requiredType) {

        Object rv = null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(value);
            rv = JSONObject.toJavaObject(jsonObject, requiredType[0]);
        } catch (Exception e) {
            LoggerUtils.fmtError(CLAZZ, e, "deserialize error %s", value.toString());
        }
        return (T) rv;
    }

    public static Object jsonDeserialize(String value) {
        return jsonDeserialize(value, Object.class);
    }

    public static Object deserialize(byte[] in) {
        return deserialize(in, Object.class);
    }

    public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
        Object rv = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                rv = is.readObject();
            }
        } catch (Exception e) {
            LoggerUtils.fmtError(CLAZZ, e, "serialize error %s", in);
        } finally {
            close(is);
            close(bis);
        }
        return (T) rv;
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                LoggerUtils.fmtError(CLAZZ, "close stream error");
            }
        }
    }


}

