package com.shop.util;

import java.util.Random;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
/**
 * 
 * @ClassName: VerCodeUtil 
 * @Description: TODO(生成验证码工具类) 
 * @author 威 
 * @date 2017年5月9日 下午6:48:10 
 *
 */
public class VerCodeUtil{
	private static int Width=90;
	private static int Height=39;
	private static int digit = 4 ;
	private static String str="abcdefghijklmnoqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 设置验证码位数
	 * @param digitS
	 */
	public VerCodeUtil(int digitS) {
		this.digit = digitS ;
	}
	/**
	 * 
	 * @Title: createImage 
	 * @Description: TODO(生成验证码) 
	 * @return
	 * Object[]
	 *
	 */
    public static Object[] createVerCode(){
    	StringBuffer sbs=new StringBuffer("");
    	//创建空白画面
    	BufferedImage img1=new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
    	Graphics g=img1.getGraphics();
    	g.setColor(getRandomColor(240, 255)) ;
    	g.fillRect(0, 0, Width, Height);
    	String varCode = "" ;
    	//写入验证码到画布上
    	for(int i=0;i<digit;i++){
    		StringBuffer sb=new StringBuffer(str) ;
    	    int n = getRandomNum(0, 61) ;
    		g.setColor(getRandomColor(0, 180)) ;
    		System.out.println(sb.charAt(n));
    		g.setFont(new Font(null,Font.BOLD+Font.ITALIC,getRandomNum(24, 28)));
    		g.drawString(sb.charAt(n)+"",(i*Width/4)-4, getRandomNum(14,38));
    		//toString().toLowerCase() ;
    		sbs.append(sb.charAt(n)) ;
    	}
    	//化干扰线
    	for(int i=0;i<13;i++){
    		g.setColor(getRandomColor(140, 200));
    		g.drawLine(getRandomNum(0, Width),getRandomNum(0, Height),getRandomNum(0, Width),getRandomNum(0, Height));
    	}
    	return new Object[]{img1,sbs.toString().toLowerCase()};
    }
     /**
	  * Desc: 获取指定范围的随机色</br>
	  * param: @param from
	  * param: @param to
	  * param: @return</br>
	  * return: Color</br>
	  */
	 private static Color getRandomColor(int from,int to){
		 Random ra=new Random();
		 int r = from+ra.nextInt(to-from);
		 int g = from+ra.nextInt(to-from);
		 int b = from+ra.nextInt(to-from);
		 return Color.getHSBColor(r,g,b);
	 }
	 /**
	  * Desc: 获取指定范围的随机数</br>
	  * param: @param from
	  * param: @param to
	  * param: @return</br>
	  * return: int</br>
	  */
	 private static int getRandomNum(int from,int to){
		 Random ra = new Random();
		 return from+ra.nextInt(to-from);
	 }
	 /**
	  * 
	  * @Title: store_img 
	  * @Description: TODO(将生成图片写入磁盘) 
	  * @param bur
	  * void
	  *
	  */
	private static void store_img(BufferedImage bur){
		try{
			File f=new File("d:/jpg.jpg");	
			OutputStream os=new FileOutputStream(f) ;
			ImageIO.write(bur, "jpg", os) ;
		}catch(IOException e){e.printStackTrace() ;}
	}
	/**
	 * 
	 * @Title: go_image 
	 * @Description: TODO(总调度) 
	 * void
	 *
	 */
	 public static void go_image(){
		 Object[] obj=createVerCode();
		 BufferedImage img=(BufferedImage)obj[0];//获取画好的图片
		 String str1=(String)obj[1];//获取验证码
		 System.out.println("str1"+str1);
		 System.out.println(GetBase64Util.getBaseToImage(img));
		 store_img(img) ;
	 }
	 public static void main(String[] args) {
		 go_image();
	 }
}
