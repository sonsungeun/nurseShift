package com.shift.myshift;

public class NurseVO {
	String type, birthday;
	int idx1,idx2;

public NurseVO() {
	// TODO Auto-generated constructor stub
}

public NurseVO(String type, String birthday, int idx1, int idx2) {
	super();
	this.type = type;
	this.birthday = birthday;
	this.idx1 = idx1;
	this.idx2 = idx2;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getBirthday() {
	return birthday;
}

public void setBirthday(String birthday) {
	this.birthday = birthday;
}

public int getIdx1() {
	return idx1;
}

public void setIdx1(int idx1) {
	this.idx1 = idx1;
}

public int getIdx2() {
	return idx2;
}

public void setIdx2(int idx2) {
	this.idx2 = idx2;
}
}
