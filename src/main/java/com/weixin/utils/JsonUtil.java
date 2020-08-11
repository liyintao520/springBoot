package com.weixin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class JsonUtil {

    /**
     * 将数据转换为json数据进行统一处理，比如数据加密，数据加标记
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 将数据转换为json数据进行统一处理，比如数据加密，数据加标记(防止值为null 序列化后没有值)
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj, SerializerFeature s) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 将json数据转换为JSONObject对象
     *
     * @param json
     * @return
     * @see JSONObject
     */
    public static Object parseJson(String json) {
        Object o = null;
        try {
            o = JSON.parse(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    /**
     * 将json数据转换为指定的java对象
     *
     * @param json
     * @param clazz
     * @return
     */
    public static final <T> T parseJson(String json, Class<T> clazz) {
        T o = null;
        try {
            o = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    /**
     * 将json数据转换为集合
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static final <T> List<T> parseJsonStr(String jsonStr, Class<T> clazz) {
        List<T> list = null;
        if (StringUtils.isNotBlank(jsonStr)) {
            if (jsonStr.startsWith("{") && jsonStr.endsWith("}")) {
                list = new ArrayList<T>();
                T t = JSON.parseObject(jsonStr, clazz);
                list.add(t);
            } else if (jsonStr.startsWith("[") && jsonStr.endsWith("]")) {
                list = JSON.parseArray(jsonStr, clazz);
            }
        }
        return list;
    }


    public static String object2json(Object obj) {
        StringBuilder json = new StringBuilder();
        if (obj == null) {
            json.append("\"\"");
        } else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean
                || obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal
                || obj instanceof BigInteger || obj instanceof Byte) {
            json.append("\"").append(string2json(obj.toString())).append("\"");
        } else if (obj instanceof Object[]) {
            json.append(array2json((Object[]) obj));
        } else if (obj instanceof List) {
            json.append(list2json((List<?>) obj));
        } else if (obj instanceof Map) {
            json.append(map2json((Map<?, ?>) obj));
        } else if (obj instanceof Set) {
            json.append(set2json((Set<?>) obj));
        } else {
            json.append(bean2json(obj));
        }
        return json.toString();
    }

    public static String bean2json(Object bean) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        PropertyDescriptor[] props = null;
        try {
            props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
        } catch (IntrospectionException e) {
        }
        if (props != null) {
            Arrays.asList(props).stream().forEach(x -> {
                try {
                    String name = object2json(x.getName());
                    String value = object2json(x.getReadMethod().invoke(bean));
                    json.append(name);
                    json.append(":");
                    json.append(value);
                    json.append(",");
                } catch (Exception e) {
                }
            });
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    public static String list2json(List<?> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            list.forEach(x -> {
                json.append(object2json(x));
                json.append(",");
            });
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    public static String list2json2(List<?> list) {
        StringBuilder json = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(object2json(obj));
                json.append(";");
            }
        }
        return json.toString();
    }

    public static String list2json3(List<?> list) {
        StringBuilder json = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (Object obj : list) {
                json.append(bean2json(obj));
                json.append(";");
            }
            return json.toString();
        } else {
            return null;
        }
    }

    public static String array2json(Object[] array) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (array != null && array.length > 0) {
            for (Object obj : array) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    public static String map2json(Map<?, ?> map) {
        StringBuilder json = new StringBuilder();
        json.append("{");
        if (map != null && map.size() > 0) {
            map.forEach((k, v) -> {
                json.append(object2json(k));
                json.append(":");
                json.append(object2json(v));
                json.append(",");
            });
            json.setCharAt(json.length() - 1, '}');
        } else {
            json.append("}");
        }
        return json.toString();
    }

    public static String set2json(Set<?> set) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (set != null && set.size() > 0) {
            for (Object obj : set) {
                json.append(object2json(obj));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

    public static String string2json(String s) {
        if (s == null)
            return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '/':
                    sb.append("\\/");
                    break;
                default:
                    if (ch >= '\u0000' && ch <= '\u001F') {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                    } else {
                        sb.append(ch);
                    }
            }
        }
        return sb.toString();
    }


    /**
     * json字符串转map
     *
     * @param json
     * @return
     */
    public static Map<String, String> convertMap(String json) {
        Map<String, String> map = new HashMap<String, String>();
        JSONObject jsonObject = JSONObject.parseObject(json);
        Iterator<String> ite = jsonObject.keySet().iterator();
        while (ite.hasNext()) {
            String key = (String) ite.next();
            String value = jsonObject.getString(key);
            map.put(key, value);
        }
        return map;
    }


    /**
     * json字符串转Bean
     *
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object convertBean(String json, Class objClass) {
        Object obj = JSONObject.parseObject(json, objClass);
        return obj;
    }


    /**
     * json字符串转List
     *
     * @param json
     * @return
     */
    public static List<Object> convertList(String json, Class objClass) {
        String[] strArray = json.split(";");
        List<Object> objList = new ArrayList<Object>();
        Object obj = null;
        for (String str : strArray) {
            obj = convertBean(str, objClass);
            objList.add(obj);
        }
        return objList;
    }
}