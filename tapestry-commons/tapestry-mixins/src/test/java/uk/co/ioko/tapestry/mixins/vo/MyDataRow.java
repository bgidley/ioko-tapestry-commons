package uk.co.ioko.tapestry.mixins.vo;

/**
 * Created by IntelliJ IDEA. User: ben Date: Jul 13, 2009 Time: 12:54:56 PM
 */
public class MyDataRow {
	private String name;
	private String description;
	private Boolean nice;

	public MyDataRow(String name, String description, Boolean nice) {
		this.name = name;
		this.description = description;
		this.nice = nice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean isNice() {
		return nice;
	}

	public void setNice(Boolean nice) {
		this.nice = nice;
	}
}
