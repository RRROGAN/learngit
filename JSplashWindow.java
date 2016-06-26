
/*
 * 2016年6月26日16:13:54
 * 功能：启动界面
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;


public class JSplashWindow extends JWindow implements Runnable {
	
	Thread splashThread = null;
	public JSplashWindow() {
		// TODO Auto-generated constructor stub
		JPanel splash = new JPanel(new BorderLayout());
		URL url = getClass().getResource("dog.jpg");
		if(url != null){
			splash.add(new JLabel(new ImageIcon(url)),BorderLayout.CENTER);
		}
		setContentPane(splash);
		Dimension screen = getToolkit().getScreenSize();  //获得屏幕分辨率
		pack();
		setLocation(screen.width - getSize().width/2,screen.height - getSize().height / 2);
		
	}
	
	public void start(){
		this.toFront();
		splashThread = new Thread(this);
		splashThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		show();
		try {
			Thread.sleep(6000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dispose();
	}
	
	static void showFrame(String title){
		
		JFrame frame = new JFrame(title);
		frame.setSize(400,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if(frameSize.height > screenSize.height){
			frameSize.height = screenSize.height;
		}
		if(frameSize.width > screenSize.width){
			frameSize.width = screenSize.width;
		}
		//使窗口自动居中
		frame.setLocation((screenSize.width - frameSize.width)/2,(screenSize.height - frameSize.height)/2);
		Label lb = new Label("进入应用系统");
		frame.getContentPane().add(lb,BorderLayout.CENTER);
		lb.setFont(new Font("default",1,36));
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		JSplashWindow splash = new JSplashWindow();
		splash.start();
		showFrame("Demo splash window");  //显示主窗口
	}

}
