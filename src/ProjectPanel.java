import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProjectPanel extends JPanel {
	
	ProjectFilterPanel filterPanel = new ProjectFilterPanel();
	ProjectSelectPanel selectPanel = new ProjectSelectPanel();
	ProjectDetailPanel detailPanel = new ProjectDetailPanel();
	ProjectButtonPanel buttonPanel = new ProjectButtonPanel();
	
	GridBagConstraints c = new GridBagConstraints();
	DefaultListModel<Projects>  array = new DefaultListModel<Projects>();
	
	String FilePath = "C:\\Users\\nikos7\\Desktop\\obj";
	
	// Add the four Panels
	public ProjectPanel() {
		super(new GridBagLayout());
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0;
		
		add(filterPanel, c);
		
		c.gridy++;
		add(selectPanel, c);
		
		
		c.gridy++;
		add(buttonPanel, c);
		
		c.gridy++;
		c.weighty = 1;
		add(detailPanel, c);
	}
	// Set a components prefered hieght
	public static void setHeight(Component c,int num) {
		Dimension size;
		size = c.getPreferredSize();
		size.height = num;
		c.setPreferredSize(size);
	}
	// Set a components prefered width
	public static void setWidth(Component c,int num) {
		Dimension size;
		size = c.getPreferredSize();
		size.width = num;
		c.setPreferredSize(size);
	}
	// Convert the Date into UnixTime
	public static long Timeconversion (String time){
	    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH); 

	    long unixTime = 0;
	    try {
	        unixTime = dateFormat.parse(time).getTime();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    return unixTime;
	   
	}
}
