import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	private static final long serialVersionUID = 1L;

	private String name, ipAddress;
	private int port;

	private DatagramSocket socket;
	private InetAddress ip;

	private Thread send;

	private int ID = -1;

	public Client(String name, String address, int port) {
		this.name = name;
		this.ipAddress = address;
		this.port = port;
	}

	public String Receive() {
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String message = new String(packet.getData());

		return message;
	}

	public void Send(final byte[] data) {
		this.send = new Thread("Send") {
			public void run() {
				DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		send.start();
	}

	public boolean OpenConnection(String address) {
		try {
			this.socket = new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
			return false;
		}
		try {
			this.ip = InetAddress.getByName(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * @Override public void actionPerformed(ActionEvent e) { String source =
	 * null; try { source = ((JButton)e.getSource()).getText(); }
	 * catch(Exception err) { err.printStackTrace(); }
	 * 
	 * if(source == null) return; if(source == "Send"){
	 * Send(message_textfield.getText()); } }
	 */
	public String GetName() {
		return this.name;
	}

	public String GetAddress() {
		return this.ipAddress;
	}

	public int GetPort() {
		return this.port;
	}

	public void SetID(int id) {
		this.ID = id;
	}

	public int GetID() {
		return this.ID;
	}

	public void Close() {
		new Thread() {
			public void run() {
				synchronized (socket) {
					socket.close();
				}
			}
		}.start();
	}

}
