import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name_textField;
	private JTextField ipaddress_textField;
	private JLabel ipaddress_label;
	private JLabel port_label;
	private JTextField port_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 380);
		setLocationRelativeTo(null); // spawn the window at the center
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name_textField = new JTextField();
		name_textField.setBounds(104, 46, 86, 20);
		contentPane.add(name_textField);
		name_textField.setColumns(10);
		
		JLabel name_label = new JLabel("Name");
		name_label.setBounds(124, 25, 46, 14);
		contentPane.add(name_label);
		
		ipaddress_textField = new JTextField();
		ipaddress_textField.setBounds(64, 119, 165, 20);
		contentPane.add(ipaddress_textField);
		ipaddress_textField.setColumns(10);
		
		ipaddress_label = new JLabel("IP Address");
		ipaddress_label.setBounds(117, 92, 59, 14);
		contentPane.add(ipaddress_label);
		
		port_label = new JLabel("Port");
		port_label.setBounds(117, 168, 59, 14);
		contentPane.add(port_label);
		
		port_textField = new JTextField();
		port_textField.setColumns(10);
		port_textField.setBounds(64, 189, 165, 20);
		contentPane.add(port_textField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(104, 276, 89, 23);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String source = null;
		try
		{
			source = ((JButton)e.getSource()).getText();
		}
		catch(Exception err)
		{
			err.printStackTrace();
		}
		
		if(source == null)
			return;
		
		if(source == "Login"){
			if(name_textField.getText() == "" || ipaddress_textField.getText() == "" || port_textField.getText() == ""){
				return;
			}
			String name = name_textField.getText();		 
			String ipAddress = ipaddress_textField.getText();
			int port = Integer.parseInt(port_textField.getText());
			login(name,ipAddress,port);
		}
	}
	
	private void login(String name, String ipAddress, int port){		
		dispose();
		new ClientWindow(name, ipAddress, port);	
	}
}
