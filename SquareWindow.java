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


import javax.swing.JFrame;

import javax.swing.JPanel;




public class SquareWindow extends JFrame  {
	
	JPanel SquareDriver;

    public SquareWindow() {
        super();
        // TO-DO: set up the frame
    }

    public static void main(String[] args) { 
        // run main application 
    	
    	JFrame frame = new JFrame("Shapes!");
    	frame.setSize(600,600);
    	frame.setTitle("Midterm Takehome!");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.getContentPane().add(new SquareDriver());
        frame.setVisible(true);

    }
}


