package com.ro.tank1;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class MyTank1 extends JFrame implements ActionListener{
	MyPanel mp = null;
	GuanKaPanel gkmp = null;
	JMenuBar cdl = null;  //�˵���
	JMenu cd1 = null;
	JMenuItem cdx1 = null;
	JMenuItem cdx2 = null;
	JMenuItem cdx3 = null;
	JMenuItem cdx4 = null;
	
	public static void main(String[] args){
		MyTank1 mt = new MyTank1();
		//System.out.println("djjdjdjd");
	}
	
	public MyTank1(){
		cdl = new JMenuBar();
		cd1 = new JMenu("��Ϸ(G)");
		cd1.setMnemonic('G');
		cdx1 = new JMenuItem("����Ϸ");
		cdx2 = new JMenuItem("������Ϸ");
		cdx3 = new JMenuItem("�����˳�");
		cdx4 = new JMenuItem("�˳���Ϸ");
		cd1.add(cdx1);
		cd1.add(cdx2);
		cd1.add(cdx3);
		cd1.add(cdx4);
		cdl.add(cd1);
		
		//ע�����
		cd1.addActionListener(this);
		cdx1.addActionListener(this);
		cdx2.addActionListener(this);
		cdx3.addActionListener(this);
		cdx4.addActionListener(this);
		
		//��������
		cdx1.setActionCommand("newGame");
		cdx2.setActionCommand("goGame");
		cdx3.setActionCommand("saveExit");
		cdx4.setActionCommand("exit");
		
		
		 gkmp = new GuanKaPanel();
		 Thread t = new Thread(gkmp);
		 t.start();
		 this.add(gkmp);
		
		this.setJMenuBar(cdl);
		
		
		this.setSize(600, 500);
		this.setLocation(270, 170);
		this.setVisible(true);
		this.setTitle("̹��������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage((new ImageIcon("tank.jpg")).getImage());
		
		
		
	}
	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("newGame")){
			 mp = new MyPanel("newGame");
			 this.remove(gkmp);
			 this.add(mp);
			 this.addKeyListener(mp);
			 Thread t = new Thread(mp);
			 t.start();
			 this.setVisible(true);   //������
		}else if(e.getActionCommand().equals("goGame")){
			mp = new MyPanel("goGame");
			this.remove(gkmp);
			this.add(mp);
			this.addKeyListener(mp);
			Thread t = new Thread(mp);
			t.start();
			this.setVisible(true);
		}else if(e.getActionCommand().equals("saveExit")){
			Jilu jl=new Jilu();   //��¼
			jl.setDtk(mp.dtk);
			jl.cunpan();
			System.exit(0);
		}else if(e.getActionCommand().equals("exit")){
			Jilu.bcjl();   //�����¼
			System.exit(0);   //�����˳�
		}
	}

	
}

class MyPanel extends JPanel implements KeyListener ,Runnable{
	
	MyTank mt = null;
	Vector<EnermyTank> dtk = new Vector<EnermyTank>();
	Vector<Weizhi> wzjh = new Vector<Weizhi>();
	int tksl=3;
	
	MyPanel(String ss){
		 mt = new MyTank(180, 250);
		 
		 if(ss.equals("newGame")){
			 for(int i=0; i < tksl; i++){
				 EnermyTank dt = new EnermyTank((i)*180+5,0);
				 dt.setDirection(2);
				 dt.dtkxl(dtk);
				 Thread t = new Thread(dt);
				 t.start();
				 Zidan zd = new Zidan(dt.x+10,dt.y+30,2);   //�˶δ�������̹�˴���ʱ�����·���һ���ӵ���ע�͵�û���κ�Ӱ��
				 dt.dzd.add(zd);
				 Thread t1 = new Thread(zd);
				 t1.start();
				 dtk.add(dt);
				 
				 
				 
			 }
		 }else if(ss.equals("goGame")){
			 Weizhi wz = new Weizhi(100,100,2);  //Ӧ�ö�ȡ��¼
			 
			 for(int i=0; i < tksl; i++){
				 EnermyTank dt = new EnermyTank(wz.x,wz.y);
				 dt.setDirection(wz.direction);
				 dt.dtkxl(dtk);
				 Thread t = new Thread(dt);
				 t.start();
				 Zidan zd = new Zidan(dt.x+10,dt.y+30,2);   //�˶δ�������̹�˴���ʱ�����·���һ���ӵ���ע�͵�û���κ�Ӱ��
				 dt.dzd.add(zd);
				 Thread t1 = new Thread(zd);
				 t1.start();
				 dtk.add(dt);
				 
				 
				 
			 }
		 }
		 
	}
	
	public void drawTank(int x, int y, Graphics g, int direction, int type){  //gҲ�ܴ�����������
		switch(type){
		case 0:
			g.setColor(Color.yellow);
			break;
		case 1:
			g.setColor(Color.green);
			break;
		}
		
		switch(direction){
		case 0:
			g.fill3DRect(x, y, 5, 30,false);  //��5����������˼�����°�����������͹
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10); 
			g.drawLine(x+10, y+15, x+10, y-3);
			break;
		case 1:
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x,y+15 , 30, 5,false);
			g.fill3DRect(x+5,y+5 ,20, 10,false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x-3, y+10);
			break;
		case 2:
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+33);
			break;
		case 3:
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+33, y+10);
			break;	
		}
		this.repaint();
	}
	
	public void tj(Graphics g){  //ͳ��
		this.drawTank(80,330, g, 0, 0); //�·�����Ϸ��¼��
		g.setColor(Color.black);
		g.drawString(Jilu.getMtsl()+"",116,350);
		
		this.drawTank(150, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Jilu.getDtsl()+"",186,350);
		
		  this.drawTank(450, 86, g, 0,1);
			g.setColor(Color.black);
			g.drawString(Jilu.getSdtj()+"",486,107);
			
			g.setColor(Color.black);
			Font f=new Font("���Ĳ���",Font.BOLD,20);
			g.setFont(f);
			g.drawString("�������̹������", 410, 40);
	}
	
	public void paint(Graphics g){   //����ͼ�ε���ں���,���屻����ʱ����
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		this.tj(g);
		if(mt.life){  //if(mt.life)
			this.drawTank(mt.getX(), mt.getY(), g, mt.getDirection(), 0);
			for(int i=0; i < mt.mzd.size(); i++){
				Zidan mtzd = mt.mzd.get(i);
				if(mtzd.life){
					g.setColor(Color.white);
					g.fill3DRect(mtzd.x, mtzd.y, 3, 3,false);
				}else{
					mt.mzd.remove(mtzd);
				}
			}  
			
		}
		for(int i=0; i<dtk.size(); i++){
			EnermyTank dt = dtk.get(i);
			if(dt.life){  //dt.life
				this.drawTank(dt.getX(), dt.getY(), g, dt.getDirection(),1);
				for(int j = 0;  j< dt.dzd.size(); j++){
					Zidan dtzd = dt.dzd.get(j);
					if(dtzd.life){
						g.setColor(Color.white);
						g.fill3DRect(dtzd.x,dtzd.y,3,3,false);
					}else{
						dt.dzd.remove(dtzd);
					}
				}
			}
		}
		
		
		
	}
	
	public boolean jzdf(Zidan zd, Tank dt){   //�Ƿ����̹��
		boolean b = false;
		switch(dt.direction){
		case 0:
		case 2:
			if(zd.x>dt.x && zd.x<dt.x+20 && zd.y>dt.y && zd.y<dt.y+30)  //�ж��ӵ���̹�˵�λ�ù�ϵ
			{
				zd.life=false;
				dt.life=false;
				b=true;
			
			}
			break;
		case 1:
		case 3:
			if(zd.x>dt.x && zd.x<dt.x+30 && zd.y>dt.y && zd.y<dt.y+20)
			{
				zd.life=false;
				dt.life=false;
				b=true;
					
			}			
		}
		return b;
		}
		
	
	public void jzwf(){
		for(int i=0; i<dtk.size(); i++){
			EnermyTank dt = dtk.get(i);
			
			for(int j=0; j< dt.dzd.size(); j++){
				Zidan zd = dt.dzd.get(j);
				
				if(mt.life){
					this.jzdf(zd, mt);
				}
			}
		}
	}
	
	public void jzdf1(){
		for(int i=0; i< mt.mzd.size(); i++){
			Zidan zd = mt.mzd.get(i);
			for(int j = 0; j < dtk.size(); j++){
				EnermyTank dt = dtk.get(j);
				if(dt.life){
					if(this.jzdf(zd, dt)){
						Jilu.dtjs();  //�з�̹�˼�һ
						Jilu.sdsl();   //��һ
					
					}
					
				}
			}
			this.repaint();
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			try	{
				Thread.sleep(100);}
			catch (Exception e) {}
			this.jzdf1();	
			this.jzwf();
			this.repaint();
		}
		
	
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {  //����һ�����ע�����
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W){
			this.mt.setDirection(0); 
			this.mt.xiangshang();
			
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			this.mt.setDirection(1);
			this.mt.xiangzuo();
		}else if(e.getKeyCode() == KeyEvent.VK_S){
			this.mt.setDirection(2);
			this.mt.xiangxia();
		}else if(e.getKeyCode() == KeyEvent.VK_D){
			this.mt.setDirection(3);
			this.mt.xiangyou();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_J){
			if(mt.mzd.size() < 8){
			this.mt.fszd();
		}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

class GuanKaPanel extends JPanel implements Runnable{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	int times=0;
	public void paint(Graphics g){
		if(times%2==0){
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.yellow);
		Font f = new Font("�����п�",Font.BOLD,38);
		g.setFont(f);
		g.drawString("�����뿪ʼ������",60,140);
		
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(1000);
			}catch(Exception e){}
			times++;
			this.repaint();
		}
	}
	
	
}
