package com.readmultiplefiles.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
    @Id
    private Long studentid;
    private String studentname;
    private double physics;
    private double chemistry;
    private double maths;
	public Long getStudentid() {
		return studentid;
	}
	public void setStudentid(Long studentid) {
		this.studentid = studentid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public double getPhysics() {
		return physics;
	}
	public void setPhysics(double physics) {
		this.physics = physics;
	}
	public double getChemistry() {
		return chemistry;
	}
	public void setChemistry(double chemistry) {
		this.chemistry = chemistry;
	}
	public double getMaths() {
		return maths;
	}
	public void setMaths(double maths) {
		this.maths = maths;
	}
	public Student(Long studentid, String studentname, double physics, double chemistry, double maths) {
		super();
		this.studentid = studentid;
		this.studentname = studentname;
		this.physics = physics;
		this.chemistry = chemistry;
		this.maths = maths;
	}
	@Override
	public String toString() {
		return "Student [studentid=" + studentid + ", studentname=" + studentname + ", physics=" + physics
				+ ", chemistry=" + chemistry + ", maths=" + maths + "]";
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
