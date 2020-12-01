package GameOfLife;
////////////////////////////////////////////////////////////////////////////////////
//C212

//
//Released:  3/5/19
//
//Lab 6: Game of Life
//@Author Dean Allen deanalle	
//Last Edited: Feb 7
//
//
//Directions: Run class,
//
//////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {
	
	private int width;
	private int height;

	public Rectangle(Color fillColor, Color borderColor, int x, int y, int width, int dx, int dy) {
		super(fillColor, borderColor, x, y, dx,dy);
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Color fillColor,int x, int y, int width, int height) {
		super(fillColor, x, y);
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(int x, int y) {
		super(x, y);
		this.width = width;
		this.height = height;
	}
	
	
	public void draw(Graphics g) {
		int[] xPoiints = {(int)(this.getX()-.5*this.width),(int)(this.getX()+.5*this.width),(int)(this.getX()+.5*this.width),(int)(this.getX()-.5*this.width)};
		int[] yPoiints = {(int)(this.getY()-.5*this.height), (int)(this.getY()-.5*this.height), (int)(this.getY()+.5*this.height), (int)(this.getY()+.5*this.height)};
		g.setColor(this.getFillColor());
		g.fillPolygon(xPoiints,yPoiints,4);
		g.setColor(this.getBorderColor());
		g.drawPolygon(xPoiints,yPoiints, 4);
		

	}
	
	public String toString() {
		return "width length: "  + this.width + "height length: "  + this.height;
	}
	
	public int equals(Shape x) {
		if (x.getX() == this.getX()) {
			return 0;
		} else if (this.getX() > x.getX()) {
			return 1;
		} else  {
			return -1;
		}

	}
	

	@Override
	double getArea() {
		
		return this.width*this.height;
	}

	@Override
	double getPerimeter() {
		// TODO Auto-generated method stub
		return this.width*2+this.height*2;
	}

	@Override
	int getSide() {
		// TODO Auto-generated method stub
		return 0;
	}


}
