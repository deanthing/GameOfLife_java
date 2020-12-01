package GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
////////////////////////////////////////////////////////////////////////////////////
//C212
//
//Released:  3/19/22
//
//
//@Author Dean Allen deanalle	
//Last Edited: March 22
//
//
//
//
//////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////

public class Square extends Rectangle{
	
	private int side;

	public Square(Color fillColor, Color borderColor, int x, int y, int side, int dx, int dy) {
		super(fillColor, borderColor, x, y, side, dx, dy);
		this.side = side;
	}
	
	public Square(Color fillColor,int x, int y, int width, int height) {
		super(fillColor, fillColor, x, y, height, height, height);

	}
	
	public Square(int x, int y) {
		super(x, y);
		this.side = side;
	}
	
	public void draw(Graphics g) {
		int[] xLst = {(int)(this.getX()-.5*this.side),(int)(this.getX()+.5*this.side),(int)(this.getX()+.5*this.side),(int)(this.getX()-.5*this.side)};
		int[] yLst = {(int)(this.getY()-.5*this.side),(int)(this.getY()-.5*this.side), (int)(this.getY()+.5*this.side), (int)(this.getY()+.5*this.side)};
		g.setColor(this.getFillColor());
		g.fillPolygon(xLst,yLst,4);
		g.setColor(this.getBorderColor());
		g.drawPolygon(xLst,yLst, 4);


	}
	
	public String toString() {
		return "side length: "  + this.side;
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
	
	public int getSide() {
		return this.side;
	}

	@Override
	double getArea() {
		// TODO Auto-generated method stub
		return side*side;
	}

	@Override
	double getPerimeter() {
		// TODO Auto-generated method stub
		return side*4;
	}

	
	

}
