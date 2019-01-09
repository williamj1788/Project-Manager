import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale.Category;
import java.util.Properties;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.util.Calendar;
import java.util.Locale;

public class AddProjectFrame extends JFrame {
	
	JPanel panel = new JPanel(new GridBagLayout());
	
	JLabel nameTitleLabel = new JLabel("Name: ");
	JLabel categoyTitleLabel = new JLabel("Category: ");
	JLabel dueDateTitleLabel = new JLabel("Due Date: ");
	
	JTextField nameTextField = new JTextField(10);
	JButton createProjectButton = new JButton("Add Project");
	JComboBox<CategoryType> categoryBox = new JComboBox<CategoryType>(CategoryType.values());
	
	UtilDateModel model = new UtilDateModel();
	
	
	
	public AddProjectFrame() {
		super("Add Project");
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());		
			
		categoryBox.removeItemAt(0);
		//DONT FORGET THIS IS NOT EXPANED
		addWindowListener(new WindowListener() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				ProjectButtonPanel.isAddProjectFrameShowing = false;
				dispose();
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			
		});
		
		createProjectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nameTextField.getText();
				CategoryType com = categoryBox.getItemAt(categoryBox.getSelectedIndex());
				String date = datePicker.getJFormattedTextField().getText();
				long dateTime;
				if(date.length() > 0) {
					dateTime = ProjectPanel.Timeconversion(date);
				}
				else {
					dateTime = System.currentTimeMillis();
				}
				

				if(nameTextField.getText().length() <= 0 ) {
					name = "Project";
				}
				
				ProjectSelectPanel.array.addElement(new Projects(name,com,dateTime));
				ProjectButtonPanel.isAddProjectFrameShowing = false;
				
				dispose();
			}
		});
		
		ProjectPanel.setHeight(createProjectButton, 50);
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		//c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		
		panel.add(nameTitleLabel,c);
		c.gridy++;
		panel.add(categoyTitleLabel,c);
		c.gridy++;
		panel.add(dueDateTitleLabel,c);
		
		c.gridy = 0;
		c.gridx++;
		panel.add(nameTextField,c);
		c.gridy++;
		panel.add(categoryBox,c);
		c.gridy++;
		panel.add(datePicker,c);
		
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 2;
		c.weighty = 0;
		c.fill = GridBagConstraints.BOTH;
		panel.add(createProjectButton,c);
		
		add(panel);
		
		
		//setSize(300, 300);
        pack();
		setResizable(false);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setMinimumSize(new Dimension(300, 300));
        setVisible(true);
	}
}
