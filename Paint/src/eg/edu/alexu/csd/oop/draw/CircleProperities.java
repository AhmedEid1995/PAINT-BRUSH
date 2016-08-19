package eg.edu.alexu.csd.oop.draw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CircleProperities {

	public JFrame frame;
	private JTextField textField;
	Circle x =new Circle();
	 Map <String, Double> circleProperities = new HashMap<String, Double>();
	 private JTextField textField_1;
	 private JTextField textField_2;
	 private boolean flag;
	/**
	 * Launch the application.
	 */
public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CircleProperities window = new CircleProperities(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CircleProperities(Shape Update) {
		if(Update!=null){
			x=(Circle) Update;
			circleProperities=x.getProperties();
		
		}
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Circle");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblNewLabel.setBounds(157, 22, 158, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEnterCenterx = new JLabel("Enter Center .x ");
		lblEnterCenterx.setBounds(33, 60, 113, 38);
		frame.getContentPane().add(lblEnterCenterx);
		
		textField = new JTextField();
		textField.setBounds(167, 69, 108, 20);
		if(x.getPosition().x !=0){textField.setText(Integer.toString(x.getPosition().x));}
		
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterCenter = new JLabel("Enter Center . y");
		
		lblEnterCenter.setBounds(33, 103, 94, 14);
		frame.getContentPane().add(lblEnterCenter);
		
		textField_1 = new JTextField();
		textField_1.setBounds(167, 100, 108, 20);
		if(x.getPosition().y !=0)textField_1.setText(Integer.toString(x.getPosition().y));
		
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		JLabel lblEnterR = new JLabel("Enter R");
		lblEnterR.setBounds(33, 134, 46, 14);
		frame.getContentPane().add(lblEnterR);
		textField_2 = new JTextField();
		
		textField_2.setBounds(162, 131, 113, 20);
		//System.out.println(circleProperities.get("shortRadius").intValue());
		if(circleProperities.get("shortRadius")!=null)textField_2.setText(circleProperities.get("shortRadius").toString());
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Point center =new Point();
				
				
				
				
				if(!flag){
				center.x=Integer.parseInt(textField.getText());
				center.y=Integer.parseInt(textField_1.getText());
				x.setPosition(center);
				}
				circleProperities.put("shortRadius", Double.parseDouble(textField_2.getText()));
				x.setProperties(circleProperities);
				x.draw(Gui.panel.getGraphics());
				frame.dispose();
			}
		});
		btnEnter.setBounds(157, 227, 89, 23);
		frame.getContentPane().add(btnEnter);
	}
	public Shape getShape(){
		return x;
	}
	public void setPoint(Point pt1,boolean f){
		flag=f;
		x.setPosition(pt1);
		
	}
	
	
}