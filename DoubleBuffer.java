package com.rogan.test;
/**
 * 2016年4月27日16:49:25
 * 功能：重写update实现双缓冲
 * 说明：
 * （1）定义一个Graphics对象gBuffer和一个Image对象iBuffer。按屏幕大小建立一个缓冲对象给iBuffer。然后取得iBuffer的Graphics赋给gBuffer。此处可以把gBuffer理解为逻辑上的缓冲屏幕，而把iBuffer理解为缓冲屏幕上的图象。
（2）在gBuffer（逻辑上的屏幕）上用paint(Graphics g)函数绘制图象。
（3）将后台图象iBuffer绘制到前台。
 * */
import java.awt.*;
import java.awt.event.*;
public class DoubleBuffer extends Frame//主类继承Frame类
{
    public paintThread pT;//绘图线程
    public int ypos=-80; //小圆左上角的纵坐标
    public DoubleBuffer()//构造函数
    {
       pT=new paintThread(this);
       this.setResizable(false);
       this.setSize(300,300); //设置窗口的首选大小
       this.setVisible(true); //显示窗口
       pT.start();//绘图线程启动
    }
    
    Image iBuffer;
    Graphics gBuffer;
    public void update(Graphics g){
    	if(iBuffer == null){
    		iBuffer = createImage(this.getSize().width, this.getSize().height);
    		gBuffer = iBuffer.getGraphics();
    	}
    	gBuffer.setColor(getBackground());
    	gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
    	paint(gBuffer);
    	g.drawImage(iBuffer,0,0,this);
    }
   public void paint(Graphics scr) //重载绘图函数
   {
       scr.setColor(Color.RED);//设置小圆颜色
       scr.fillOval(90,ypos,80,80); //绘制小圆
    }
    public static void main(String[] args)
    {
       DoubleBuffer DB=new DoubleBuffer();//创建主类的对象
       DB.addWindowListener(new WindowAdapter()//添加窗口关闭处理函数
       {
           public void windowClosing(WindowEvent e)
           {
              System.exit(0);
           }});
    }
}
class paintThread extends Thread  //绘图线程类，或者也可以使用定时器进行重绘
{
    DoubleBuffer DB;
       public paintThread(DoubleBuffer DB) //构造函数
       {
           this.DB=DB;
       }
       public void run()//重载run()函数
       {
           while(true)//线程中的无限循环
           {
              try{
                  sleep(30); //线程休眠30ms
                  }catch(InterruptedException e){}
              DB.ypos+=5; //修改小圆左上角的纵坐标
              if(DB.ypos>300) //小圆离开窗口后重设左上角的纵坐标
                  DB.ypos=-80;
              DB.repaint();//窗口重绘
           }
       }
}

