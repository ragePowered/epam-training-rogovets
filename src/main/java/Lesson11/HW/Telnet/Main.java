package Lesson11.HW.Telnet;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 5/12/14
 * Time: 1:50 PM
 */

/**
 * Создать на основе сокетов клиент/серверное визуальное приложение:
 * Телнет. Создать программу, которая соединяется с указанным
 * сервером по указанному порту и производит обмен текстовой
 * информацией.
 * */

public class Main {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(new MainFrame());
	}
}

class MainFrame extends JFrame implements Runnable{

	private AtomicBoolean isServer = new AtomicBoolean(false);
	private AtomicBoolean isClient = new AtomicBoolean(false);

	private Socket socket;
	private String userName;
	private BufferedReader reader;
	private PrintWriter writer;

	private int port;

	private JPanel mainPanel = new JPanel(new BorderLayout());
	private JPanel northPanel = new JPanel(new GridLayout(1, 3));
	private JTextField serverIPTextField = new JTextField("server IP");
	private JTextField serverPortTextField = new JTextField("server port");
	private JButton connectButton = new JButton("Connect");
	private JPanel centerPanel = new JPanel(new BorderLayout());
	private JTextArea chatTextArea = new JTextArea();
	private JPanel southPanel = new JPanel(new BorderLayout());
	private JTextField messageTextField = new JTextField("Enter your message here");
	private JButton sendButton = new JButton("Send");

	MainFrame(){
		try {
			port = Integer.valueOf(JOptionPane.showInputDialog(null,"Enter open port for your server"));

			while ((userName = JOptionPane.showInputDialog(null,"Enter you name")).length() < 3) {
				JOptionPane.showMessageDialog(null, "Your name must contain at least 3 characters", "Incorrect username", JOptionPane.NO_OPTION);
			}
		} catch (NumberFormatException | NullPointerException e){
			System.exit(0);
		}

		frameCalibration();
		Thread serverThread = new Thread(new Server());
		serverThread.start();
	}

	private void frameCalibration(){
		setSize(300, 350);
		setMinimumSize(new Dimension(300, 200));
		setLocationByPlatform(true);
		setTitle("Chat (" + userName + ")");

		DefaultCaret caret = (DefaultCaret)chatTextArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(chatTextArea);

		chatTextArea.setEditable(false);
		chatTextArea.setLineWrap(true);

		serverIPTextField.addActionListener(new ConnectAction());
		serverIPTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				serverIPTextField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		serverIPTextField.setHorizontalAlignment(JTextField.CENTER);

		serverPortTextField.addActionListener(new ConnectAction());
		serverPortTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				serverPortTextField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {}
		});
		serverPortTextField.setHorizontalAlignment(JTextField.CENTER);

		connectButton.addActionListener(new ConnectAction());

		sendButton.addActionListener(new SendAction());
		sendButton.setEnabled(false);

		messageTextField.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				messageTextField.setText("");
			}

			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		messageTextField.addActionListener(new SendAction());
		messageTextField.setEnabled(false);

		northPanel.add(serverIPTextField);
		northPanel.add(serverPortTextField);
		northPanel.add(connectButton);

		centerPanel.add(scrollPane, BorderLayout.CENTER);

		southPanel.add(messageTextField, BorderLayout.CENTER);
		southPanel.add(sendButton, BorderLayout.EAST);

		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		mainPanel.add(southPanel, BorderLayout.SOUTH);

		add(mainPanel);
	}
	private void successfulConnection(String side){
		connectButton.setText(side);
		connectButton.setBackground(Color.green);
		connectButton.setEnabled(false);
		serverIPTextField.setEnabled(false);
		serverPortTextField.setEnabled(false);
		sendButton.setEnabled(true);
		messageTextField.setEnabled(true);
		new Thread(new PortListener()).start();
	}

	class SendAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			writer.println(userName + ": " + messageTextField.getText());
			writer.flush();
			chatTextArea.append("Me: " + messageTextField.getText() + "\n");
			messageTextField.setText("");
			messageTextField.grabFocus();

		}
	}

	class ConnectAction extends AbstractAction{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!serverIPTextField.getText().equals("") && !serverPortTextField.getText().equals("") && !isServer.get() && !isClient.get()){
				String IP = serverIPTextField.getText().trim();
				try {
					port = Integer.valueOf(serverPortTextField.getText().trim());
					socket = new Socket(IP, port);
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

					successfulConnection("Client");

				} catch (IllegalArgumentException e1){
					connectButton.grabFocus();
					JOptionPane.showMessageDialog(null, "Bad port!");
				} catch (UnknownHostException e1) {
					connectButton.grabFocus();
					JOptionPane.showMessageDialog(null, "Bad IP!");
				} catch (ConnectException e1){
					connectButton.grabFocus();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e2) {
						e2.printStackTrace();  //Exception
					}
					JOptionPane.showMessageDialog(null, "server not ready or unreachable. Please, try again");
				} catch (IOException e1) {
					e1.printStackTrace();  //Exception
				}
			} else {
				connectButton.grabFocus();
				JOptionPane.showMessageDialog(null, "Empty IP/port");
			}
		}
	}

	class Server implements Runnable{

		@Override
		public void run() {
			while (!isServer.get() && !isClient.get() && !Thread.currentThread().isInterrupted()){
				try {
					ServerSocket server = new ServerSocket(port);
					socket = server.accept();
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				} catch (IOException e) {
					e.printStackTrace();
				}
				isServer.set(true);
			}

			successfulConnection("server");

		}
	}

	class PortListener implements Runnable{

		@Override
		public void run() {
			StringBuilder received = new StringBuilder(100);
			while (!Thread.currentThread().isInterrupted()){
				if (socket.isClosed()) JOptionPane.showMessageDialog(null, "Companion leaved...");
				try {
					received.append(reader.readLine());
				} catch (NullPointerException e){
					System.exit(0);
				} catch (IOException e) {
					return;
				}
				chatTextArea.append(received.toString() + "\n");
				received.delete(0, received.length());
			}
		}
	}

	@Override
	public void run() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		mainPanel.grabFocus();
	}
}
