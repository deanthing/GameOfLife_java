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
import java.awt.Point;
import java.awt.image.BufferedImage;
////////////////////////////////////////////////////////////////////////////////////
//C212
//
//Released:  3/19/22
//
//MidTerm Takehome
//@Author Dean Allen deanalle	
//Last Edited: March 22
//
//
//
//
//////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////



public abstract class Shape {
	 private Color fillColor;
	 private Color borderColor;
	 private Boolean isFilled;
	 private Point Location;
	private int dx;
	private int dy;
	 // the three constructors initialize the instance fields
	 public Shape(Color fillColor,Color borderColor, int x, int y, int dx, int dy) { 
		 
		 this.fillColor = fillColor;
		 this.borderColor = borderColor;
		 this.isFilled = isFilled();
		 this.Location = new Point(x,y);
		 this.dx = dx;
		 this.dy = dy;
		 
		 
	 }
	 // set borderColor to Black since not provided
	 public Shape(Color fillColor, int x, int y) { 
		 
		 this.Location = new Point(x,y);
		 this.fillColor = fillColor;
		 this.borderColor = Color.black;
		 
	 }
	 // set fillColor to white and border color to black
	 public Shape(int x, int y) { 
		 this.Location = new Point(x,y);
		 this.fillColor = Color.white;
		 this.borderColor = Color.black;
		 
	 }
	 // will fill the shape with some random image. You can select any image for larger shapes
	 public void setFillImage(BufferedImage b) { 
		 //lol what
		 
	 }
	 public void setFillColor(Color c) {
		 
		 this.fillColor = c;
		 
	 }
	 public Color getFillColor() {
		 
		 return this.fillColor;
	 }
	 public void setBorderColor(Color c) {
		 this.borderColor = c;
	 }
	 public Color getBorderColor() { 
		 return this.borderColor;
	 }
	 public void setLocation(Point pt) { 
		 this.Location.setLocation(pt);
	 }
	 public Point getLocation() { 
		 return this.Location;
	 }
	 // Note: subclasses of Shape do not inherent private members so we need methods the subclasses
	 // can use to get the x and y values from the private Point instance field
	 public int getX() { 
		 int returnthing = (int) this.Location.getX();
		 return returnthing;
	 }
	 public void setX(int x) { 
		 int y = (int) this.Location.getY();
		 this.Location.setLocation(new Point(x,y));
	 }
	 public int getY() {
		 
		 return (int) this.Location.getY();
		 
	 }
	 public void setY(int y) { 
		 
		 this.Location.setLocation(new Point((int) this.Location.getX(),y));
		 
	 }
	 // if fillColor is white returns true, else returns false
	 public boolean isFilled() {
		 
		if (this.fillColor == Color.white ) {
			return true;
		} else {
			return false;
		}
		 
	 }
	 // moves location by dx and dy
	 private void moveLocation(int dx, int dy) { 
		 int x = (int) this.Location.getX();
		 x = x+ dx;
		 
		 int y = (int) this.Location.getY();
		 y = y+ dx;
		 
		 this.Location.setLocation(new Point(x,y));
		 
	 }
	 
	 public int getDx() {
		 return this.dx;
	 }
	 public int getDy() {
		 return this.dy;
	 }
	 public void setDx(int dx) {
		 this.dx = dx;
	 }
	 public void setDy(int dy) {
		 this.dy = dy;
	 }
	 abstract double getArea();
	 abstract double getPerimeter();
	 abstract int getSide();
	 abstract void draw(Graphics g);
}
