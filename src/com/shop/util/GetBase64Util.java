package com.shop.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

/**
 * 
 * @ClassName: GetBase64 
 * @Description: TODO(获取base64为编码) 
 * @author 威 
 * @date 2017年5月9日 下午6:55:57 
 *
 */
public class GetBase64Util {
	public static String getBaseToImage(BufferedImage buf){
		ByteArrayOutputStream output = null;
		try{
			output = new ByteArrayOutputStream();
			ImageIO.write(buf ,"jpg" ,output);
		}catch(Exception e){
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(output.toByteArray()).toString();
	}
}
