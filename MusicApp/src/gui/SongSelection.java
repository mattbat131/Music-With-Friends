package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class SongSelection extends JPanel implements GUIInterface{

	private static final long serialVersionUID = 2L;
	
	JMenuBar menuBar;
	JMenu options;
	JMenu server;
	JMenu config;
	
	JMenuItem addSong;
	JMenuItem removeSong;
	JMenuItem connect;
	JMenuItem host;
	JMenuItem disconnect;
	JMenuItem audioControls;
	JMenuItem reload;
	JMenuItem about;
	
	JLabel searchBarName;
	JTextField search;
	
	public SongSelection() {
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		initVars();
		initDisplay();
		
	}
	

	@Override
	public void initVars() {
		menuBar = new JMenuBar();
		
		addSong = new JMenuItem("Add Song");
		addSong.setMnemonic(KeyEvent.VK_A);
		
		removeSong = new JMenuItem("Remove Song");
		removeSong.setMnemonic(KeyEvent.VK_R);
		
		host = new JMenuItem("Host Server");
		host.setMnemonic(KeyEvent.VK_H);
		
		connect = new JMenuItem("Connect To Server");
		connect.setMnemonic(KeyEvent.VK_C);
		
		disconnect = new JMenuItem("Disconnect From Server");
		disconnect.setMnemonic(KeyEvent.VK_D);
		
		audioControls = new JMenuItem("Volume/Audio Controls");
		audioControls.setMnemonic(KeyEvent.VK_E);
		
		reload = new JMenuItem("Reload");
		reload.setMnemonic(KeyEvent.VK_Z);
		
		about = new JMenuItem("About");
		about.setMnemonic(KeyEvent.VK_I);
		
		options = new JMenu("Options");
		options.add(addSong);
		options.add(removeSong);
		
		server = new JMenu("Server");
		server.add(host);
		server.add(connect);
		server.add(disconnect);
		
		config = new JMenu("Config/Help");
		config.add(audioControls);
		config.add(reload);
		config.add(about);
		
		searchBarName = new JLabel("Search");
		search = new JTextField();
	}

	@Override
	public void initDisplay() {
		setLayout(new SpringLayout());
	    
		menuBar.add(options);
		menuBar.add(server);
		menuBar.add(config);
		
		searchBarName.setText("Search");
		searchBarName.setSize(new Dimension(100, 100));
		search.setPreferredSize(new Dimension(100, 50));
		
		add(menuBar, 0);
		add(searchBarName, 1);
		add(search, 1);

		
	}

	@Override
	public void initActionListeners() {

	}

}
