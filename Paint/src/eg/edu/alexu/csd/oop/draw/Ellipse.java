package eg.edu.alexu.csd.oop.draw;



import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends Circle{
/*******************************************************/
	public Ellipse(){
		
		circleProperities.put("longRadius", null);
	}
/*******************************************************/
	public void setProperties(java.util.Map<String, Double> properties){
		// TODO Auto-generated method stub
		circleProperities=properties;
	}
/*******************************************************/	
	public java.util.Map<String, Double> getProperties(){
		return circleProperities;
	}
/*******************************************************/
	public void draw(Graphics canvas) { 
		// TODO Auto-generated method stub 
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(this.getFillColor());
		g.fill(new Ellipse2D.Double(getPosition().x,getPosition().y,circleProperities.get("shortRadius").intValue(),circleProperities.get("longRadius").intValue()));
		g.setColor(this.getColor());
		g.draw(new Ellipse2D.Double(getPosition().x,getPosition().y,circleProperities.get("shortRadius").intValue(),circleProperities.get("longRadius").intValue()));
		
	}	
/*******************************************************/
	@Override
	public Object clone() throws CloneNotSupportedException {

		Shape newShape =new Ellipse();
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