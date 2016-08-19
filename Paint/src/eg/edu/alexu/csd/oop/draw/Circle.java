package eg.edu.alexu.csd.oop.draw;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Circle extends ShapeImplementation{
	
	
 Map <String, Double> circleProperities = new HashMap<String, Double>();

/*******************************************************/	
	public Circle() {
		circleProperities.put("shortRadius",null);
	}
/*******************************************************/	
	public java.util.Map<String, Double> getProperties() {
		return circleProperities;
	}
/*******************************************************/	
	public void setProperties(java.util.Map<String, Double> properties){
		// TODO Auto-generated method stub
		circleProperities=properties;
		
	}
/*******************************************************/
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) canvas;   
		g.setColor(this.getFillColor());		
		g.fillOval(getPosition().x,getPosition().y,circleProperities.get("shortRadius").intValue(),circleProperities.get("shortRadius").intValue());
		g.setColor(this.getColor());
		g.drawOval(getPosition().x,getPosition().y,circleProperities.get("shortRadius").intValue(),circleProperities.get("shortRadius").intValue());
		
	}
/*******************************************************/
	@Override
	public Object clone() throws CloneNotSupportedException {

		Shape newShape =new Circle();
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
