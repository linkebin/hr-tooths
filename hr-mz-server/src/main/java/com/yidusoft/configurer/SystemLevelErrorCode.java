package com.yidusoft.configurer;

import java.util.HashMap;
import java.util.Map;

/**系统级错误码
 * Created by smy on 2018/1/8.
 */
public class SystemLevelErrorCode {

    public final static String CLIENT_ID_LACK = "1000000001L";
    public final static String CLIENT_ID_MSG_LACK = "缺少client_id参数！";

    public final static String CLIENT_SECRET_LACK = "1000000002L";
    public final static String CLIENT_SECRET_MSG_LACK = "缺少client_secret参数！";

    public final static String CLIENT_ID_ERROR = "1000000010L";
    public final static String CLIENT_ID_MSG_ERROR = "参数client_id错误！";

    public final static String CLIENT_SECRET_ERROR = "1000000011L";
    public final static String CLIENT_SECRET_MSG_ERROR = "参数client_secret错误！";

    public final static String TOKEN_LACK = "1000000088L";
    public final static String TOKEN_MSG_LACK = "缺少token参数！";

    public final static String TOKEN_ERROR = "1000000089L";
    public final static String TOKEN_MSG_ERROR = "token无效或者已过期！";

    public final static String TIMESTAMP_LACK = "1000000090L";
    public final static String TIMESTAMP_LACK_MSG = "timestamp参数不正确或者已经超时！";

    public final static String SIGN_ERROR = "1000000091L";
    public final static String SIGN_ERROR_MSG = "签名验证失败！";

    public final static String SERVER_ERROR = "1000000000L";
    public final static String SERVER_ERROR_MSG = "服务器错误！";


    public final static String SERVER_INSIDE_INTERFACE_ERROR ="2000000103L";
    public final static String SERVER_INSIDE_INTERFACE_MSG ="内部接口调用失败，请联系管理员";

    public static Map<String,Object> getErrorCode(String code,String msg) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("error",code);
        map.put("error_description",msg);
        return map;
    }

    public static Map<String,Object> getSucceedCode(String code) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("resultCode",code);
        return map;
    }

}
