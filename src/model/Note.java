package model;

public class Note {
	private int id;
    private String name;
    private String type;
    private String province;
	private String city;
	private String linkman;
	private String remark;
	private String person;   
	private String date;
	
	public void setId(int id) {
			this.id = id;
	   }
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvince() {
		return province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark() {
		return remark;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getPerson() {
		return person;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate() {
			return date;
		}
}

