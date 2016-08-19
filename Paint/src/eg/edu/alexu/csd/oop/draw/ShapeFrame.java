package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;


import java.awt.Point;
import java.util.HashMap;

import java.util.Map;


import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ShapeFrame {
	 private JButton btnEnter;
	 private JFrame frame;
	 private String shapeName;
	 private Shape newShape ;
	 private JComboBox<String> comboBox;
	 private Map <String, Double> properties = new HashMap<String, Double>();
	 private JTextField[] textField=new JTextField[30];
	 private JLabel[] label=new JLabel[30];
	 private JComboBox<String> comboBox_1;
	 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShapeFrame window = new ShapeFrame(null,null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ShapeFrame(Shape Update,String className) {
		shapeName=className;
		newShape=Update;
		properties=newShape.getProperties();
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 413, 394);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		label[0] = new JLabel(shapeName);
		label[0].setFont(new Font("Tahoma", Font.ITALIC, 24));
		label[0].setBounds(157, 22, 158, 29);
		frame.getContentPane().add(label[0]);
		
		
		
		label[1]=new JLabel("PositionX");
		//label[1].setFont(new Font("Tahoma", Font.ITALIC, 24));
	    label[1].setBounds(33, 60, 113, 38);
	    frame.getContentPane().add(label[1]);
	    
	    textField[1] = new JTextField();
		textField[1].setBounds(167, 69, 108, 20);
		if(newShape.getPosition().x !=-1){textField[1].setText(Integer.toString(newShape.getPosition().x));}
		frame.getContentPane().add(textField[1]);
		textField[1].setColumns(10);
	    
		label[2]=new JLabel("PositionY");
		//label[2].setFont(new Font("Tahoma", Font.ITALIC, 24));
	    label[2].setBounds(33, 103, 94, 14);
	    frame.getContentPane().add(label[2]);
	    
	    textField[2] = new JTextField();
		textField[2].setBounds(167, 100, 108, 20);
		if(newShape.getPosition().y !=-1)textField[2].setText(Integer.toString(newShape.getPosition().y));
		frame.getContentPane().add(textField[2]);
		textField[2].setColumns(10);
		
	    
	    
		//if(properties.get("Length").toString()!=null)System.out.println("null");
		
		// Iterator it = properties.entrySet().iterator();
		 int i=3;
		 for(String key: properties.keySet()){
			 label[i]=new JLabel((String)key);
			 if(properties.get(key)!=null)textField[i]=new JTextField( properties.get(key).toString());
		        else textField[i]=new JTextField();
			 	label[i].setBounds(33, 103+(i-2)*36, 94, 14);
		        frame.getContentPane().add(label[i]);
		        textField[i].setBounds(167, 100+(i-2)*36, 108, 20);
		        frame.getContentPane().add(textField[i]);
		        textField[i].setColumns(10);
		        i++;
		       
	           
	        }
		
		   
		
		
		
		
		
		 btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Point center =new Point();
				
				if(newShape.getPosition().x==-1&&newShape.getPosition().y==-1){
				center.x=Integer.parseInt(textField[1].getText());
				center.y=Integer.parseInt(textField[2].getText());
				newShape.setPosition(center);
				
				}
				
				
				 int i=3;
				 for(String key: properties.keySet()){
						
			          // System.out.println(key);
			           properties.put((String)key , Double.parseDouble(textField[i].getText()));
				        i++;
				       
			           
			        }

				 newShape.setProperties(properties);
				String color = (String) comboBox.getSelectedItem();	
				String fillColor = (String) comboBox_1.getSelectedItem();		
				newShape.setColor(getColor (color ));
				newShape.setFillColor(getColor (fillColor ));
				newShape.draw(Gui.panel.getGraphics());
				frame.dispose();
			}
		});
		btnEnter.setBounds(160, 313, 89, 23);
		frame.getContentPane().add(btnEnter);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setBounds(39, 210, 46, 14);
		frame.getContentPane().add(lblColor);
		
		JLabel lblFillColor = new JLabel("Fill Color");
		lblFillColor.setBounds(39, 252, 46, 14);
		frame.getContentPane().add(lblFillColor);
		comboBox = new JComboBox<String>();
		 
		comboBox.setBounds(186, 207, 95, 20);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Black");
		comboBox.addItem("Red");
		comboBox.addItem("Blue");
		comboBox.addItem("Cyan");
		comboBox.addItem("Gray");
		comboBox.addItem("Green");
		comboBox.addItem("Orange");
		comboBox.addItem("Pink");
		comboBox.addItem("Yellow");		
		comboBox.addItem("White");
		comboBox_1 = new JComboBox<String>();
		
		comboBox_1.setBounds(186, 252, 95, 20);
		frame.getContentPane().add(comboBox_1);
		comboBox_1.addItem("Black");
		comboBox_1.addItem("Red");
		comboBox_1.addItem("Blue");
		comboBox_1.addItem("Cyan");
		comboBox_1.addItem("Gray");
		comboBox_1.addItem("Green");
		comboBox_1.addItem("Orange");
		comboBox_1.addItem("Pink");
		comboBox_1.addItem("Yellow");		
		comboBox_1.addItem("White");
		
	}
	Color getColor (String color){
		
		if(color=="Black")
			return Color.BLACK;
		else if (color=="Blue")
			return Color.BLUE;
		else if (color=="Cyan")
			return Color.CYAN;
		else if (color=="Gray")
			return Color.GRAY;
		else if (color=="Green")
			return Color.GREEN;
		else if (color=="Orange")
			return Color.ORANGE;
		else if (color=="Pink")
			return Color.pink;
		else if (color=="Yellow")
			return Color.yellow;
		else if (color=="White")
			return Color.white;
		else if (color=="Red")
			return Color.RED;
		return null;
	}
	public JFrame getShapeFrame(){
		return frame;
	}
	public JButton getEnterButton(){
		return btnEnter;
	}
}
