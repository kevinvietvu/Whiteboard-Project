import java.awt.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class Canvas extends JPanel  implements MouseMotionListener {
	static double previousFontSize;
	static DShape selected;
	static DShapeModel selectedModel;
	public static ArrayList<DShape> shapesList;
	private ArrayList<Point> knobs;
	
	
	
	
	public Canvas()
	{
		super();
		this.setPreferredSize(new Dimension(500,500));
		this.setOpaque(true);
	    this.setBackground(Color.WHITE);
	    shapesList = new ArrayList<>();
	    knobs = new ArrayList<>();
	    //Adds clicking on shapes to select it.
	    addMouseMotionListener(this);
	    addMouseListener(new MouseAdapter() {
	        public void mouseClicked(MouseEvent e) {
	            super.mouseClicked(e);
	            System.out.println(e.getPoint());
	            ControlPanel.disableButtons();
	            for (DShape d : shapesList)
	            {
	        		if (d.getName().equals("DRect"))
	        	    {
	        	    	DRect rect = (DRect) d;
	        	    	if (rect.contains(e.getPoint()))
	        	    	{	 		
	        	    		selected = rect;
	        	    		selectedModel = rect.info;
<<<<<<< HEAD
	        	    		ControlPanel.enableButtons();
	        	    		knobs.add(new Point(selected.getBounds().x-3, selected.getBounds().y-3));
	        	    		knobs.add(new Point(selected.getBounds().x-3, (int)selected.getBounds().getMaxY()-3));
	        	    		knobs.add(new Point((int)selected.getBounds().getMaxX()-3, selected.getBounds().y-3));
	        	    		knobs.add(new Point((int)selected.getBounds().getMaxX()-3, (int)selected.getBounds().getMaxY()-3));
=======
>>>>>>> c42b81463d0f6a4c2755dea154ca1ec65ff7510e
	        	    		System.out.println(selected.getName());
	        	    		System.out.println("Knobs are: " + getKnobs());
	        	    		System.out.println(selected.getBounds());
	        	    		break;
	        	    	}
	        	    	else {
	        	    	    selected = null;
	        	    		selectedModel = null;
<<<<<<< HEAD
	        	    		getKnobs().clear();
	        	    	}
=======
	        	    	}   	 	
>>>>>>> c42b81463d0f6a4c2755dea154ca1ec65ff7510e
	        	   	}
	        	    else if (d.getName().equals("DOval"))
	        	    {
	        	    	DOval oval = (DOval) d;
	        	    	if (oval.contains(e.getPoint()))
	        	    	{
	        	    		selected = oval;
	        	    		selectedModel = oval.info;
	        	    		System.out.println(selected.getName());
	        	    		break;
	        	    	}
	        	    	else {
	        	    	    selected = null;
	        	    		selectedModel = null;
	        	    	}
	        	    		
	        	  	}
	        	    else if (d.getName().equals("DLine"))
	        	    {	  
	        	    	DLine line = (DLine) d;
	        	    	if (line.contains(e.getPoint()))
	        	    	{	      
	        	    		selected = line;
	        	    		selectedModel = line.info;
	        	    		System.out.println(selected.getName());
	        	    		break;
	        	    	}
	        	    	else {
	        	    	    selected = null;
	        	    		selectedModel = null;
	        	    	}
	        	  	}
	        	    else if (d.getName().equals("DText"))
	        	    {	        
	        	    	DText text = (DText) d;
	        	    	if (text.contains(e.getPoint()))
	        	    	{	
	        	    		ControlPanel.enableButtons();
	        	    		selected = text;
	        	    		selectedModel = text.info;
	        	    		ControlPanel.enableButtons();
	        	    		System.out.println(selected.getName());
	        	    		break;
	        	    	} 
	        	    	else {
	        	    	    selected = null;
	        	    		selectedModel = null;
	        	    	}
	        	  	}
	        	   	
	            } 
	        }
	    });  
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		for (ListIterator<DShape> iterator = shapesList.listIterator(shapesList.size()); iterator.hasPrevious();)
		{
			DShape d = (DShape) iterator.previous();
			if (d.getName().equals("DRect"))
			{	
				DRect rect = (DRect) d;
				rect.draw(g2);
			}
			else if (d.getName().equals("DOval"))
			{
				DOval oval = (DOval) d;
				oval.draw(g2);
			}
			else if (d.getName().equals("DLine"))
			{
				DLine line = (DLine) d;
				line.draw(g2);
			}
			else if (d.getName().equals("DText"))
			{
				DText text = (DText) d;
				text.draw(g2);
			}
			if (selected != null)
			{
				if (shapesList.contains(selected))
				{
					DShape shape = shapesList.get(shapesList.indexOf(selected));
					if (shape.getName().equals("DRect"))
					{
						DRect rect = (DRect) shape;
						Point upperLeft = getKnobs().get(0);
						Point bottomLeft = getKnobs().get(1);
						Point upperRight = getKnobs().get(2);
						Point bottomRight = getKnobs().get(3);
						
						g2.setColor(Color.CYAN);
						g2.drawRect(rect.info.getX(),rect.info.getY(),rect.info.getWidth(),rect.info.getHeight());
						g2.setColor(Color.BLACK);
						g2.fillRect(upperLeft.x, upperLeft.y, 9, 9);
						g2.fillRect(bottomLeft.x, bottomLeft.y, 9, 9);
						g2.fillRect(upperRight.x, upperRight.y, 9, 9);
						g2.fillRect(bottomRight.x, bottomRight.y, 9, 9);
						
					}
					else if (shape.getName().equals("DOval"))
					{
						DOval oval = (DOval) shape;
						g2.setColor(Color.CYAN);
						g2.drawOval(oval.info.getX(),oval.info.getY(),oval.info.getWidth(),oval.info.getHeight());
					}
					else if (shape.getName().equals("DLine"))
					{
						DLine line = (DLine) shape;
						g2.setColor(Color.CYAN);
						g2.drawRect(line.info.getX(),line.info.getY(),line.info.getWidth(),line.info.getHeight());
					}
					else if (shape.getName().equals("DText"))
					{
						DText text = (DText) shape;
						g2.setColor(Color.CYAN);
						g2.drawRect(text.info.getX(),text.info.getY(),text.info.getWidth(),text.info.getHeight());
						((DTextModel) selectedModel).setFontType(((Font) ControlPanel.fonts.getSelectedItem()).getName());
						((DTextModel) selectedModel).setText(ControlPanel.textDisplay.getText());		
					} 	
				}
			}
		}
		repaint();
	}
	
	// testing purposes
	public static void printList()
	{
		int count = 1;
		for (DShape d : shapesList)
		{
			System.out.println(count + " " + d.getName());
			count++;
		}
	}
	
	// testing purposes
	public static void printReverse()
	{
		int count = 1;
		for (ListIterator<DShape> iterator = shapesList.listIterator(shapesList.size()); iterator.hasPrevious();)
		{
			System.out.println(count + " " + ((DShape) iterator.previous()).getName());
			count++;
		
		}
	}
	
	public static void addShape(DShapeModel shapeModel)
	{
		if (shapeModel instanceof DRectModel)
		{
			DRect rect = new DRect();
			rect.info = (DRectModel) shapeModel;
			shapesList.add(rect);
			DShapeModel.listeners.add(shapeModel);
		}
		if (shapeModel instanceof DOvalModel)
		{
			DOval oval = new DOval();
			oval.info = (DOvalModel) shapeModel;
			shapesList.add(oval);
			DShapeModel.listeners.add(shapeModel);
		}
		if (shapeModel instanceof DLineModel)
		{
			DLine line = new DLine();
			line.info = (DLineModel) shapeModel;
			shapesList.add(line);
			DShapeModel.listeners.add(shapeModel);
		}
		if (shapeModel instanceof DTextModel)
		{
			DText textLine = new DText();
			textLine.info = (DTextModel) shapeModel;
			shapesList.add(textLine);
			DShapeModel.listeners.add(shapeModel);
		} 
	}
	
	public void mouseDragged(MouseEvent e) 
	{
		if(selected != null)
		{
			if(selected.getName().equals("DRect"))
			{
				update(e);
		
			}
			
			else if(selected.getName().equals("DOval"))
			{
				update(e);
			}
			
			else if(selected.getName().equals("DLine"))
			{
				update(e);
			}
<<<<<<< HEAD
			
			else if(selected.getName().equals("DText"))
			{
				update(e);
			}
		}
		
=======
			else if (selected.getName().equals("DText"))
			{
				update(e);
			}
		}	
>>>>>>> c42b81463d0f6a4c2755dea154ca1ec65ff7510e
	}
	
	public void update(MouseEvent e)
	{
		selectedModel.setX(e.getX());
		selectedModel.setY(e.getY());
		
	}
	
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public ArrayList<Point> getKnobs()
	{
		return knobs;
	}
}
