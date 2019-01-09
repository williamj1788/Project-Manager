import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ProjectButtonPanel extends JPanel {
	JButton addButton = new JButton("ADD");
	JButton delButton = new JButton("DELETE");
	JButton editButton = new JButton("EDIT");
	JButton summaryButton = new JButton("SUMMARY");
	GridBagConstraints c = new GridBagConstraints();
	
	static boolean isAddProjectFrameShowing = false;
	static boolean isEditProjectFrameShowing = false;
	
	public ProjectButtonPanel() {
		super(new GridBagLayout());
		
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isAddProjectFrameShowing == false) {
					new AddProjectFrame();
					isAddProjectFrameShowing = true;
				}
			}
		});
		
		delButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!ProjectSelectPanel.projectSelector.isSelectionEmpty()) {
					int index = ProjectSelectPanel.projectSelector.getSelectedIndex();
					ProjectSelectPanel.array.remove(index);
					ProjectDetailPanel.clearPanel();
				
				}
			}
		});
		
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isEditProjectFrameShowing == false && !ProjectSelectPanel.projectSelector.isSelectionEmpty()) {
					new EditProjectFrame();
					isEditProjectFrameShowing = true;
				}
			}
		});
		
		
		ProjectPanel.setHeight(this,20);
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		
		add(addButton,c);
		c.gridx++;
		add(delButton,c);
		c.gridx++;
		add(editButton,c);
		c.gridx++;
		add(summaryButton,c);
	}
}
