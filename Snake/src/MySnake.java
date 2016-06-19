import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class MySnake extends JPanel implements KeyListener{
	
	private int x;
	private int y;
	private int speed=5;
	private int direction = 2;  //初始方向为左
	
	
	
	public void paint(Graphics g){
		g.fillRect(0, 0, 15, 15);
		g.setColor(Color.black);
		//将Snake放在哪里呢？
		
	}
	
	
	
	public MySnake(Yard yard){
		this.setLocation(yard.COLS*yard.BLOCK_SIZE, (yard.ROWS-5) * yard.BLOCK_SIZE);
		yard.add(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP: this.setDirection(0);this.y-=this.speed;
		case KeyEvent.VK_DOWN: this.setDirection(1); this.y+=this.speed;
		case KeyEvent.VK_LEFT: this.setDirection(2); this.x-=this.speed;
		case KeyEvent.VK_RIGHT: this.setDirection(3); this.x+=this.speed;
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	public int getSpeed() {
		return speed;
	}



	public void setSpeed(int speed) {
		this.speed = speed;
	}



	public int getDirection() {
		return direction;
	}



	public void setDirection(int direction) {
		this.direction = direction;
	}



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
	
	
}
