package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class ShapeImplementation implements Shape{
	private Color frameColor ;
	private Color fillColor ;
	private int currentX=-1,currentY=-1;
	
	//implemented
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stubz
		if(position==null){
			currentX=-1;
			currentY=-1;
		}else{
			currentX=position.x;
			currentY=position.y;
		}
		
	}

	
	@Override //implemented
	public Point getPosition() {
		// TODO Auto-generated method stub
		
		return new Point(currentX,currentY);
	}

	@Override
	public void setProperties(java.util.Map<String, Double> properties){
		// TODO Auto-generated method stub
		
	}
	@Override
	public java.util.Map<String, Double> getProperties(){
		return null;
		
	}

	@Override
	//implemented
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		frameColor=color;	
	}

	@Override
	//implemented
	public Color getColor() {
		// TODO Auto-generated method stub
		return frameColor ;
	}

	@Override
	//implemented
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		fillColor=color;
	}

	@Override
	//implemented
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return fillColor;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return null;
		
	}

	
}
