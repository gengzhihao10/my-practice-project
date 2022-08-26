package com.scs.test;

import com.common.utils.JsonUtil;
import com.scs.input.PersonInput;
import org.springframework.http.HttpStatus;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class Test {

//    public void testOcr() throws IOException {
//        // 您的应用id
//        String appid = "**********";
//        //您的密钥
//        String appSecret = "***************";
//        //API path
//        //网关接口地址
//        String url = " http://gateway-http.poc:8080/aiability/v1/ocr";
//        String appkey = appSecret;
//        String gatewayAddress = url.substring(0, StringUtils.ordinalIndexOf(url, "/", 3));
//        //从接口地址中分离出apiPath路径
//        String apiPath = url.substring(StringUtils.ordinalIndexOf(url, "/", 3));
//        //根据路径得到能力名称
//        String capabilityName = getCapabilityName(apiPath);
//        String xCurTime = System.currentTimeMillis() / 1000 + "";
//        //能力名称 长度24位，不足则在后补0
//        StringBuilder stringBuilder = new StringBuilder("aiability-ocr");
//        while (stringBuilder.length() < 24) {
//            stringBuilder.append(0);
//        }
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        String csid = appid + stringBuilder + uuid;
//        String xServerParam = Base64Utils.getBase64Encoder("{"appid":"" + appid + "","csid":"" + csid + ""}");
//        String xCheckSum = DigestUtils.md5Hex(appSecret + xCurTime + xServerParam);
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//        headers.put("appid", appid);
//        headers.put("x-server-param", xServerParam);
//        headers.put("x-checksum", xCheckSum);
//        headers.put("x-curtime", xCurTime);
//        File file = new File("D:json.txt");//定义一个file对象，用来初始化FileReader
//        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
//        BufferedReader bReader = new BufferedReader( new InputStreamReader(new FileInputStream(file),"UTF-8") );//new一个BufferedReader对象，将文件内容读取到缓存
//
//        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
//        String s = "";
//        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//            sb.append(s);//将读取的字符串添加换行符后累加存放在缓存中
//            System.out.println(s);
//        }
//        bReader.close();
//        String str = sb.toString();
//        System.out.println(str );
//        GatewayResponse response = GatewayService.response(appid, appSecret, gatewayAddress, apiPath,sb.toString() , false, headers);
//        if (HttpStatus.SC_OK == response.getHttpStatus()) {
//            String resJson = new String(response.getData());
//            System.out.println("返回结果------->"+resJson);
//        }
//        System.out.println("HttpResponse------->"+response.getHttpResponse());
//    }

//
//    /**
//     * 根据apiPath 得到能力名称
//     *
//     * @param apiPath apiPath
//     * @return 能力名称
//     */
//    private String getCapabilityName(String apiPath) {
//        String[] pathSplit = StringUtils.split(apiPath, "/");
//        if (pathSplit.length == 2 && "v1".equals(pathSplit[0]) && "querycapabilities".equals(pathSplit[1])) {
//            return null;
//        }
//        String capabilityName = "";
//        if (pathSplit[0].length() < 24) {
//            capabilityName = org.apache.commons.lang.StringUtils.rightPad(pathSplit[0], 24, 0);
//        } else if (pathSplit[0].length() > 24) {
//            capabilityName = pathSplit[0].substring(0, 24);
//        }
//        return capabilityName;
//    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        LongAdder longAdder = new LongAdder();

        String test = "ef";
        JsonUtil.jsonToObjcet(test, PersonInput.class);
    }

}
