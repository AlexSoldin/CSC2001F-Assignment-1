/*
PowerArrayApp will store an array of type PowerUser (an array of objects)
This class will scan in the data stored in the .csv file and add it to the array
It will search for an element in the array and return whether or not the entry was found as well as the number of operations
needed - this will be used later on for efficiency
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PowerArrayApp {

    private static PowerUser[] users = new PowerUser[500];
    private static int numberOfElements = 0;
    public static int opCount;

    /**
     * Parameterised Constructor
     * @param users
     * @param numberOfElements
     */
    public PowerArrayApp(PowerUser[] users, int numberOfElements) {
        this.users = users;
        this.numberOfElements = numberOfElements;
    }

    public static void main(String[] args) {
        //reading in from the array
        int count = 0;

        try {
            Scanner sc = new Scanner(new File("cleaned_data.csv"));
            sc.nextLine();

            while (sc.hasNextLine()) {
                Scanner scLine = new Scanner(sc.nextLine()).useDelimiter(",");
                String time = scLine.next();
                String power = scLine.next();
                scLine.next();
                String voltage = scLine.next();
                users[count] = new PowerUser(time, power, voltage);
                count++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }

        if (args.length == 0) {
            printAllDateTimes();
        } else {
            printDateTime(args[0]);
        }

        System.out.println(Operations());


    }

    /**
     * This method will search for the entry in the users[] array with the same dateTime as the one entered and return the opCount
     * The opCount is set to zero at the start for the purpose of the array average --> efficiency
     *
     * @param dateTime
     * @return operations count taken to find the user
     */
    public static int printDateTime(String dateTime) {
        opCount = 0;
        String out = "Date/time not found"; //if it isn't found, this will be displayed
        for (int i = 0; i < 500; i++) {
            opCount++;
            if (users[i].getDateTime().equals(dateTime)) {
                out = users[i].toString();
                break;
            }

        }
        System.out.println(out);
        return opCount;
    }

    //loops through the array of objects and invokes the toString method from the PowerUser class
    //operation count is zero as nothing is searched for but only printed out
    public static void printAllDateTimes() {
        for (int i = 0; i < 500; i++) {
            System.out.println(users[i].toString());
        }
    }

    //prints the totol number of operations used to either find the entry or print out all results
    public static String Operations(){
        return "The total number of operations is: "+opCount;
    }

}










