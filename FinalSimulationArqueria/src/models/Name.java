package models;
/**
 * 
 * @author Maria Latorre
 *
 */
public class Name {

	private String name;
	private boolean isSelected;
	
	public Name(String name) {
		super();
		this.name = name;
		this.isSelected = false;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public boolean isSelected() {
		return isSelected;
	}
	
	public String getName() {
		return name;
	}
}
