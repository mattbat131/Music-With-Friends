package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class MusicGUI extends JFrame implements GUIInterface{

	private static final long serialVersionUID = 1L;
		
	JPanel songControl;
	SongSelection songSelection;
	JPanel songPlaying;
	
	JButton playButton;
	JButton nextSong;
	JButton prevSong;
	JButton quit;
	
	GridBagConstraints c;
	
	public MusicGUI() {
		super("Music Player");
		setSize(1000, 500);
		setForeground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		
		initVars();
		initDisplay();
		initActionListeners();

	}
	
	@Override
	public void initVars() {		
		songControl = new JPanel();
		songSelection = new SongSelection();
		songPlaying = new SongPlaying();
		
		playButton = new JButton("PLAY");
		nextSong = new JButton(">>");
		prevSong = new JButton("<<");
		
		quit = new JButton();
		
		playButton.setMnemonic(KeyEvent.VK_SPACE);
		playButton.setName("PLAY");
		
		nextSong.setMnemonic(KeyEvent.VK_RIGHT);
		nextSong.setName(">>");
		
		prevSong.setMnemonic(KeyEvent.VK_LEFT);
		prevSong.setName("<<");
		
		c = new GridBagConstraints();
	}
	
	@Override
	public void initDisplay() {
		setLayout(new GridBagLayout());
		
		songControl.setLayout(new GridBagLayout());
		songControl.setPreferredSize(new Dimension(500, 50));
		
		c.fill = GridBagConstraints.CENTER;
		c.ipadx = 100;
		c.gridx = 0;
		c.gridy = 0;
		songControl.add(prevSong, c);
		
		c.gridx = 1;
		songControl.add(playButton, c);
		
		c.gridx = 2;
		songControl.add(nextSong, c);

		
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 200;
		c.ipady = 500;
		c.gridx = 0;
		c.gridy = 1;
		add(songSelection, c);
		
		c.fill = GridBagConstraints.PAGE_START;
		c.ipadx = 500;
		c.ipady = 50;
		c.gridx = 0;
		c.gridy = 0;
		add(songPlaying, c);
		
		c.fill = GridBagConstraints.NORTH;
		c.ipady = 10;
		c.ipadx = 100;
		c.weighty = 10;
		c.gridy = 0;
		c.gridx = 1;
		add(songControl, c);
		
		
		setVisible(true);
		setResizable(false);
		pack();
	}
	
	@Override
	public void initActionListeners() {
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				songSelection.playSong();
			}
		});
	}
	
}
