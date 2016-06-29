/*
 * 功能：抓取图像像素到数组，并对RGB值和alpha值进行重新计算和赋值
 * 2016年6月26日21:27:17
 * 其实很简单
 * PixelGrabber类和MemoryImageSource类
 * */

import java.awt.*;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

import javax.swing.JFrame;


public class Image1 extends JFrame {
	int x,w,h,i=0,j=0;
	int[] pixels;
	Image img,cropped;
	
	
	
	
	public Image1(){
		setSize(500,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//应该是可有可无
		MediaTracker tracker1 = new MediaTracker(this);
		img = Toolkit.getDefaultToolkit().getImage("dog.jpg");
		// 向此媒体跟踪器正在跟踪的图像列表添加一个图像。
		tracker1.addImage(img, 0);
		try {
			//加载指定标识符的图像
			tracker1.waitForID(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		w = img.getWidth(this);
		h = img.getHeight(this);
		pixels = new int[w*h];
		//PixelGrabber用来抓取图像像素
		PixelGrabber pg;
		//抓取图像的像素到数组中    倒数的0表示存放第一个像素的位置，w表示每行的像素个数
		pg = new PixelGrabber(img, 0, 0, w, h, pixels,0,w);
		try {
			//抓取图像到 pixels中
			pg.grabPixels();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ColorModel cm = ColorModel.getRGBdefault();
		//对RGB值和alpha值进行重新计算和赋值
		for(x = 0;x < w*h; x++){
			int alpah = 100;
			int red = cm.getRed(pixels[x]);
			int green = cm.getGreen(pixels[x]);
			int blue = cm.getBlue(pixels[x]);
//			色彩合成: color24 = red << 16 | green << 8 | blue;  
//	    　　　 color32 = alpha << 24 | red << 16 | green << 8 | blue; (或运算: 两个数中有一个为1,结果就为1,若都为0则为0);
//	例颜色值0xFF55F3的组成:
//	 　　红色范围的值为0xFF, 二进制为11111111,将它向左移动16位,结果:111111110000000000000000;
//	 　　绿色范围的值为0x55,二进制为01010101,将它向左移动8位,结果: 000000000101010100000000;
//	　　 蓝色范围的值为0xF3,二进制为11110011,不需要移动.
			//合成一个颜色值
			pixels[x] = (alpah<<24) | (red<<16) | (green<<8) | blue; 
					
		}
		//通过MemoryImageSource将数组中的像素产生一个新图像
		//MemoryImageSource将数组中的像素值重构一个新的图像
		ImageProducer imgprd = new MemoryImageSource(w, h, pixels, 0, w);
		cropped = createImage(imgprd);
		MediaTracker tracker2 = new MediaTracker(this);
		tracker2.addImage(cropped, 1);
		try {
			tracker2.waitForID(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g){
		
		g.drawImage(img,10,20,this); //显示原图像
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(cropped,w+20,20,this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Image1 f = new Image1();
		
	}

}


