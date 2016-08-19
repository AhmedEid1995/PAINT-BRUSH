package eg.edu.alexu.csd.oop.draw;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SaveProperities {

	JFrame frame;
	private JTextField textField;
	SaveAndLoad save =new SaveAndLoad();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveProperities window = new SaveProperities();
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
	public SaveProperities() {
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
				if(Gui.flag==1)
					Gui.engine.save(path);
				else if (Gui.flag==2)
					Gui.engine.load(path);
				Gui.flag=0;
				frame.dispose();
			}
		});
		btnXml.setBounds(189, 182, 89, 23);
		frame.getContentPane().add(btnXml);
	}
}
