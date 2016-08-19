package eg.edu.alexu.csd.oop.draw;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class RectangleProperities {
	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	Map<String, Double> squareProperities = new HashMap<String, Double>();
	private Rectangle x = new Rectangle();
	private boolean flag;

	/** * Launch the application. */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RectangleProperities window = new RectangleProperities();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/** * Create the application. */
	public RectangleProperities() {
		initialize();
	}

	/** * Initialize the contents of the frame. */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblRectangle = new JLabel("Rectangle");
		lblRectangle.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRectangle.setBounds(126, 27, 127, 31);
		frame.getContentPane().add(lblRectangle);
		JLabel lblCenterX = new JLabel("Center x :");
		lblCenterX.setBounds(27, 102, 70, 14);
		frame.getContentPane().add(lblCenterX);
		textField = new JTextField();
		textField.setBounds(145, 99, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		JLabel lblCenterY = new JLabel("Center y :");
		lblCenterY.setBounds(27, 127, 56, 14);
		frame.getContentPane().add(lblCenterY);
		textField_1 = new JTextField();
		textField_1.setBounds(145, 130, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		JLabel lblNewLabel = new JLabel("Length");
		lblNewLabel.setBounds(27, 164, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		textField_2 = new JTextField();
		textField_2.setBounds(145, 161, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		JLabel lblWidth = new JLabel("width");
		lblWidth.setBounds(27, 193, 46, 14);
		frame.getContentPane().add(lblWidth);
		textField_3 = new JTextField();
		textField_3.setBounds(145, 190, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!flag) {
					Point center = new Point();
					center.x = Integer.parseInt(textField.getText());
					center.y = Integer.parseInt(textField_1.getText());
					x.setPosition(center);
				}
				squareProperities.put("Length",
						Double.parseDouble(textField_2.getText()));
				squareProperities.put("height",
						Double.parseDouble(textField_3.getText()));
				x.setProperties(squareProperities);
				x.draw(Gui.panel.getGraphics());
				frame.dispose();
			}
		});
		btnNewButton.setBounds(142, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

	Shape getShape() {
		return x;
	}

	public void setPoint(Point pt1, boolean f) {
		flag = f;
		x.setPosition(pt1);
	}
}