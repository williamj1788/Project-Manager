import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ProjectFilterPanel extends JPanel {
	
	JComboBox<CategoryType> filterBox = new JComboBox<CategoryType>(CategoryType.values());
	JButton createWindowButton = new JButton("Create Window");
	
	public ProjectFilterPanel() {
		super(new GridBagLayout());
		
		filterBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CategoryType cat = (CategoryType) filterBox.getSelectedItem();
				ProjectSelectPanel.FilterByCategory(cat);
				
			}
		});
		
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		
		ProjectPanel.setHeight(this,20);
		ProjectPanel.setWidth(createWindowButton,150);
		
		add(filterBox,c);
		c.gridx++;
		c.weightx = 0;
		add(createWindowButton,c);
		
	}
	
}
