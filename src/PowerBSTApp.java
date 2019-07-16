/*
This class is used to facilitate searching through the given .CSV file using a binary search tree
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PowerBSTApp {

    public static BinarySearchTree<PowerUser> bt = new BinarySearchTree<PowerUser>();
    public static PowerUser[] users;
    public static int numberOfElements = 0;

    /**
     * Parameterised Constructor
     * @param users
     * @param numberOfElements
     */
    public PowerBSTApp(PowerUser[] users, int numberOfElements){
            insertValues(users, numberOfElements);
    }

    //This method reads from the .CSV file and populates the binary tree
    public static void insertValues(PowerUser[] users, int numberOfElements) {
        try {
            Scanner sc = new Scanner(new File("cleaned_data.csv"));
            sc.nextLine();

            while (sc.hasNextLine()) {
                Scanner scLine = new Scanner(sc.nextLine()).useDelimiter(",");
                String time = scLine.next();
                String power = scLine.next();
                scLine.next();
                String voltage = scLine.next();
                bt.insert(new PowerUser(time, power, voltage));
                numberOfElements++;
            }
            sc.close();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
    }

    //the main method handles input and output from the terminal
    public static void main(String[] args) {
        insertValues(users, 500);

        if (args.length == 0) {
            printAllDateTimes();
        } else {
            printDateTime(args[0]);
        }

        System.out.println(Operations());
    }

    //prints out all contents in the tree
    public static void printAllDateTimes() {
        bt.inOrder();
    }

    /**
     * Searches for node in the tree with the same dateTime as the one sent into the method
     * bt.opCount is set to zero for enabling the averages in PowerUserApp to be accurate
     *
     * @param dateTime
     * @return operation count taken to find the dam
     */
    public static int printDateTime(String dateTime) {
        bt.opCount = 0;
        PowerUser user = new PowerUser();
        user.setDateTime(dateTime);
        BinaryTreeNode node = bt.find(user);

        String out = "Date/time not found";

        if(node==null){
            System.out.println(out);
        }
        else{
            PowerUser foundUser = (PowerUser) node.getData();
            out = foundUser.toString();

        }
        System.out.println(out);
        return bt.opCount;
    }

    //prints the totol number of operations used to either find the entry or print out all results
    public static String Operations(){
        return "The total number of operations is: "+bt.opCount;
    }

}
