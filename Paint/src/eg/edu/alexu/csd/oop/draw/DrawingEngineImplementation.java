package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DrawingEngineImplementation implements DrawingEngine{
	 
	
	private Shape[] shapes=new Shape[100] ;
	
	private boolean undoFlag=false,redoFlag=false,flag=false,loadFlag=false;;
	private Stack<State> undo =new Stack<State>();
	private Stack<State> redo=new Stack<State>();
	private  LinkedList<Shape> shapesList=new LinkedList<Shape>();
	private List supportedShapes = new ArrayList<Class<?>>();
	private static DrawingEngineImplementation newDraw =null;
	
	
	public DrawingEngineImplementation getInstance(){			//Singelton Pattern 
		if(newDraw==null){
			newDraw=new DrawingEngineImplementation();
		}
		return newDraw;
	}
	
	public static void destoryInstance(){							//Just for creating more objects to Junit test
		newDraw = null;
   }
	
	
	
	@Override
	public void refresh(Graphics canvas) {
		for(Shape x : shapesList){
			x.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		shapesList.add(shape);
		State e=new State();
		e.shape=shape;
		e.add=true;
		if(undoFlag){e.undo=true;undoFlag=false;}
		undo.add(e);
		redo.clear();
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		if(shape==null||shapesList.size()==0)throw new RuntimeException();
		for(Shape x:shapesList){
			if(x.equals(shape)==true)flag=true;
			
		}
		if(flag==false)throw new RuntimeException();
		else flag=false;
		State e=new State();
		e.shape=shape;
		e.add=false;
		if(redoFlag){e.redo=true;redoFlag=false;}
		undo.add(e);
		
		for(int i=0;i<shapesList.size();i++){
				if(shapesList.get(i).equals(shape))
				{shapesList.remove(i);break;}
		}
		
		redo.clear();

	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		
		if(oldShape==null||shapesList.size()==0)throw new RuntimeException();
		for(Shape x:shapesList){
			if(x.equals(oldShape)==true)flag=true;
			
		}
		if(flag==false)throw new RuntimeException();
		else flag=false;
		undoFlag=true;redoFlag=true;
		removeShape(oldShape);
		addShape(newShape);
		
		
		
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		shapes=new Shape[shapesList.size()];
		for(int i=0;i<shapesList.size();i++){
			shapes[i]=shapesList.get(i);
		}
		return shapes;
	}

	
	
	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		JarClassLoader x=new JarClassLoader();
		try {
			supportedShapes=x.supportedShape();
			//supportedShapes.add((Class<? extends Shape>) );
			
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		supportedShapes.add(Circle.class);
		supportedShapes.add(Ellipse.class);
		supportedShapes.add(Rectangle.class);
		supportedShapes.add(Square.class);
		supportedShapes.add(LineSegment.class);
		supportedShapes.add(Triangle.class);
		//System.out.println(Triangle.class.getName());
		
		return supportedShapes;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

		if(!loadFlag){
			if(!undo.isEmpty()){
				
				State x=(State)undo.peek();
				undo.pop();
				if(x.add){shapesList.removeLast();}//removeShape(x.shape);
				else {shapesList.addLast(x.shape);}// addShape(x.shape);
				x.add=!x.add;
				redo.push(x);
				if(x.undo){
					State y=(State)undo.peek();
					undo.pop();
					if(y.add){shapesList.removeLast();}//removeShape(x.shape);
					else {shapesList.addLast(y.shape);}// addShape(x.shape);
					y.add=!y.add;
					redo.push(y);
					
				}
				
				
				
				
			}else throw new RuntimeException();
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if(!redo.isEmpty()){
			
			State x=(State)redo.peek();
			redo.pop();
			if(x.add){shapesList.removeLast();}//removeShape(x.shape);
			else {shapesList.addLast(x.shape);}//addShape(x.shape);
			x.add=!x.add;
			undo.push(x);
			if(x.redo){
				State y=(State)redo.peek();
				redo.pop();
				if(y.add){shapesList.removeLast();}//removeShape(x.shape);
				else {shapesList.addLast(y.shape);}//addShape(x.shape);
				y.add=!y.add;
				undo.push(y);
			}
		
		}else throw new RuntimeException();
	
	}


		@Override
		public void save(String path) {
			// TODO Auto-generated method stub
			SaveAndLoad x =new SaveAndLoad();
			String extension = "";
		
			int i = path.lastIndexOf('.');
			if (i > 0) {
			    extension = path.substring(i+1);
			}
			if(extension.equalsIgnoreCase("xml")){
				x.SaveXml(path,this.getShapes());
			}else if (extension.equalsIgnoreCase("json")){
				x.SaveJson(path,this.getShapes());
			}else{
				throw new RuntimeException();
			}
			
		}
		
		
	
		@Override
		public void load(String path) {
			// TODO Auto-generated method stub
			loadFlag=true;
			SaveAndLoad x =new SaveAndLoad();
			shapesList.clear();
			String extension = "";
		
			int i = path.lastIndexOf('.');
			if (i > 0) {
			    extension = path.substring(i+1);
			}
			if(extension.toLowerCase().endsWith("xml")){
				shapesList=new LinkedList<Shape>(x.loadXml(path));
			}else if (extension.toLowerCase().endsWith("json")){
				
				shapesList=new LinkedList<Shape>(x.loadJson(path));
			}else{
				throw new RuntimeException();
			}
		}


	

}
