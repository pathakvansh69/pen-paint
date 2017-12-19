package penpaint;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class DrawingBoard extends JComponent{
	ArrayList<Shape> shapes=new ArrayList<Shape>();
	ArrayList<Color> shapefill=new ArrayList<Color>();
	ArrayList<Color> shapestroke=new ArrayList<Color>();
	ArrayList<Float> transPercent =new ArrayList<Float>();
	
	Point drawstart, drawend;
	Shape ashape; 
	public DrawingBoard(){
		this.setBackground(Color.white);
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(userint.currentAction !=1){
				drawstart=new Point(e.getX(),e.getY());
		 		drawend =drawstart;
		 		repaint();
				}
			}
			public void mouseReleased(MouseEvent e){
				if(userint.currentAction!=1){
				ashape =null;

            	if (userint.currentAction == 2){
            		ashape = drawLine(drawstart.x, drawstart.y,e.getX(), e.getY());
            	} else 
            	
            	if (userint.currentAction == 3){
            		ashape = drawEllipse(drawstart.x, drawstart.y,e.getX(), e.getY());
            	} else 
            	
            	if (userint.currentAction == 4) {
            		
                    ashape = drawRectangle(drawstart.x, drawstart.y,e.getX(), e.getY());
            	}
				}
				shapes.add(ashape);
				shapefill.add(userint.fillColor);
				shapestroke.add(userint.strokeColor);
				transPercent.add(userint.transval);
				drawstart=null;
				drawend=null;
				repaint();
				}
				
			});//end of mouselistener
		
		this.addMouseMotionListener(new MouseMotionAdapter()
				{
			public void mouseDragged(MouseEvent e){
				if(userint.currentAction==1){
					int x=e.getX();
					int y=e.getY();
					Shape ashape =null;
					userint.strokeColor=userint.fillColor;
					ashape =drawBrush(x,y,4,4);
					shapes.add(ashape);
					shapefill.add(userint.fillColor);
					shapestroke.add(userint.strokeColor);
					transPercent.add(userint.transval);
					
				}
				drawend =new Point(e.getX(),e.getY());
				repaint();
			}
				}); // end of mousemotionlistener
		
	}
	public void paint(Graphics g){
		userint.graphsettings =(Graphics2D)g;
		userint.graphsettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		userint.graphsettings.setStroke(new BasicStroke(2));
		
		// Iterators created to cycle through strokes and fills
		Iterator<Color> strokecounter=shapestroke.iterator();
		Iterator<Color> fillcounter =shapefill.iterator();
		Iterator<Float> transcounter =transPercent.iterator();
        
       
        for (Shape s : shapes)

       {
        	userint.graphsettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transcounter.next()));

        	// Grabs the next stroke from the color arraylist

        	userint.graphsettings.setPaint(strokecounter.next());

       	
        	userint.graphsettings.draw(s);

       	
        	// Grabs the next fill from the color arraylist

        	userint.graphsettings.setPaint(fillcounter.next());

        	
        	userint.graphsettings.fill(s);

        }

        // Guide shape used for drawing


        if (drawstart != null && drawend != null)

       {

        	// Makes the guide shape transparent
            userint.graphsettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40f));

        	
            // Make guide shape gray for professional look  
        	userint.graphsettings.setPaint(Color.LIGHT_GRAY);
        	Shape ashape = null;
        	
        	if (userint.currentAction == 2){
        		ashape = drawLine(drawstart.x, drawstart.y,drawend.x, drawend.y);
        	} else 
        	
        	if (userint.currentAction == 3){
        		ashape = drawEllipse(drawstart.x, drawstart.y,drawend.x, drawend.y);
        	} else 
        	
        	if (userint.currentAction == 4) {
        		
        		// Create a new rectangle using x & y coordinates
        		
                ashape = drawRectangle(drawstart.x, drawstart.y,drawend.x, drawend.y);
        	}

        	userint.graphsettings.draw(ashape);

        }

}
	private Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2)

{

	// Get the top left hand corner for the shape

	// Math.min returns the points closest to 0

	
        int x = Math.min(x1, x2);

        int y = Math.min(y1, y2);

        
        // Gets the difference between the coordinates and

        
        int width = Math.abs(x1 - x2);

        int height = Math.abs(y1 - y2);


        return new Rectangle2D.Float(x, y, width, height);

}


// The other shapes will work similarly

// More on this in the next tutorial

	private Ellipse2D.Float drawEllipse(int x1, int y1, int x2, int y2)

{

        int x = Math.min(x1, x2);

        int y = Math.min(y1, y2);

       int width = Math.abs(x1 - x2);

        int height = Math.abs(y1 - y2);


        return new Ellipse2D.Float(x, y, width, height);

}
	private Line2D.Float drawLine(int x1,int y1,int x2,int y2){
		return new Line2D.Float(x1,y1,x2,y2);
	}
	private Ellipse2D.Float drawBrush(int x,int y,int widht,int height){
		return new Ellipse2D.Float(x,y,widht,height);
	}

}


