import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class LoadImgApplication extends JFrame {

	Image img;
	Toolkit tool;
	public LoadImgApplication() throws IOException {
		// TODO Auto-generated constructor stub
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tool = Toolkit.getDefaultToolkit();
		img = tool.getImage("image/dog.jpg"); //从图像文件中获取图像,为何无法显示
		//img = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\dog.jpg"));
		validate();
		System.out.println("构造函数");
	}
	
	public void paint(Graphics g){
		System.out.println("paint");
		//g.drawString("5555", 200, 200);
		g.drawImage(img, 0,0,this);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new LoadImgApplication();
	}

}
