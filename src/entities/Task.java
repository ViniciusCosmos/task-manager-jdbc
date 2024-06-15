package entities;


public class Task{
	private Integer id;
	private String description;
	
	public Task() {	
	}
	
	public Task(String description) {
		this.description = description; 
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
