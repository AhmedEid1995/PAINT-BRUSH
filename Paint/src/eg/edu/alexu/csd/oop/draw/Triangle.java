package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Triangle extends LineSegment{

	
	public Triangle(){


		lineSegment.put("y2", null);
		lineSegment.put("x2",null);
		
	}
	public void setProperties(java.util.Map<String, Double> properties){
		// TODO Auto-generated method stub
		lineSegment=properties;
	}
	
	public java.util.Map<String, Double> getProperties(){
		return lineSegment;
		
	}
	public void draw(Graphics canvas) { 
		// TODO Auto-generated method stub 
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(this.getFillColor());
		g.fillPolygon(new int[] {getPosition().x, lineSegment.get("x1").intValue(),lineSegment.get("x2").intValue()}, new int[] {getPosition().y, lineSegment.get("y1").intValue(), lineSegment.get("y2").intValue()}, 3);
		g.setColor(this.getColor());
		g.drawPolygon(new int[] {getPosition().x, lineSegment.get("x1").intValue(),lineSegment.get("x2").intValue()}, new int[] {getPosition().y, lineSegment.get("y1").intValue(), lineSegment.get("y2").intValue()}, 3);
		
} 
	@Override
	public Object clone() throws CloneNotSupportedException {

		Shape newShape =new Triangle();
		newShape.setColor(this.getColor());
		newShape.setFillColor(this.getFillColor());
		Point x =new Point (this.getPosition().x,this.getPosition().y);	
		newShape.setPosition(x);
		Map <String, Double> a = new HashMap<String, Double>();
		a.putAll(this.getProperties());
		newShape.setProperties(a);
		return newShape;
		
	}
}