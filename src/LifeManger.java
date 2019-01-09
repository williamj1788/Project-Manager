import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class LifeManger extends JFrame {

	public LifeManger() {
		super("Life Manager");
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				ProjectSelectPanel.SaveProjecFileArray();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		createMenuBar();
		createTabbedPane();
        
		setSize(850, 600);
        //pack();
		setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(750, 600));
        setVisible(true);
        
    }
	
	//Create MenuBar and add the MenuItems to it
    public void createMenuBar() {
    	JMenuBar menuBar = new JMenuBar();
    	
    	JMenu fileMenuOb = new JMenu("File");
    	JMenuItem saveItem = new JMenuItem("Save");
    	JMenuItem importItem = new JMenuItem("Open");
    	
    	//Save to A file
    	saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ProjectSelectPanel.SaveProjecFileArray();
			}
    	});

    	JMenu windowsMenuOb = new JMenu("Windows");
    	JMenuItem showItem = new JMenuItem("Show");
    	
    	fileMenuOb.add(saveItem);
    	fileMenuOb.add(importItem);
    	
    	windowsMenuOb.add(showItem);
    	
    	menuBar.add(fileMenuOb);
    	menuBar.add(windowsMenuOb);
    	
    	setJMenuBar(menuBar);
    	
    }
    
    //Create the TabbedPanel and the panels to it
    public void createTabbedPane() {
    	
    	ProjectPanel pPanel = new ProjectPanel();
    	JPanel goalPanel = new JPanel();
    	
    	JTabbedPane tabbedpane = new JTabbedPane();
    	
    	tabbedpane.add("Projects", pPanel);
    	tabbedpane.add("Goals", goalPanel);
    	add(tabbedpane);
    	
    }
    
	
    public static void main(String[] args) {
    	new LifeManger();
        
      
    }

}
