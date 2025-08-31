/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.InterfaceList;
import models.Mountain;
import models.Statistics;
import models.Student;
import models.StudentMountain;
import utils.Utils;

/**

 *
 * @author Admin
 */

public class StudentMountainList extends ArrayList<StudentMountain> implements InterfaceList {

    @Override
    public boolean create() {
        String id = Utils.getValidStudentInfor("id");
        String name = Utils.getValidStudentInfor("name");
        String phone = Utils.getValidStudentInfor("phone number");
        String email = Utils.getValidStudentInfor("email");

        Student student = new Student(id, name, phone, email);
        if (this.contains(new StudentMountain(student))) {
            System.out.println("Duplicate data.");
            return false;
        }

        List<Object> mountainList = new ArrayList<>();
        try {
            mountainList = Utils.readListObjectFromFile("mountainList.bin");
        } catch (IOException ex) {
            Logger.getLogger(StudentMountainList.class.getName()).log(Level.SEVERE, null, ex);
        }

        String mountainCode;
        boolean valid = false;
        do {
            mountainCode = Utils.getString("Enter mountain code: ");
            for (Object obj : mountainList) {
                if (((Mountain) obj).getCode().equalsIgnoreCase(mountainCode)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) System.out.println("Invalid mountain code.");
        } while (!valid);

        double fee = Utils.BASE_FEE;
        String prefix = phone.substring(0, 3);
        if (prefix.equals("098") || prefix.equals("097") || prefix.equals("090") || prefix.equals("091")) {
            fee = Utils.BASE_FEE * (100 - Utils.DISCOUNT_FEE) / 100;
        }

        StudentMountain sm = new StudentMountain(student, mountainCode, fee);
        this.add(sm);
        System.out.println("Registration successful.");
        return true;
    }

    @Override
    public boolean update(String id) {
        StudentMountain target = new StudentMountain(new Student(id, "", "", ""));
        int index = this.indexOf(target);
        if (index == -1) {
            System.out.println("This student has not registered yet.");
            return false;
        }

        StudentMountain sm = this.get(index);
        String name = Utils.getUpdatedStudentInfor("name", sm.getStudent().getName());
        String phone = Utils.getUpdatedStudentInfor("phone number", sm.getStudent().getPhone());
        String email = Utils.getUpdatedStudentInfor("email", sm.getStudent().getEmail());

        List<Object> mountainList = new ArrayList<>();
        try {
            mountainList = Utils.readListObjectFromFile("mountainList.bin");
        } catch (IOException ex) {
            Logger.getLogger(StudentMountainList.class.getName()).log(Level.SEVERE, null, ex);
        }

        String mountainCode;
        boolean valid = false;
        do {
            mountainCode = Utils.updateString("Enter mountain code: ", sm.getMountainCode());
            for (Object obj : mountainList) {
                if (((Mountain) obj).getCode().equalsIgnoreCase(mountainCode)) {
                    valid = true;
                    break;
                }
            }
            if (!valid) System.out.println("Invalid mountain code.");
        } while (!valid);

        double fee = Utils.BASE_FEE;
        String prefix = phone.substring(0, 3);
        if (prefix.equals("098") || prefix.equals("097") || prefix.equals("090") || prefix.equals("091")) {
            fee = Utils.BASE_FEE * (100 - Utils.DISCOUNT_FEE) / 100;
        }

        sm.setStudent(new Student(id, name, phone, email));
        sm.setMountainCode(mountainCode);
        sm.setFee(fee);
        System.out.println("Update successful.");
        return true;
    }

    @Override
    public boolean delete(String id) {
        StudentMountain target = new StudentMountain(new Student(id, "", "", ""));
        int index = this.indexOf(target);
        if (index == -1) {
            System.out.println("This student has not registered yet.");
            return false;
        }

        StudentMountain sm = this.get(index);
        System.out.println("Student Details:");
        System.out.println(sm.toString());

        if (Utils.confirmYesNo("Are you sure you want to delete this registration?")) {
            this.remove(index);
            System.out.println("The registration has been successfully deleted.");
            return true;
        }
        return false;
    }

    @Override
    public List<Object> search(String name) {
        List<Object> result = new ArrayList<>();
        String keyword = name.toLowerCase();
        for (StudentMountain sm : this) {
            if (sm.getStudent().getName().toLowerCase().contains(keyword)) {
                result.add(sm);
            }
        }
        return result;
    }

    @Override
    public void displayList(List<Object> list, String type) {
        if (list.isEmpty()) {
            System.out.println(type.equalsIgnoreCase("display")
                ? "No students have registered yet."
                : "No one matches the search criteria.");
            return;
        }

        System.out.println("----------------------------------------------------------------------");
        System.out.println("Student ID | Name           | Phone       | Peak Code | Fee");
        System.out.println("----------------------------------------------------------------------");
        for (Object obj : list) {
            System.out.println(((StudentMountain) obj).toString());
        }
        System.out.println("----------------------------------------------------------------------");
    }

    @Override
    public List<Object> filter(String campusCode) {
        List<Object> result = new ArrayList<>();
        for (StudentMountain sm : this) {
            if (sm.getStudent().getId().startsWith(campusCode)) {
                result.add(sm);
            }
        }
        return result;
    }

    @Override
    public List<Object> statistic() {
        List<Object> stats = new ArrayList<>();
        List<Object> mountainList = new ArrayList<>();
        try {
            mountainList = Utils.readListObjectFromFile("mountainList.bin");
        } catch (IOException ex) {
            Logger.getLogger(StudentMountainList.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Object obj : mountainList) {
            Mountain m = (Mountain) obj;
            int count = 0;
            double totalFee = 0;
            for (StudentMountain sm : this) {
                if (sm.getMountainCode().equalsIgnoreCase(m.getCode())) {
                    count++;
                    totalFee += sm.getFee();
                }
            }
            if (count > 0) {
                stats.add(new Statistics(m.getCode(), count, totalFee));
            }
        }
        return stats;
    }

    public void displayStatistics(List<Object> list) {
        if (list.isEmpty()) {
            System.out.println("No registration data available for statistics.");
            return;
        }

        System.out.println("---------------------------------------------------------------");
        System.out.println("Peak Code   | Number of Participants | Total Cost");
        System.out.println("---------------------------------------------------------------");
        for (Object obj : list) {
            System.out.println(((Statistics) obj).toString());
        }
        System.out.println("---------------------------------------------------------------");
    }

    @Override
    public boolean saveToFile(String path) {
        try {
            Utils.writeListObjectToFile(path,(List<StudentMountain>) this);
            System.out.println("Registration data has been successfully saved to '" + path + "'.");
            return true;
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
            return false;
        }
    }

    public void loadData() {
        try {
            List<Object> data = Utils.readListObjectFromFile("studentMountainList.bin");
            for (Object obj : data) {
                this.add((StudentMountain) obj);
            }
        } catch (IOException ex) {
            Logger.getLogger(StudentMountainList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}