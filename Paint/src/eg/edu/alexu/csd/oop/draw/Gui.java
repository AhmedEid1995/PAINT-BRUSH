package eg.edu.alexu.csd.oop.draw;


import java.awt.EventQueue;
import java.awt.Point;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 public class Gui extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8788076063117693456L;

	
	private Shape shape;
	//private Factory factory =new Factory();
	private ShapeFrame shapeFrame;
	//private Point p=null;
	private List<Class<? extends Shape>> supportedShapes;	
	private JButton[] button=new JButton[100];
	private boolean line=false,clone=false,select=false,update=false;
	private Object Clone=new Object();
	private Object Select=new Object();
	private Object current =new Object();
	private Object Update=new Object();
	private Shape[] shapes=new Shape[100];
	private int itrator=0,btnPostition=0;
	private int lineCounter=0;
	//private Point p2=null;
	private PaintController control = new PaintController();
	private static Gui newGui =null;
	private ShapeButton btnShape=new ShapeButton();
	public static JFrame frame= null;
	public static JPanel panel = null;
	public static JFrame getJFrameInstance(){//Singelton Pattern 
		if(frame==null){
			frame=new JFrame();
		}
		return frame;
	}
	public static void destoryJFrameInstance(){//Just for creating more objects to Junit test
		frame = null;
   }
	public static JPanel getJPanelInstance(){//Singelton Pattern 
		if(panel==null){
			panel=new JPanel();
		}
		return panel;
	}
	public static void destoryJPanelInstance(){	//Just for creating more objects to Junit test
		panel = null;
   }
	public static Gui getInstance(){//Singelton Pattern 
		if(newGui==null){
			newGui=new Gui();
		}
		return newGui;
	}
	public static void destoryInstance(){							//Just for creating more objects to Junit test
		newGui = null;
   }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					Gui window = new Gui();
					
					window.getJFrameInstance().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
				
				);
		
		getJFrameInstance().setBounds(0, 0, 1500, 1500);
		getJFrameInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getJFrameInstance().getContentPane().setLayout(null);
		getJPanelInstance().setBounds(199, 76, 1400, 1400);
		getJFrameInstance().getContentPane().add(getJPanelInstance());
		
		
	}
	

	/**
	 * Create the application.
	 */
	public Gui() {
		
		initialize();
		
	}

	
	public void paint(){
		
		control.refreshModel(getJPanelInstance().getGraphics());
	}
	
	private void initialize() {
		
		
		supportedShapes=control.getSupportedShapesFromModel();
		
		for(Class<?> a:supportedShapes){
			String className = a.getName();
			//System.out.println(a.getName());
			String[] parts = className.split("draw.");
			className=parts[parts.length-1];
			
			button[btnPostition]=new JButton(className);
			button[btnPostition].setBounds(7, 35+(btnPostition+1)*35, 89, 23);
			getJFrameInstance().getContentPane().add(button[btnPostition]);
			button[btnPostition].addActionListener(btnShape);
			btnPostition++;
			
				
		}
		

		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaintController.getInstance().undoModel();
				getJPanelInstance().getGraphics().clearRect(0, 0, 500, 500);
				shapes=control.getShapes();
				
				if(itrator>1){itrator--;current=shapes[itrator-1];}
				paint();
			}
		});
		btnUndo.setBounds(7, 35+(btnPostition+1)*35, 89, 23);
		btnPostition++;
		getJFrameInstance().getContentPane().add(btnUndo);
		
		
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.redoModel();
				getJPanelInstance().getGraphics().clearRect(0, 0, 500, 500);
				shapes=control.getShapes();
				if(itrator<shapes.length){
				itrator++;
				current=shapes[itrator-1];
				}
				paint();
			}
		});
		btnRedo.setBounds(7, 35+(btnPostition+1)*35, 89, 23);
		btnPostition++;
		getJFrameInstance().getContentPane().add(btnRedo);
		
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select=true;
				line=false;clone=false;update=false;
				
			}
		});
		btnRemove.setBounds(7, 35+(btnPostition+1)*35, 89, 23);
		btnPostition++;
		getJFrameInstance().getContentPane().add(btnRemove);
		/****************************************************************************/
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update=true;select=true;
				line=false;clone=false;
	
			}
		});
		btnUpdate.setBounds(7, 35+(btnPostition+1)*35, 89, 23);
		btnPostition++;
		getJFrameInstance().getContentPane().add(btnUpdate);
		
		JButton btnClone = new JButton("Clone");
		btnClone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				line=false;select=false;update=false;clone=true;
				//Clone=current;
				try {
					Clone=((Shape) current).clone();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnClone.setBounds(7, 35+(btnPostition+1)*35, 89, 23);
		btnPostition++;
		getJFrameInstance().getContentPane().add(btnClone);
		
		JButton btnSave = new JButton("save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveFrame x =new SaveFrame();
				//Gui.flag=1;
				x.setFlag(1);
				x.getSaveFrame().setVisible(true);
				
			}
		});
		btnSave.setBounds(7, 35+(btnPostition+1)*35, 89, 23);
		btnPostition++;
		getJFrameInstance().getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveFrame x =new SaveFrame();
				//Gui.flag=2;
				x.setFlag(2);
				x.getSaveFrame().setVisible(true);
			}
		});
		btnLoad.setBounds(7, 35+(btnPostition+1)*35, 89, 23);
		btnPostition++;
		getJFrameInstance().getContentPane().add(btnLoad);
	
		JButton btnClassLoader = new JButton("LoadShapes");
		btnClassLoader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				int retValue = fc.showOpenDialog(new JPanel());
				if(retValue == JFileChooser.APPROVE_OPTION){
					File f = fc.getSelectedFile();
					 String path = f.getAbsolutePath();
					 JarClassLoader cl =new JarClassLoader();
					 try {
						 supportedShapes=cl.supportedShape(path);
						 
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}int t=btnPostition;
					getJPanelInstance().repaint();
					 for(Class<?> a:supportedShapes){
						 //System.out.println("YES");
						 
							String className = a.getName();
							System.out.println(className);
							//System.out.println(a.getName());
							String[] parts = className.split("draw.");
							className=parts[parts.length-1];
							
							button[t]=new JButton(className);
							button[t].setBounds(7, 35+(t+1)*35, 89, 23);
							getJFrameInstance().getContentPane().add(button[t]);
							button[t].addActionListener(btnShape);
							t++;
							
								
						}
				}

				
			}
		});
		
		btnClassLoader.setBounds(7, 35+(btnPostition+1)*35, 89, 23);
		btnPostition++;
		getJFrameInstance().getContentPane().add(btnClassLoader);
		
		getJPanelInstance().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               Point p=null;
               Point p2=null;
               p= evt.getPoint();
               
                if(select){
                	double minm=1e9,sq;
                	shapes=control.getShapes();
                	for(Shape x:shapes){
                			sq=Math.sqrt((x.getPosition().x-p.x)*(x.getPosition().x-p.x)+(x.getPosition().y-p.y)*(x.getPosition().y-p.y));
                			if(sq<minm){
                				minm=sq;
                				Select=(Shape)x;
                			}
                	}
                	
                	if(update){
                		try {
        					Update=((Shape) Select).clone();
        				} catch (CloneNotSupportedException e1) {
        					// TODO Auto-generated catch block
        					e1.printStackTrace();
        				}
                		shapeFrame= new ShapeFrame((Shape)Update,shape.getClass().getSimpleName());
                		shapeFrame.getShapeFrame().setVisible(true);
                		shapeFrame.getEnterButton().addActionListener(new ActionListener() {
                			public void actionPerformed(ActionEvent arg0) {
                				control.updateShapeFromModel((Shape)Select, (Shape)Update);
                				getJPanelInstance().getGraphics().clearRect(0, 0, 500, 500);
                				shapeFrame.getShapeFrame().setVisible(true);
                				
                			}
                		});

        				update=false;
        				select=false;
                	}else{control.removeShapeFromModel((Shape)Select);getJPanelInstance().getGraphics().clearRect(0, 0, 500, 500);}
                	paint();
                	
    				
    				
    				
                	
                }
                else if(clone){
                	((Shape) Clone).setPosition(p);
                	((Shape) Clone).draw(getJPanelInstance().getGraphics());
                	itrator++;
                	control.addShapeToModel((Shape)Clone);
                	try {
    					Clone=((Shape) current).clone();
    				} catch (CloneNotSupportedException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
                	
                	
                	}
                else  if(line==true){
                	lineCounter++;
                	if(lineCounter%2==0){
                		p2=p;
                		Double x=(double) p2.x;
                		Double y=(double) p2.y;
                		Map <String, Double> Map = new HashMap<String, Double>();
                		Map=shape.getProperties();
                		
                		Map.put("x1",x);
                		Map.put("y1",y);
                		shape.setProperties(Map);
                		shape.draw(getJPanelInstance().getGraphics());
                		if(lineCounter!=2){itrator++;control.addShapeToModel(shape);}
                		current=shapes[itrator-1];
                		shape=new LineSegment();	
                	}else {shape.setPosition(p);}
                }
                else shape.setPosition(p);
               
            }
           
        });
		
		
	}
	public class ShapeButton implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
			Factory factory =new Factory();
			String x=((JButton) event.getSource()).getText();
			if(x.equals("LineSegment"))line=true;
			try {
				shape =(Shape) factory.createShape(x);
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException e1) {
				e1.printStackTrace();
			}
			String className = shape.getClass().getSimpleName();
			shapeFrame=new ShapeFrame(shape, className);
			control.addShapeToModel(shape);
			current=(Shape)shape;
			itrator++;
			shapeFrame.getShapeFrame().setVisible(true);
		}

	}
	public void incrementIterator() {
		// TODO Auto-generated method stub
		itrator++;
	}

}