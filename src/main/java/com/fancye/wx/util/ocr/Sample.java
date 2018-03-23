/**
 * 
 */
package com.fancye.wx.util.ocr;

import java.io.IOException;

/**
 * @author Fancye
 *
 */
public class Sample {
	
    //设置APPID/AK/SK
	public static final String APP_ID = "9226350";
    public static final String API_KEY = "6NpMNQPBz1HgZgwc5k5nMXaj";
    public static final String SECRET_KEY = "PZThTA1GccLE0vK6R1uPLRqk4qTPGor7";

    public static void main(String[] args) throws IOException {
    	OcrUtil.bankcard("e://2.jpg");
    }
}
