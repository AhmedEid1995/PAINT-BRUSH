package eg.edu.alexu.csd.oop.draw;

public class Factory {

	private Shape tempShape;
	public Shape createShape(String shapeName) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> c = Class.forName("eg.edu.alexu.csd.oop.draw."+shapeName);
		tempShape = (Shape)c.newInstance();		
		return tempShape;
		
	}
	public String className(){
		String className = tempShape.getClass().getSimpleName();
		return className;
	}

}
