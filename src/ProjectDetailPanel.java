import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ProjectDetailPanel extends JPanel {
		int dHeight = 20;
		
		JLabel startDateTitleLabel = new JLabel("Start Date: ");
		JLabel dueDateTitleLabel = new JLabel("Due Date: ");
		JLabel TotalHoursTitleLabel = new JLabel("Total Hours: ");
		JLabel categoryTypeTitleLabel = new JLabel("Category: ");
		
		static JLabel startDateLabel = new JLabel("Date");
		static JLabel dueDateLabel = new JLabel("Date");
		static JLabel TotalHoursLabel = new JLabel("Hours");
		static JLabel categoryTypeLabel = new JLabel("Category");
		
		JButton addTaskButton = new JButton("Add Task");
		JButton editTaskButton = new JButton("Edit Task");
		JButton delTaskButton = new JButton("Delete Task");
		
		JPanel taskHolder = new JPanel(new GridBagLayout());
		JPanel buttonTaskHolder = new JPanel(new GridBagLayout());
		
		JCheckBox checkBox = new JCheckBox();
		
		static JTable taskTabel = new JTable();
		String header[] = new String[] { "TASK","INFO", "COMPLETION" };
		static DefaultTableModel dtm = new DefaultTableModel(0, 0){
			
			private static final long serialVersionUID = 1L;
			@Override
			//Set the Completion column to be editable
			public boolean isCellEditable(int row, int column) {
				return column == 2;
			}
			//Set the column class type
			@Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
		};
		
		JScrollPane scroll = new JScrollPane(taskTabel);
		GridBagConstraints c = new GridBagConstraints();
		
		static boolean isAddTaskFrameShowing = false;
		static boolean isEditTaskFrameShowing = false;
	
	public ProjectDetailPanel() {
		super(new GridBagLayout());
		
		dtm.setColumnIdentifiers(header);
		taskTabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		taskTabel.setModel(dtm);
		taskTabel.getColumnModel().getColumn(0).setPreferredWidth(250);
		taskTabel.getColumnModel().getColumn(1).setPreferredWidth(250);
		taskTabel.getColumnModel().getColumn(2).setPreferredWidth(20);
		
		
		TableColumn comColumn = taskTabel.getColumnModel().getColumn(1);
		comColumn.setCellEditor(new DefaultCellEditor(checkBox));
			
		setUpButtonTaskHolder();
		setUptaskHolder();
		
		ProjectPanel.setHeight(startDateTitleLabel,dHeight);
		ProjectPanel.setHeight(dueDateTitleLabel,dHeight);
		ProjectPanel.setHeight(TotalHoursTitleLabel,dHeight);
		ProjectPanel.setHeight(startDateLabel,dHeight);
		ProjectPanel.setHeight(dueDateLabel,dHeight);
		ProjectPanel.setHeight(categoryTypeTitleLabel,dHeight);
		ProjectPanel.setHeight(categoryTypeLabel,dHeight);
		
		ProjectPanel.setHeight(this,100);
		
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 0;

		add(startDateTitleLabel,c);
		c.gridy++;
		add(dueDateTitleLabel,c);
		c.gridy++;
		add(categoryTypeTitleLabel,c);
		c.gridy++;
		add(TotalHoursTitleLabel,c);
		
		c.gridy = 0;
		c.gridx++;
		add(startDateLabel,c);
		c.gridy++;
		add(dueDateLabel,c);
		c.gridy++;
		add(categoryTypeLabel,c);
		c.gridy++;
		add(TotalHoursLabel,c);
		
		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 2;
		c.weighty = 1;
		add(taskHolder,c);
		
	}
	public void setUptaskHolder() {

		GridBagConstraints d = new GridBagConstraints();
		
		d.gridx = 0;
		d.gridy = 0;
		d.fill = GridBagConstraints.BOTH;
		d.weightx = 1;
		d.weighty = 0;
		
		taskHolder.add(buttonTaskHolder,d);
		
		d.gridy++;
		d.weighty = 1;
		taskHolder.add(scroll,d);
		
		taskHolder.setBorder(BorderFactory.createTitledBorder("Task"));
	}
	//Creates and adds the Task buttons to the panel
	public void setUpButtonTaskHolder() {
		
		Font f = delTaskButton.getFont().deriveFont(2);
		addTaskButton.setFont(f);
		editTaskButton.setFont(f);
		delTaskButton.setFont(f);
		
		addTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isAddTaskFrameShowing == false && ProjectSelectPanel.projectSelector.isSelectionEmpty() == false) {
					new AddTaskFrame();
					isAddTaskFrameShowing = true;
				}
			}});
		
		editTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isEditTaskFrameShowing == false && taskTabel.getSelectedRow() != -1) {
					new EditTaskFrame();
					isEditTaskFrameShowing = true;
				}
			}});
		
		delTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(taskTabel.getSelectedRow() != -1) {
					Projects sP = ProjectSelectPanel.projectSelector.getSelectedValue();
					sP.taskList.remove(taskTabel.getSelectedRow());
					updatePanel();
				}
			}});
		
		GridBagConstraints d = new GridBagConstraints();
		
		d.gridx = 0;
		d.gridy = 0;
		d.fill = GridBagConstraints.BOTH;
		d.weightx = 1;
		d.weighty = 1;
		
		buttonTaskHolder.add(addTaskButton,d);
		d.gridx++;
		buttonTaskHolder.add(editTaskButton,d);
		d.gridx++;
		buttonTaskHolder.add(delTaskButton,d);
		
	}
	
	@SuppressWarnings("deprecation")
	public static void updatePanel() {
		Projects sP = ProjectSelectPanel.projectSelector.getSelectedValue();
		java.util.Date time = new java.util.Date(ProjectSelectPanel.getSelectedStartTime());
		java.util.Date dueTime = new java.util.Date(sP.dueTime);
		CategoryType com = ProjectSelectPanel.getSelectedCategoryType();
		
		TotalHoursLabel.setText(Double.toString(ProjectSelectPanel.getSelectedProjectHours()));
		startDateLabel.setText(time.toLocaleString());
		dueDateLabel.setText(dueTime.toLocaleString());
		categoryTypeLabel.setText(com.toString());
		
		for(int j = dtm.getRowCount() - 1; j >= 0; j--) {
			dtm.removeRow(j);
		}
		
		for(int i = 0; i < sP.taskList.size(); i++) {
			
			Object[] tempData = new Object[3];
			tempData[0] = sP.taskList.get(i).Tasktext;
			tempData[1] = sP.taskList.get(i).infoText;
			tempData[2] = sP.taskList.get(i).competion;
			
			ProjectDetailPanel.dtm.addRow(tempData);
		}
		
	}
	public static void clearPanel() {
				
		TotalHoursLabel.setText("");
		startDateLabel.setText("");
		dueDateLabel.setText("");
		categoryTypeLabel.setText("");
	
		for(int j = dtm.getRowCount() - 1; j >= 0; j--) {
			dtm.removeRow(j);
		}
	}
}
