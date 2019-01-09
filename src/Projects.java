import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

enum CategoryType {
	NoFilter,
	None,
	GameDev,
	AppDev,
	WebDev,
	MachineLearning,
	
}


public class Projects implements Serializable{
	private static final long serialVersionUID = 1L;

	String name;
	
	long startTime;
	long dueTime;
	
	double totalHours;
	boolean completion;
	CategoryType category; 
	List<Tasks> taskList = new ArrayList<>();
	
	public Projects(String name,double hours, boolean com, CategoryType cat) {
		this.name = name;
		this.totalHours = hours;
		this.completion = com;
		this.category = cat; 
		this.startTime =  System.currentTimeMillis();
	
	}
	public Projects(String name, CategoryType com, long duetime) {
		this.name = name;
		this.totalHours = 0;
		this.completion = false;
		this.category = com;
		this.startTime =  System.currentTimeMillis();
		this.dueTime = duetime;
		
	}
	public Projects(String name,long startTime, long dueTime,double hours, boolean com, CategoryType cat,List<Tasks> taskList) {
		this.name = name;
		this.startTime = startTime;
		this.dueTime = dueTime;
		this.totalHours = hours;
		this.completion = com;
		this.category = cat; 
		this.taskList = taskList;
	
	}
	@Override
	public String toString() {
		return name;
	}
}
