import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ProjectSelectPanel extends JPanel {
	
	static DefaultListModel<Projects>  array = new DefaultListModel<Projects>();
	static JList<Projects> projectSelector = new JList<Projects>(array);
	
	JScrollPane scroll = new JScrollPane(projectSelector);
	
	public ProjectSelectPanel() {
		super(new BorderLayout());
		
		array = LoadProjectFileArray();
		projectSelector.setModel(array);
		
		projectSelector.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		projectSelector.addListSelectionListener(new ListSelectionListener() {
			@Override
			//When Selecting a new project, This makes sure the the projects Details are saved
			public void valueChanged(ListSelectionEvent arg0) {
				if(!arg0.getValueIsAdjusting()) {
					if(!projectSelector.isSelectionEmpty() && array.getSize() > 1) {
						int selectedIndex = projectSelector.getSelectedIndex();
						int previousIndex = selectedIndex == arg0.getFirstIndex() ? arg0.getLastIndex() : arg0.getFirstIndex();
						try {
							Projects sP = array.getElementAt(previousIndex);
							for(int k = 0; k < sP.taskList.size(); k++) {
								sP.taskList.get(k).competion = (boolean) ProjectDetailPanel.dtm.getValueAt(k, 2);
							}
						}
						catch(ArrayIndexOutOfBoundsException e) {
						}
						ProjectDetailPanel.updatePanel();
					
					}
				}
			}
		});
		
		ProjectPanel.setHeight(this,100);
		
		add(scroll,BorderLayout.CENTER);
	}
	
	public static List<Tasks> getTaskList(){
		return projectSelector.getSelectedValue().taskList;
	}
	
	public static double getSelectedProjectHours() {
			return projectSelector.getSelectedValue().totalHours;
	}
	public static long getSelectedStartTime() {
		return projectSelector.getSelectedValue().startTime;
	}
	public static CategoryType getSelectedCategoryType() {
		return projectSelector.getSelectedValue().category;
	}
	
	// Filter the projects in the selection array based on a catorgory
	public static void FilterByCategory(CategoryType cat) {
		if(cat == CategoryType.NoFilter) {
			projectSelector.setModel(array);
		}
		else {
			DefaultListModel<Projects>  copyArray =  new DefaultListModel<Projects>();
	
			for (int i = 0; i < array.getSize();i++) {
				copyArray.addElement(array.getElementAt(i));
			}
			for (int i = copyArray.getSize() - 1; i > -1; i--) {
				if(copyArray.getElementAt(i).category != cat) {
					copyArray.remove(i);
				}
			}
			projectSelector.setModel(copyArray);
		}
	}
	
	public DefaultListModel<Projects> LoadProjectFileArray() {
		DefaultListModel<Projects> array = new DefaultListModel<Projects>();
		try {
			FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			
			array = (DefaultListModel<Projects>) oi.readObject();
			
			fi.close();
			oi.close();
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
		return array; 
	}
	public static void SaveProjecFileArray() {
		try {
			FileOutputStream fileOut = new FileOutputStream("myObjects.txt");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(array);
			objectOut.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}

	}
}

