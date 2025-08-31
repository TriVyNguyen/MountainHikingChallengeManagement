/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class StudentMountain implements Serializable {

    private static final long serialVersionUID = 2L;

    private Student student;
    private String mountainCode;
    private double fee;

    public StudentMountain() {
    }

    public StudentMountain(Student student) {
        this.student = student;
    }

    public StudentMountain(Student student, String mountainCode, double fee) {
        this.student = student;
        this.mountainCode = mountainCode;
        this.fee = fee;
    }

    // Equality check based on student ID
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StudentMountain) {
            StudentMountain sm = (StudentMountain) obj;
            return this.student.getId().equals(sm.getStudent().getId());
        }
        return false;
    }

    // Getters & setters 
    public String getMountainCode() {
        return mountainCode;
    }

    public void setMountainCode(String mountainCode) {
        String tmp = mountainCode;
        if (tmp.length() < 2)
            tmp = "0" + tmp;
        if (!tmp.startsWith("MT"))
            tmp = "MT" + tmp;
        this.mountainCode = tmp;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return String.format("%-13s|%-19s|%-12s|%-11s|%-12.1f",
                this.student.getId(),
                this.student.getName(),
                this.student.getPhone(),
                this.mountainCode,
                this.fee);
    }

    public void displayDelete() {
        for (int i = 0; i < 40; i++) System.out.print("-");
        System.out.println();
        System.out.println("Student ID: " + this.getStudent().getId());
        System.out.println("Name      : " + this.getStudent().getName());
        System.out.println("Phone     : " + this.getStudent().getPhone());
        System.out.println("Mountain  : " + this.mountainCode);
        System.out.println("Fee       : " + this.fee);
        for (int i = 0; i < 40; i++) System.out.print("-");
        System.out.println();
    }
}
