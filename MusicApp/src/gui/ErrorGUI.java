package gui;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class ErrorGUI extends JFrame{

	private static final long serialVersionUID = 4L;
	JTextField error;
	
	public ErrorGUI(String err) {
		error = new JTextField(err);
		error.setFocusable(false);
		
		add(error);
		
		setSize(500, 500);
		setVisible(true);
		pack();
	}

}
