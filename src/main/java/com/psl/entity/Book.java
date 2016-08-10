package com.psl.entity;

public class Book {
private int id;
private String name;
private boolean isavailable;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public boolean isIsavailable() {
	return isavailable;
}
public void setIsavailable(boolean isavailable) {
	this.isavailable = isavailable;
}
@Override
public String toString() {
	return "Book [id=" + id + ", name=" + name + ", isavailable=" + isavailable
			+ "]";
}

}
