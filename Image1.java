/*
 * ���ܣ�ץȡͼ�����ص����飬����RGBֵ��alphaֵ�������¼���͸�ֵ
 * 2016��6��26��21:27:17
 * ��ʵ�ܼ�
 * PixelGrabber���MemoryImageSource��
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
		//Ӧ���ǿ��п���
		MediaTracker tracker1 = new MediaTracker(this);
		img = Toolkit.getDefaultToolkit().getImage("dog.jpg");
		// ���ý����������ڸ��ٵ�ͼ���б����һ��ͼ��
		tracker1.addImage(img, 0);
		try {
			//����ָ����ʶ����ͼ��
			tracker1.waitForID(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		w = img.getWidth(this);
		h = img.getHeight(this);
		pixels = new int[w*h];
		//PixelGrabber����ץȡͼ������
		PixelGrabber pg;
		//ץȡͼ������ص�������    ������0��ʾ��ŵ�һ�����ص�λ�ã�w��ʾÿ�е����ظ���
		pg = new PixelGrabber(img, 0, 0, w, h, pixels,0,w);
		try {
			//ץȡͼ�� pixels��
			pg.grabPixels();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ColorModel cm = ColorModel.getRGBdefault();
		//��RGBֵ��alphaֵ�������¼���͸�ֵ
		for(x = 0;x < w*h; x++){
			int alpah = 100;
			int red = cm.getRed(pixels[x]);
			int green = cm.getGreen(pixels[x]);
			int blue = cm.getBlue(pixels[x]);
//			ɫ�ʺϳ�: color24 = red << 16 | green << 8 | blue;  
//	    ������ color32 = alpha << 24 | red << 16 | green << 8 | blue; (������: ����������һ��Ϊ1,�����Ϊ1,����Ϊ0��Ϊ0);
//	����ɫֵ0xFF55F3�����:
//	 ������ɫ��Χ��ֵΪ0xFF, ������Ϊ11111111,���������ƶ�16λ,���:111111110000000000000000;
//	 ������ɫ��Χ��ֵΪ0x55,������Ϊ01010101,���������ƶ�8λ,���: 000000000101010100000000;
//	���� ��ɫ��Χ��ֵΪ0xF3,������Ϊ11110011,����Ҫ�ƶ�.
			//�ϳ�һ����ɫֵ
			pixels[x] = (alpah<<24) | (red<<16) | (green<<8) | blue; 
					
		}
		//ͨ��MemoryImageSource�������е����ز���һ����ͼ��
		//MemoryImageSource�������е�����ֵ�ع�һ���µ�ͼ��
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
		
		g.drawImage(img,10,20,this); //��ʾԭͼ��
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


