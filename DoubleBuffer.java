package com.rogan.test;
/**
 * 2016��4��27��16:49:25
 * ���ܣ���дupdateʵ��˫����
 * ˵����
 * ��1������һ��Graphics����gBuffer��һ��Image����iBuffer������Ļ��С����һ����������iBuffer��Ȼ��ȡ��iBuffer��Graphics����gBuffer���˴����԰�gBuffer���Ϊ�߼��ϵĻ�����Ļ������iBuffer���Ϊ������Ļ�ϵ�ͼ��
��2����gBuffer���߼��ϵ���Ļ������paint(Graphics g)��������ͼ��
��3������̨ͼ��iBuffer���Ƶ�ǰ̨��
 * */
import java.awt.*;
import java.awt.event.*;
public class DoubleBuffer extends Frame//����̳�Frame��
{
    public paintThread pT;//��ͼ�߳�
    public int ypos=-80; //СԲ���Ͻǵ�������
    public DoubleBuffer()//���캯��
    {
       pT=new paintThread(this);
       this.setResizable(false);
       this.setSize(300,300); //���ô��ڵ���ѡ��С
       this.setVisible(true); //��ʾ����
       pT.start();//��ͼ�߳�����
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
   public void paint(Graphics scr) //���ػ�ͼ����
   {
       scr.setColor(Color.RED);//����СԲ��ɫ
       scr.fillOval(90,ypos,80,80); //����СԲ
    }
    public static void main(String[] args)
    {
       DoubleBuffer DB=new DoubleBuffer();//��������Ķ���
       DB.addWindowListener(new WindowAdapter()//��Ӵ��ڹرմ�����
       {
           public void windowClosing(WindowEvent e)
           {
              System.exit(0);
           }});
    }
}
class paintThread extends Thread  //��ͼ�߳��࣬����Ҳ����ʹ�ö�ʱ�������ػ�
{
    DoubleBuffer DB;
       public paintThread(DoubleBuffer DB) //���캯��
       {
           this.DB=DB;
       }
       public void run()//����run()����
       {
           while(true)//�߳��е�����ѭ��
           {
              try{
                  sleep(30); //�߳�����30ms
                  }catch(InterruptedException e){}
              DB.ypos+=5; //�޸�СԲ���Ͻǵ�������
              if(DB.ypos>300) //СԲ�뿪���ں��������Ͻǵ�������
                  DB.ypos=-80;
              DB.repaint();//�����ػ�
           }
       }
}

