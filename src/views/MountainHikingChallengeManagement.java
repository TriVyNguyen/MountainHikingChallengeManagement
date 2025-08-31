/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

/**
 *
 * @author Admin
 */
import java.util.List;
import controllers.Menu;
import controllers.StudentMountainList;
import models.InterfaceList;
import models.InterfaceMenu;
import utils.Utils;
 
public class MountainHikingChallengeManagement {

    public static void main(String args[]) {
        InterfaceMenu menu = new Menu();
        menu.addItem("1. Register a new participant");
        menu.addItem("2. Modify existing registration");
        menu.addItem("3. Show all registered participants");
        menu.addItem("4. Remove a registration");
        menu.addItem("5. Look up participant by name");
        menu.addItem("6. View registrations by campus");
        menu.addItem("7. Count registrations by location");
        menu.addItem("8. Export registration data to file");
        menu.addItem("9. Exit program");
        int choice;
        boolean cont = false;
        String id;
        InterfaceList list = new StudentMountainList();
        ((StudentMountainList)list).loadData();
        do {
            menu.showMenu();
            choice = menu.getChoice();            
            switch (choice) {
                case 1: //New Regis
                    Utils.displayStatus(list.create(), "Registration added successfully.", "Failed to add registration.");
                    break;
                case 2: //Update stuInfor
                    id = Utils.getString("Enter student ID to modify: ");
                    Utils.displayStatus(list.update(id.toUpperCase()), "Registration updated.", "No registration found for this student.");
                    break;
                case 3: //Display all
                    ((StudentMountainList)list).displayList((List<Object>) list, "list");
                    break;
                case 4: //Delete
                    id = Utils.getString("Enter student ID to remove: ");
                    Utils.displayStatus(list.delete(id.toUpperCase()), "Registration deleted.", "No registrations available to delete.");
                    break;
                case 5: //Search
                    String name = Utils.getString("Enter name to search for: ");
                    List<Object> searchedList = list.search(name);
                    ((StudentMountainList)list).displayList(searchedList, "search results");
                    break;
                case 6: //Filter
                    String campus = Utils.getString("Enter campus code [SE/DE/HE/CE/QE]: ");
                    List<Object> filterList = list.filter(campus.toUpperCase());
                    ((StudentMountainList)list).displayList(filterList, "filtered results");
                    break;
                case 7: //Show statistics
                    List<Object> statisticsList = list.statistic();
                    ((StudentMountainList)list).displayStatistics(statisticsList);
                    break;
                case 8: //Save
                    Utils.displayStatus(list.saveToFile("studentMountainList.bin"), "Data saved successfully.", "Unable to save data.");
                    break;
                case 9: //Exit
                    cont = menu.confirmYesNo("Are you sure you want to exit? (Y/N)");
                    break;
            }
        } while (!cont);
    }
}

