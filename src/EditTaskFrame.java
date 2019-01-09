import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditTaskFrame extends JFrame {
JPanel panel = new JPanel(new GridBagLayout());
	
	JLabel taskTitleLabel = new JLabel("Task: ");
	JLabel infoTitleLabel = new JLabel("Info: ");
	
	JTextField taskField = new JTextField(20);
	JTextField infoField = new JTextField(20);
	
	JButton editTaskButton = new JButton("Edit Task");
	
	int selIndex = ProjectDetailPanel.taskTabel.getSelectedRow();
	
	
	public EditTaskFrame() {
		super("Edit Task");
		
		//DONT FORGET THIS IS NOT EXPAND!!
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				ProjectDetailPanel.isEditTaskFrameShowing = false;
				dispose();
				
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
		editTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				String taskText = taskField.getText();
				String infoText = infoField.getText();
				List<Tasks> task = ProjectSelectPanel.getTaskList();
				
				if(taskText.length() > 0) {
					boolean com = (boolean) ProjectDetailPanel.dtm.getValueAt(selIndex, 2);
					task.set(selIndex, new Tasks(taskText,infoText,com));
				}
				else {
					task.remove(selIndex);
				}
				
				ProjectDetailPanel.updatePanel();
				
				ProjectDetailPanel.isEditTaskFrameShowing = false;
				dispose();
				
				
			}
			
		});
		
		taskField.setText((String) ProjectDetailPanel.dtm.getValueAt(selIndex, 0));
		infoField.setText((String) ProjectDetailPanel.dtm.getValueAt(selIndex, 1));
		
		ProjectPanel.setWidth(taskTitleLabel, 35);
		ProjectPanel.setWidth(infoTitleLabel, 35);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5,0,5,0);
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		
		panel.add(taskTitleLabel,c);
		c.gridy++;
		panel.add(infoTitleLabel,c);
		
		c.gridy = 0;
		c.gridx++;
		panel.add(taskField,c);
		c.gridy++;
		panel.add(infoField,c);
		
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 2;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0,0,0,0);
		
		panel.add(editTaskButton,c);
		
		
		add(panel);
		
		
		//setSize(200, 100);
        pack();
		setResizable(false);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //setMinimumSize(new Dimension(750, 600));
        setVisible(true);
	}
	
}

