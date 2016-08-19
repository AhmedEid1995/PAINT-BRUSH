package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.util.List;

public class PaintController {
	
	
	private DrawingEngineImplementation model = new DrawingEngineImplementation().getInstance();
	
	private static PaintController newController =null;
	
	
	public static PaintController getInstance(){			//Singelton Pattern 
		if(newController==null){
			newController=new PaintController();
		}
		return newController;
	}
	
	public static void destoryInstance(){							//Just for creating more objects to Junit test .
		newController = null;
   }
	
	
	 void refreshModel (Graphics canvas) {
		 this.model.refresh(canvas);
	}
	 
	 void addShapeToModel(Shape shape) {
		 this.model.addShape(shape);
	 }
	
	 void removeShapeFromModel(Shape shape) {
		 this.model.removeShape(shape);
	 }
	 
	 
	 void updateShapeFromModel(Shape oldShape, Shape newShape) {
		 model.updateShape(oldShape, newShape);
	 }
	
	 
	 Shape[] getShapes() { 
		 return model.getShapes();
	 }
	 
	 List<Class<? extends Shape>> getSupportedShapesFromModel() {
		 return model.getSupportedShapes();
	 }
	 
	 
	 void undoModel() {
		 model.undo();
	 }
	 
	 void redoModel() {
		 model.redo();
	 }
	 
	 void saveModel(String path) {
		 model.save(path);
	 }
	 
	 void loadModel(String path) {
		 model.load(path);
	 }
}
