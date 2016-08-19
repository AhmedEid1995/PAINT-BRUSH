package eg.edu.alexu.csd.oop.draw;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Square extends ShapeImplementation{
	
    Map <String, Double> squareProperities = new HashMap<String, Double>();
	
	public Square() {
		squareProperities.put("Length", null);	
	}
	
	public java.util.Map<String, Double> getProperties(){
		return squareProperities;
	}
	
	public void setProperties(java.util.Map<String, Double> properties){
		// TODO Auto-generated method stub
		
		squareProperities=properties;
	}

	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) canvas;	
		g.setColor(this.getColor());
		g.fillRect(getPosition().x, getPosition().y,squareProperities.get("Length").intValue(),squareProperities.get("Length").intValue());
		g.setColor(this.getColor());
		g.drawRect(getPosition().x, getPosition().y,squareProperities.get("Length").intValue(),squareProperities.get("Length").intValue());
		
	}
	@Override
	public Object clone() throws CloneNotSupportedException {

		Shape newShape =new Square();
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
