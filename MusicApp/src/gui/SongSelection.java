package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.Main;

public class SongSelection extends JPanel implements GUIInterface{

	private static final long serialVersionUID = 2L;
	
	JMenuBar menuBar;
	JMenu options;
	JMenu server;
	JMenu config;
	
	JList songs;
	DefaultListModel songModel;
	JScrollPane songScroll;
	ArrayList<String> songNames;
	
	JMenuItem addSong;
	JMenuItem removeSong;
	JMenuItem connect;
	JMenuItem host;
	JMenuItem disconnect;
	JMenuItem audioControls;
	JMenuItem reload;
	JMenuItem about;
	
	JFileChooser addFile;
	
	JLabel searchBarName;
	JTextField search;
	
	MenuListener menuListener;
	FileNameExtensionFilter filter;
	
	String filePath;
	
	public SongSelection() {
		try {
			filePath = new File("./Music/Music").getCanonicalPath();
		} catch (IOException e) {
			Main.errGUI = new ErrorGUI(e.toString());
			e.printStackTrace();
		}
		
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		initVars();
		initDisplay();
		initActionListeners();
	}
	
	public void addFile(File file) { 
		File newFile = new File(filePath + file.getName());
				
		try {
			if (!newFile.exists()) {
				System.out.println("Creating new file");
				newFile.createNewFile();
			}
			Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
			Main.errGUI = new ErrorGUI(e.toString());
		}
		addSongToList(file.getName());
	}
	
	public void addSongToList(String name) {
		songModel.addElement(name);
	}

	@Override
	public void initVars() {
		menuBar = new JMenuBar();
		
		addSong = new JMenuItem("Add Song");
		addSong.setMnemonic(KeyEvent.VK_A);
		addSong.setActionCommand("AddSong");
		
		removeSong = new JMenuItem("Remove Song");
		removeSong.setMnemonic(KeyEvent.VK_R);
		removeSong.setActionCommand("RemoveSong");
		
		host = new JMenuItem("Host Server");
		host.setMnemonic(KeyEvent.VK_H);
		host.setActionCommand("Host");
		
		connect = new JMenuItem("Connect To Server");
		connect.setMnemonic(KeyEvent.VK_C);
		connect.setActionCommand("Connect");
		
		disconnect = new JMenuItem("Disconnect From Server");
		disconnect.setMnemonic(KeyEvent.VK_D);
		disconnect.setActionCommand("Disconnect");
		
		audioControls = new JMenuItem("Volume/Audio Controls");
		audioControls.setMnemonic(KeyEvent.VK_E);
		audioControls.setActionCommand("AudioControls");
		
		reload = new JMenuItem("Reload");
		reload.setMnemonic(KeyEvent.VK_Z);
		reload.setActionCommand("Reload");
		
		about = new JMenuItem("About");
		about.setMnemonic(KeyEvent.VK_I);
		about.setActionCommand("About");
		
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
		
		addFile = new JFileChooser("Add Song");
		filter = new FileNameExtensionFilter("m4a, mp3 or WAV", "m4a", "mp3", "wav");
		addFile.setFileFilter(filter);
		
		songModel = new DefaultListModel();
		songs = new JList(songModel);
		songScroll = new JScrollPane(songs);
		songNames = new ArrayList<String>();
		
		menuListener = new MenuListener();
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
		add(songScroll, 3);

		
	}

	@Override
	public void initActionListeners() {
		addSong.addActionListener(menuListener);
		removeSong.addActionListener(menuListener);
		connect.addActionListener(menuListener);
		host.addActionListener(menuListener);
		disconnect.addActionListener(menuListener);
		audioControls.addActionListener(menuListener);
		reload.addActionListener(menuListener);
		about.addActionListener(menuListener);
	}
	
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			if (e.getActionCommand().equals("AddSong")) {
				int returnVal = addFile.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = addFile.getSelectedFile();
					addFile(file);
				}
				
			}
		}
		
	}

}
