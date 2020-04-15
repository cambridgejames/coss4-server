package cn.net.powerinv.coss4.basic.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公共返回类
 * @author PengJQ
 * @date 2019-12-18
 */
public class CommonResultUtil {

    /**
     * 返回默认的成功请求信息
     * @return 默认的成功请求信息
     */
    public static Map<String, Object> returnTrue() {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", 0);
        returnMap.put("msg", "请求成功");
        return returnMap;
    }

    /**
     * 返回带有自定义的Object型数据的成功请求信息
     * @param msg 自定义的Object型数据
     * @return 成功请求信息，传入的msg将被放置在data字段中
     */
    public static Map<String, Object> returnTrue(Object msg) {
        Map<String, Object> returnMap = returnTrue();
        returnMap.put("data", msg);
        return returnMap;
    }

    /**
     * 返回带有自定义的Object型数据列表的分页成功请求信息
     * @param total 列表大小
     * @param list 分页信息
     * @return 成功请求信息，传入的msg将被放置在data字段中
     */
    public static <T> Map<String, Object> returnTrue(Long total, List<T> list) {
        Map<String, Object> returnMap = returnTrue();
        Map<String, Object> msg = new HashMap<>();
        msg.put("total", total);
        msg.put("list", list);
        returnMap.put("data", msg);
        return returnMap;
    }

    /**
     * 返回带有自定义的Object型数据列表的分页成功请求信息
     * @param total 列表大小
     * @param current 当前页码
     * @param list 分页信息
     * @return 成功请求信息，传入的msg将被放置在data字段中
     */
    public static <T> Map<String, Object> returnTrue(Long total, Integer current, List<T> list) {
        Map<String, Object> returnMap = returnTrue();
        Map<String, Object> msg = new HashMap<>();
        msg.put("total", total);
        msg.put("current", current);
        msg.put("list", list);
        returnMap.put("data", msg);
        return returnMap;
    }

    /**
     * 返回错误信息
     * @param code 错误代码
     * @return 错误信息，包含错误代码和错误提示
     */
    public static Map<String, Object> returnFalse(MessageCode code) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", code.getCode());
        returnMap.put("msg", code.getMsg());
        return returnMap;
    }
}
