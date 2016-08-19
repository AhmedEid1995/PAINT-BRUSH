package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

public class Test {

	
	public static class Bedengan implements Shape{

		public Map<String, Double> myProperties;
		
		public Bedengan() {
			myProperties = new HashMap<>();
			myProperties.put("hamada", null);
		}
		@Override
		public Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}
		@Override
		public void setPosition(Point position) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Point getPosition() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setProperties(Map<String, Double> properties) {
			myProperties = properties;
			
		}

		@Override
		public Map<String, Double> getProperties() {
			// TODO Auto-generated method stub
			return myProperties;
		}

		@Override
		public void setColor(Color color) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Color getColor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setFillColor(Color color) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Color getFillColor() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void draw(Graphics canvas) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@org.junit.Test
	public void test1() {
		Rectangle x =new Rectangle();
		DrawingEngineImplementation y = new DrawingEngineImplementation();
		x.setPosition(null);
		x.setProperties(null);
		x.setColor(null);
		x.setFillColor(null);
		y.addShape(x);
		y.addShape(new Bedengan());y.addShape(new Bedengan());y.addShape(new Bedengan());y.addShape(new Bedengan());y.addShape(new Bedengan());
	
		y.save("F:/a.json");
		y.load("F:/a.json");
		Shape[] yy =y.getShapes();
		
		System.out.println(yy.length);
	
	}
	
	
	@org.junit.Test
	public void test2() {
		DrawingEngine solver = new DrawingEngineImplementation();
		
		Shape dummy = new Bedengan();
		
		solver.addShape(dummy);
		
		
		dummy.setProperties(null);
		
		solver.save("F:/aa.json");
		
		solver.load("F:/aa.json");
		
		Shape[] ans = solver.getShapes();
		
		Assert.assertEquals(1, ans.length);
		
		System.out.println(ans[0].getProperties());
		
		Assert.assertEquals(true, dummy.getProperties() == null &&  ans[0].getProperties() == null);
		
		
		
			
			
		
	}

}
