/**
 * 
 */
package com.fancye.wx.util.ocr;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Util;

/**
 * 百度OCR文档:http://ai.baidu.com/docs#/OCR-Java-SDK/top
 * 
 * @author Fancye
 *
 */
public class OcrUtil {

	public static final String APP_ID = "9226350";
    public static final String API_KEY = "6NpMNQPBz1HgZgwc5k5nMXaj";
    public static final String SECRET_KEY = "PZThTA1GccLE0vK6R1uPLRqk4qTPGor7";
    
    private static final AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    
    /**
     * 通用文字识别接口 
     * 用户向服务请求识别某张图中的所有文字
     * 
     * @param imagePath 本地图片路径
     * @throws IOException 
     */
    public static void basicGeneral(String imagePath) throws IOException {
    	// 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        
        
        // 参数为本地图片路径
//        String image = "test.jpg";
        JSONObject res = client.basicGeneral(imagePath, options);
        System.out.println(res.toString(2));

        // 参数为本地图片二进制数组
        byte[] file = Util.readFileByBytes(imagePath);
        res = client.basicGeneral(file, options);
        System.out.println(res.toString(2));
    }
    
    /**
     * 通用文字识别接口 
     * 用户向服务请求识别某张图中的所有文字
     * 
     * @param url 图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，
     * 		最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式，当image字段存在时url字段失效
     */
    public static void basicGeneralUrl(String url) {
    	// 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("language_type", "CHN_ENG");
        options.put("detect_direction", "true");
        options.put("detect_language", "true");
        options.put("probability", "true");
        
    	// 通用文字识别, 图片参数为远程url图片
        JSONObject res = client.basicGeneralUrl(url, options);
        System.out.println(res.toString(2));
    }
    
    /**
     * 身份证识别
     * 用户向服务请求识别身份证，身份证识别包括正面和背面
     * 
     * @param image 本地图片路径
     * @throws IOException
     */
    public static String idcard(String image) throws IOException {
    	// 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("detect_direction", "true");
        options.put("detect_risk", "false");
        
        String idCardSide = "back";
        
        // 参数为本地图片路径
//        String image = "test.jpg";
        JSONObject res = client.idcard(image, idCardSide, options);
        
        return res.toString();

        // 参数为本地图片二进制数组
//        byte[] file = Util.readFileByBytes(image);
//        res = client.idcard(file, idCardSide, options);
//        System.out.println(res.toString(2));
    }
    
    /**
     * 银行卡识别
     * 识别银行卡并返回卡号和发卡行
     * @param image 本地图片路径或者图片二进制数据
     * @throws IOException 
     */
    public static void bankcard(String image) throws IOException {
    	// 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        
        
        // 参数为本地图片路径
//        String image = "test.jpg";
        JSONObject res = client.bankcard(image, options);
        System.out.println(res.toString(2));

        // 参数为本地图片二进制数组
//        byte[] file = Util.readFileByBytes(image);
//        res = client.bankcard(file, options);
//        System.out.println(res.toString(2));
    }
    
}
