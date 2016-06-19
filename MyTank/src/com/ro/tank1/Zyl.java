package com.ro.tank1;

import java.util.Vector;
import java.io.*;



/*
 * 2016年3月12日11:19:32------2016年3月13日19:35:56
 * Zyl：重要类
 * 							先写类，再写逻辑
 * */
class Tank {  //私有的不能被继承
	int x=0,y=0;
	 int direction = 0;
	 int speed = 5;
	 int color = 0;
	boolean life = true;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public boolean isLife() {
		return life;
	}
	public void setLife(boolean life) {
		this.life = life;
	}
	public Tank(int x, int y) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
	}
	
}

class MyTank extends Tank{  //类之间的组合关系，以及集合的使用。
	
	Vector<Zidan> mzd = new Vector<Zidan>();
	Zidan zd = null;
	public MyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void fszd(){ //发射子弹,不用传入反向，MyTank本来就有自己的方向
		switch(this.direction) {  //子类中不能访问private direction,私有的不能被继承。
		case 0:
			zd = new Zidan(x+10,y,0);  //x,y是坦克左上角的坐标
			mzd.add(zd);
			break;
		case 1:
			zd = new Zidan(x, y+10, 1);
			mzd.add(zd);
			break;
		case 2:
			zd = new Zidan(x+10, y+30, 2);
			mzd.add(zd);
			break;
		case 3:
			zd = new Zidan(x+30, y+10, 3);
			mzd.add(zd);
			break;
		
		}
		Thread t = new Thread(zd);
		t.start();
		
	}
	
	public void xiangshang(){
		y-=speed;
	}
	public void xiangzuo(){
		x-=speed;
	}
	public void xiangxia(){
		y+=speed;
	}
	public void xiangyou(){
		x+=speed;
	}
	
	
}

class EnermyTank extends Tank implements Runnable{  //敌人的坦克是任意行走的，所以开启一个线程

	int time=0;
	int speed = 1;
	Vector<EnermyTank> dtk = new Vector<EnermyTank>();
	Vector<Zidan> dzd = new Vector<Zidan>();
	public EnermyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void dtkxl(Vector<EnermyTank> dtk){   //????????/
		this.dtk = dtk;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			switch(this.direction){
			case 0:
				for(int i = 0; i< 30; i++){
					if(y > 0){
						y-=speed;
						
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){}
				}
				break;
			case 1:
				for(int i = 0; i< 30; i++){
					if(x > 0){
						x-=speed;
						
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){}
				}
				break;
			case 2:
				for(int i = 0; i< 30; i++){
					if(y < 250){
						y+=speed;
						
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){}
				}
				break;
			case 3:
				for(int i = 0; i< 30; i++){
					if(x < 360){
						x+=speed;
						
					}
					try{
						Thread.sleep(50);
					}catch(Exception e){}
				}
				break;
			}
			this.direction = (int)(Math.random()*4);
			if(this.life == false){
				break;
			}
			this.time++;
			if(time % 2 == 0){
				if(life){
					if(this.dzd.size() < 5){  //少于5颗子弹就new子弹
						Zidan zd = null;
						switch(direction){
						case 0:
							zd = new Zidan(x+10,y,0);
							dzd.add(zd);
							break;
						case 1:
							zd = new Zidan(x,y+10,1);
							dzd.add(zd);
							break;
						case 2:
							zd = new Zidan(x+10,y+30,2);
							dzd.add(zd);
							break;
						case 3:
							zd = new Zidan(x+30,y+10,3);
							dzd.add(zd);
							break;
						}
						Thread t = new Thread(zd);
						t.start();
					}
				}
			}
		}
	}
	
}  

class Zidan implements Runnable{
	private int direction;   //子弹被new出来之后，不需要修改了，所以没有set或get方法。
	boolean  life = true;
	int speed=5;
	int x;
	 int y;
	
	Zidan(int x, int y, int direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
				
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(50);
			}catch(Exception e){}
		switch(direction){
		case 0:
			y-=speed;
			break;
		case 1:
			x-=speed;
			break;
		case 2:
			y+=speed;
			break;
		case 3:
			x+=speed;
			break;
		}
		if(x>=400 || x<=0 || y>=300 || y<=0){
			this.life = false;
			break;
		}
		}
		
	}
}

class Weizhi{
	int x,y;
	int direction;
	public Weizhi(int x, int y, int direction) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}

class Jilu{
	private static int dtsl =10; //敌人坦克数量
	private static int mtsl = 2;  //自己坦克数量
	private static int sdtj = 0;   //?????  敌人坦克记录
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	private static FileReader fr = null;
	private static BufferedReader br = null;
	private static Vector<EnermyTank> dtk = new Vector<EnermyTank>();
	static Vector<Weizhi> wzjh = new Vector<Weizhi>();
	
	public Vector<EnermyTank> getDtk() {
		return dtk;
	}
	public void setDtk(Vector<EnermyTank> dtk) {
		this.dtk = dtk;
	}
	
	public static int getDtsl() {
		return dtsl;
	}
	public static void setDtsl(int dtsl) {
		Jilu.dtsl = dtsl;
	}
	public static int getMtsl() {
		return mtsl;
	}
	public static void setMtsl(int mtsl) {
		Jilu.mtsl = mtsl;
	}
	public static int getSdtj() {
		return sdtj;
	}
	public static void setSdtj(int sdtj) {
		Jilu.sdtj = sdtj;
	}
	
	public static void dtjs()   //敌人坦克减一
	{
		dtsl--;
	}
	public static void sdsl()
	{
		sdtj++;
	}
	
	public static Vector<Weizhi> dupan(){
		try {
			fr = new FileReader("g:/TKDZ/cpjl.txt");
			br = new BufferedReader(fr);
			String s = "";
			s = br.readLine();
			sdtj = Integer.parseInt(s);
			while ((s = br.readLine()) != null) {
				String[] sz = s.split(" "); //返回一个数组
				Weizhi wz = new Weizhi(Integer.parseInt(sz[0]),
						Integer.parseInt(sz[1]), Integer.parseInt(sz[2]));
				wzjh.add(wz);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
			} 
		}
		
		return wzjh;
	}
	
	
	
	public static void bcjl(){   //保存记录
		
		try {
			fw = new FileWriter("g:/TKDZ/cpjl.txt");
			bw = new BufferedWriter(fw);
			bw.write(sdtj + "\r\n");
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try{
			fw.close();
			bw.close();
			}catch(Exception e){}
		}
	}
	
	
	public void cunpan() {
		try {
			fw = new FileWriter("g:/TKDZ/cpjl.txt");
			bw = new BufferedWriter(fw);
			bw.write(sdtj + "\r\n");
			for (int i = 0; i < dtk.size(); i++) {
				EnermyTank dt = dtk.get(i);

				if (dt.life) {
					String ss = dt.x + " " + dt.y + " " + dt.direction;
					bw.write(ss + "\r\n");
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				fw.close();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	

	public static void dqjl(){   //读取记录
		
		try {
			fr = new FileReader("g:/TKDZ/cpjl.txt");
			br = new BufferedReader(fr);
			String s=br.readLine();
			sdtj=Integer.parseInt(s);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
			} 
		}
		
	}
	
}

