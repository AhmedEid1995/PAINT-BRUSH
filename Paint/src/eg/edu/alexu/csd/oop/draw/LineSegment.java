package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public class LineSegment extends ShapeImplementation{
	java.util.Map< String, Double >lineSegment=new HashMap<String, Double>();
	/*******************************************************/
	public LineSegment(){
		lineSegment.put("x1", null);
		lineSegment.put("y1", null);
		
	}
	/*******************************************************/
	public void setProperties(java.util.Map<String, Double> properties) {
		// TODO Auto-generated method stub
		lineSegment=properties;
	}
	/*******************************************************/
	public java.util.Map<String, Double> getProperties(){
		return lineSegment;
	}
	/*******************************************************/
	public void draw(Graphics canvas) { 
		// TODO Auto-generated method stub 
	
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(this.getFillColor());
		g.drawLine(getPosition().x,getPosition().y,lineSegment.get("x1").intValue(), lineSegment.get("y1").intValue());
	} 
	/*******************************************************/
	@Override
	public Object clone() throws CloneNotSupportedException {

		Shape newShape =new LineSegment();
		newShape.setColor(this.getColor());
		newShape.setFillColor(this.getFillColor());
		Point x =new Point (this.getPosition().x,this.getPosition().y);	
		newShape.setPosition(x);
		Map <String, Double> a = new HashMap<String, Double>();
		a.putAll(this.getProperties());
		newShape.setProperties(a);
		return newShape;
		
	}
	/*******************************************************/
}