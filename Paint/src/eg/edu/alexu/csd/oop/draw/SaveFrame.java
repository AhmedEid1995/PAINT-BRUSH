package eg.edu.alexu.csd.oop.draw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveFrame {
	private int flag =-1;
	private JFrame frame;
	private PaintController control = new PaintController();
	private Gui window =new Gui ();
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveFrame window = new SaveFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SaveFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEnterDirectory = new JLabel("Enter Directory");
		lblEnterDirectory.setBounds(28, 64, 93, 27);
		frame.getContentPane().add(lblEnterDirectory);
		
		textField = new JTextField();
		textField.setBounds(155, 64, 238, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnXml = new JButton("Enter");
		btnXml.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String path = textField.getText();
				if(flag==1)
					control.saveModel(path);
				else if (flag==2){
					control.loadModel(path);
					Shape[] loadedShapes=control.getShapes();
					for(int i =0;i<loadedShapes.length;i++){
						loadedShapes[i].draw(window.panel.getGraphics());
			       //	Gui.engine.addShape(loadedShapes[i]);			        	
						window.incrementIterator(); 
					}
				}
				flag=-1;
				frame.dispose();
			}
		});
		btnXml.setBounds(189, 182, 89, 23);
		frame.getContentPane().add(btnXml);
	}
	public int getFlag() {
		return flag;
		// TODO Auto-generated method stub
		
	}
	public void setFlag(int x) {
		 flag=x;
		// TODO Auto-generated method stub
	}
	public JFrame getSaveFrame(){
		return frame;
	}
}