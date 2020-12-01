package GameOfLife;
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



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;




public class SquareDriver extends JPanel implements KeyListener, ActionListener {

    // Panel constraints
    public final int FRAME_WIDTH = 600;
    public final int FRAME_HEIGHT = 600;
    
    //gloval vars
    public int numberCalled = 0;
    public int iterations = 0;
    public int side = 200;
    private Random rand;
    private ArrayList<Shape> list;
    private Mouse mouse;
    public int currentTimeElapsed;
    public int backSpaceCount;
    
    private Timer timer;
    private Timer noClick;
    private Timer deleteKeyTimer;
    
  
    
    
    
    public SquareDriver() {
        super(); 

        /* T0-DO: 
         *  - set up JPanel
         *  - initialize any other fiels you want to declare and use
         *  - add the KeyListiner 
         */
        rand = new Random();
        
        list = new ArrayList<Shape>();
        
        
        JPanel primary = new JPanel();
        
        addMouseListener(new PanelListener());
        
		// the second argument to the Timer Constructor takes an ActionListener
		// the this key word informs the JVM to look inside this class for
		// the actionPerformed method that must be overridden when
		// ActionListener is implemented
		// Every tick of the clock will now run the actionPerformed method
		timer = new Timer(1000/60, this);
		timer.start();
		
		//start the scene with one square
    	startScene();
		addKeyListener(new MyKeyListener());  
		
		
		//no click timer
		noClick = new Timer (15000, this);
		noClick.start();
		noClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		    		//move squares out
		    		for (Shape element: list) {

		    			String closestBorderY = "";
		    			String closestBorderX = "";
		    			
		    			int x = element.getX();
		    			int y = element.getY();
		    			
		    			int distanceTop  = y;
		    			int distanceBottom = 600-y;
		    			
		    			int distanceRight = 600-x;
		    			int distanceLeft = x;

		    			
		    			
		    			
		    			//determine which is closer and move for top/bottom
		    			if (distanceTop > distanceBottom) {
		    				closestBorderY = "bottom";
		    				element.setDy(1);
		    			} else if (distanceTop<distanceBottom) {
		    				
		    				closestBorderY = "top";
		    				element.setDy(-1);
		    			} else {
		    				element.setDy(-1);
		    			}
		    			
		    			
		    			
		    			//determine which is closer and move for left/right
		    			if (distanceRight > distanceLeft) {
		    				closestBorderX = "left";
		    				element.setDx(-1);
		    			} else if (distanceRight < distanceLeft) {
		    				closestBorderX = "right";
		    				element.setDx(1);
		    			}  else {
		    				element.setDx(1);
		    			}
		    	
		    			
		    		}
		    		
		    		
				
			}
		});
		
		
		//delete key timer
		deleteKeyTimer = new Timer (3000, this);
		deleteKeyTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (backSpaceCount >= 3) {
					list.clear();
					startScene();
					backSpaceCount = 0;
					deleteKeyTimer.restart();

					deleteKeyTimer.restart();
					deleteKeyTimer.stop();
					repaint();
				} else {
				    backSpaceCount = 0;
				    deleteKeyTimer.restart();
				    deleteKeyTimer.stop();

				}
		    		
				
			}
		});
			
		
		
		 
    }
    
    public boolean isFocusTraversable ( ) {
    	return true ;
    }
    
   


    @Override
    public void paintComponent(Graphics g) {
        // call super class paintComponent method
        // background will not be colored otherwise
        super.paintComponent(g);
        
        
        
        

        // TO-DO use the different Shapes draw methods here
        // The draw methods in the differnet shapes should take 
        // The Graphics object should be passed to the Shapes Draw method
        
        for (Shape element: list) {
        	
        	element.draw(g);
        	
        	
        }

        
    }
    
    private class PanelListener implements MouseListener {
    	
    	

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			//if clicked, set time elapsed to 0
			noClick.restart();
			//get click locations
			int clickX = e.getX();
			int clickY = e.getY();
			Shape toDelete = null;
			//if left click
			if (SwingUtilities.isLeftMouseButton(e)) {
				//loop through all shapes to check if click is inside shape
				for (Shape element: list) {
					
					
					//get loc of current element
					int x = element.getX();
					int y = element.getY();
					
					//get sidelength
					int side = element.getSide()/2;
					
					//get bounds
					int rightBoundX = x + side;
					int leftBoundX = x - side;
					int upperBoundY = y + side;
					int lowerBoundY = y - side;
					
					
					
					//check if in bounds, if so addd to new var to delete
					if ((clickX <= rightBoundX && clickX >= leftBoundX) && (clickY <= upperBoundY && clickY >= lowerBoundY)) {
						toDelete = element;
					}
					
					
					
				} 

				//get new points from method for new squares
				ArrayList<Integer> points = devide(toDelete.getX(), toDelete.getY(), toDelete.getSide()/2);
				
				
				//remove element
				list.remove(toDelete);
				
				
				
				//loop through for squares
				for (int i = 0; i < points.size(); i += 2) {
					Random rand = new Random();
			    	//random color
			    	float r = rand.nextFloat();
			    	float g = rand.nextFloat();
			    	float b = rand.nextFloat();
			    	Color randomColor = new Color(r, g, b);
			    	
			    	//random border color
			    	r = rand.nextFloat();
			    	g = rand.nextFloat();
			    	b = rand.nextFloat();
			    	Color randomColorBorder = new Color(r, g, b);
					
			    	
			    	list.add(new Square(randomColorBorder, randomColor, points.get(i), points.get(i+1), toDelete.getSide()/2, 0, 0));
			    	
			    	repaint();
					
				}
				
				
			//right key click 
			} else {
				//delete if clicked

				for (Shape element: list) {
					
					
					//get loc of current element
					int x = element.getX();
					int y = element.getY();
					
					//get sidelength
					int side = element.getSide()/2;
					
					//get bounds
					int rightBoundX = x + side;
					int leftBoundX = x - side;
					int upperBoundY = y + side;
					int lowerBoundY = y - side;
					
					
					
					//check if in bounds, if so addd to new var to delete
					if ((clickX <= rightBoundX && clickX >= leftBoundX) && (clickY <= upperBoundY && clickY >= lowerBoundY)) {
						toDelete = element;
					}
				
				}
				
				list.remove(toDelete);
			
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
    	
    }
    
    public void actionPerformed(ActionEvent ae) {
    	//restart the scene


  
    	//if hits border delete
    	Shape deleteBoundry = null;
    	
    	for (Shape element: list) {
    		if (element.getX()+element.getSide()*.5==600) {
    			deleteBoundry = element;
    		} else if (element.getX()-element.getSide()*.5==0) {
    			deleteBoundry = element;
    		} else if (element.getY()+element.getSide()*.5==600) {
    			deleteBoundry = element;
    		} else if (element.getY()-element.getSide()*.5==0) {
    			deleteBoundry = element;
    		}
    	}
    	
    	list.remove(deleteBoundry);
    	
    	
    	//movement
    	for (Shape element: list) {
    		int dx = element.getDx();
    		int dy = element.getDy();

    		int x = element.getX();
    		int y = element.getY();

    		element.setX(element.getX() + dx);
    		element.setY(element.getY() + dy);

    		
    	}
    	
    	repaint();

    	

    }
    
    
    //keypress
    private class MyKeyListener extends KeyAdapter {
    	@Override
    	public void keyPressed (KeyEvent e) {
    		//if delete remove
    		if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
    	    {  
    	        if (deleteKeyTimer.isRunning()) {
    	        	backSpaceCount++;
    	        } else {
        	    	backSpaceCount++;
        	    	deleteKeyTimer.start();
        	    }
    	    } 
    		
    
    		
    		if (e.getKeyCode()== KeyEvent.VK_S) {
    			for (Shape element: list) {
    				element.setDx(0);
    				element.setDy(0);
    			}
    		}
    	}
    
    }

    // do not need to do anything with this method from KeyListener
    // but must have since this class implements KeyListiner 
    @Override
    public void keyReleased(KeyEvent e) { }
    
    // do not neet to do anything with this method from KeyListener
    // but must have since this class implements KeyListiner 
    @Override
    public void keyTyped(KeyEvent e) { }

    // test client
    public static void main(String[] args) {

    }

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void startScene(){
		Random rand = new Random();
    	//random color
    	float r = rand.nextFloat();
    	float g = rand.nextFloat();
    	float b = rand.nextFloat();
    	Color randomColor = new Color(r, g, b);
    	
    	//random border color
    	r = rand.nextFloat();
    	g = rand.nextFloat();
    	b = rand.nextFloat();
    	Color randomColorBorder = new Color(r, g, b);
		
		list.add(new Square(randomColorBorder, randomColor, 300, 300, side, 0, 0));
		
		
	}
	
	public ArrayList<Integer> devide(int x, int y, int side) {
		
		ArrayList<Integer> lst  = new ArrayList<Integer>(8);
		
		//top left coords
		lst.add(x-side/2);
		lst.add(y+side/2);
		
		//top right coords
		lst.add(x+side/2);
		lst.add(y+side/2);
		
		//bottom left coords
		lst.add(x-side/2);
		lst.add(y-side/2);
		
		//bottom right coords
		lst.add(x+side/2);
		lst.add(y-side/2);
		
		return lst;
		
		
	}
	

	
}