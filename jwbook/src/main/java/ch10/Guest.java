package ch10;

public class Guest {
	private int id;
	private String writer;
	private String email;
	private String date;
	private String title;
	private String password;
	private String content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		System.out.println(id);
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		System.out.println(writer);
		if(writer=="") {
			writer = null;
		}
		this.writer = writer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		System.out.println(email);
		if(email=="") {
			email = null;
		}
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		System.out.println(date);
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		System.out.println(title);
		if(title=="") {
			title = null;
		}
		this.title = title;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println(password);
		if(password=="") {
			password = null;
		}
		this.password = password;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		System.out.println(content);
		if(content=="") {
			content = null;
		}
		this.content = content;
	}
}
