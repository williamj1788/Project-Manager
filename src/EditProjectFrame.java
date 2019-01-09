import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class EditProjectFrame extends JFrame {
JPanel panel = new JPanel(new GridBagLayout());
	
	JLabel nameTitleLabel = new JLabel("Name: ");
	JLabel categoyTitleLabel = new JLabel("Category: ");
	JLabel dueDateTitleLabel = new JLabel("Due Date: ");
	
	JTextField nameTextField = new JTextField(10);
	JButton EditProjectButton = new JButton("Edit Project");
	JComboBox<CategoryType> categoryBox = new JComboBox<CategoryType>(CategoryType.values());
	
	Projects sP = ProjectSelectPanel.projectSelector.getSelectedValue();
	
	UtilDateModel model = new UtilDateModel();
	
	public EditProjectFrame() {
		super("Edit Project");
		
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		//DONT FORGET THIS IS NOT EXPANED
		addWindowListener(new WindowListener() {

			@Override
			public void windowClosed(WindowEvent arg0) {
				ProjectButtonPanel.isEditProjectFrameShowing = false;
				ProjectDetailPanel.updatePanel();
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
		
		EditProjectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = nameTextField.getText();
				CategoryType com = categoryBox.getItemAt(categoryBox.getSelectedIndex());
				String date = datePicker.getJFormattedTextField().getText();
				long dateTime = ProjectPanel.Timeconversion(date);
				
				Projects sP =  ProjectSelectPanel.projectSelector.getSelectedValue();
				Projects copyOfSP = new Projects(sP.name,sP.startTime,sP.dueTime,sP.totalHours,sP.completion,sP.category,sP.taskList);
				
				int selIndex;
				
				ProjectSelectPanel.array.indexOf(sP);
				
				if(ProjectSelectPanel.array.indexOf(sP) != -1) {
					selIndex = ProjectSelectPanel.array.indexOf(sP);
				}
				else {
					selIndex = 0;
				}
				
				if(nameTextField.getText().length() <= 0 ) {
					name = "Project";
				}
				copyOfSP.name = name;
				copyOfSP.dueTime = dateTime;
				copyOfSP.category = com;
				
				ProjectSelectPanel.array.set(selIndex, copyOfSP);
				ProjectButtonPanel.isEditProjectFrameShowing = false;
				dispose();
			}
		});
		
		ProjectPanel.setHeight(EditProjectButton, 50);
		
		
		nameTextField.setText(sP.name);
		categoryBox.removeItemAt(0);
		categoryBox.setSelectedItem(sP.category);
		java.util.Date dueTime = new java.util.Date(sP.dueTime);
		datePicker.getJFormattedTextField().setText(dueTime.toLocaleString());
		
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
		panel.add(EditProjectButton,c);
		
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
