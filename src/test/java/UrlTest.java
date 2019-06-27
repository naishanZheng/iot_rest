import com.iot.base.utils.HttpClientUtil;
import com.iot.base.utils.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengnaishan
 * @date 2019/4/15 0015
 * @describe :
 */
public class UrlTest {

    public static void main(String[] args) {
        //登录参数
        String loginJson = "{\"serverId\":\""+"jiayingdev01"+"\",\"password\":\""+"jsj2018"+"\"}";
        //登录
        String json = HttpClientUtil.doPostJson("https://www.easy-iot.cn/idev/3rdcap/server/login", loginJson);
        Map loginResult = JsonUtils.jsonToPojo(json, Map.class);
        //获取登录token
        String accessToken = (String) loginResult.get("accessToken");

        Map headerMap = new HashMap<String, String>();
        headerMap.put("serverID", "jiayingdev01");
        headerMap.put("accessToken", accessToken);
       // headerMap.put("callbackUrl","http://120.78.137.20:8080/iot-server/iotServer");
        Map body = new HashMap();
        String response = HttpClientUtil.doPostJsonWithHeader("https://www.easy-iot.cn/idev/3rdcap/unsubscribe-service-address",
                JsonUtils.objectToJson(body), headerMap );
        System.out.println(response);
    }
}
