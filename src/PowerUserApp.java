import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PowerUserApp {

    private PowerUser[] users;
    private int numberOfElements;

    public static void main(String[] args) {
        new PowerUserApp();
    }

    public PowerUserApp(){
        users = new PowerUser[500];
        numberOfElements = 0;

        try {
            Scanner sc = new Scanner(new File("cleaned_data.csv"));
            sc.nextLine();

            while (sc.hasNextLine()) {
                Scanner scLine = new Scanner(sc.nextLine()).useDelimiter(",");
                String time = scLine.next();
                String power = scLine.next();
                scLine.next();
                String voltage = scLine.next();
                users[numberOfElements] = new PowerUser(time, power, voltage);
                numberOfElements++;
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        compare();

    }

    /**
     * Test method to check that the compare method is working on a subset of data containing 4 elements
     */
    public void printUserTest(){
        PowerArrayApp powerArrayApp = new PowerArrayApp(users, numberOfElements);
        PowerBSTApp powerBSTApp = new PowerBSTApp(users, numberOfElements);

        String[] names = {"16/12/2006/19:51:00", "16/12/2006/22:30:00", "16/12/2006/21:15:00", "16/12/2006/17:43:00"};


        System.out.println("\nArray Results\n");

        for(int i = 0; i < 4; i++){
            System.out.println("Test " + (i+1) + " Searching for: " + names[i]);
            powerArrayApp.printDateTime(names[i]);
        }

        System.out.println("\nPrinting All Dams\n");

        System.out.println("\nBST Results\n");
        for(int i = 0; i < 4; i++){
            powerBSTApp = new PowerBSTApp(users, numberOfElements);
            System.out.println("Test " + (i+1) + " Searching for: " + names[i]);
            powerBSTApp.printDateTime(names[i]);
        }


        System.out.println("\nPrinting All Dams\n");
    }

    /**
     * This method creates to array lists an stores a counter containing object of the operation counter class
     * All elements are searched for iterating using the array and searching using the binary tree
     * These values are stored in the fileStrings array list in a format for a .CSV file
     * After all searching has been done, the fileStrings array is written to a .CSV file
     */
    public void compare(){
        ArrayList<OperationCount> counter = new ArrayList<>();
        ArrayList<String> fileStrings = new ArrayList<>();

        for (int i = 1; i <= numberOfElements; i+=20) {
            counter.add(new OperationCount(i));
            PowerUser[] sub = Arrays.copyOf(users,i);

            double btOpCountTot = 0;
            double arrOpCountTot = 0;

            for (PowerUser user: sub) {
                PowerArrayApp powerArrayApp = new PowerArrayApp(sub,i);
                PowerBSTApp powerBSTApp = new PowerBSTApp(sub, i);

                int arrOpCount = powerArrayApp.printDateTime(user.getDateTime());
                int btOpCount = powerBSTApp.printDateTime(user.getDateTime());

                btOpCountTot+= btOpCount;
                arrOpCountTot+= arrOpCount;

                updateStats(counter.get(counter.size()-1), arrOpCount, btOpCount);

            }

            double arrAv = Math.round((arrOpCountTot/i)*100.0)/100.0;
            double btAv = Math.round((btOpCountTot/i)*100.0)/100.0;
            counter.get(counter.size()-1).setBtAv(btAv);
            counter.get(counter.size()-1).setArrAv(arrAv);

            fileStrings.add(counter.get(counter.size()-1).toFile());
            counter.clear();
        }
        //printSummary(counter);
        CSVUtility.writeCSV("Num Users,BST Best Case,BST Worst Case,Array Best Case,Array Worst Case,BST Average,Array Average\n", fileStrings);
    }


    /**
     * This method keeps a counter of of all the best/worst case values for both the array and the BST by utilising the
     *operation count class
     *
     * @param operationCount
     * @param arrOpCount
     * @param btOpCount
     */
    private void updateStats(OperationCount operationCount, int arrOpCount, int btOpCount){
        if(operationCount.getArrBestCase() > arrOpCount) {
            //Fewer operations update best case
            operationCount.setArrBestCase(arrOpCount);
        }
        if(operationCount.getArrWorstCase() < arrOpCount){
            operationCount.setArrWorstCase(arrOpCount);
        }

        if(operationCount.getBtBestCase() > btOpCount){
            operationCount.setBtBestCase(btOpCount);
        }
        if(operationCount.getBtWorstCase() < btOpCount){
            operationCount.setBtWorstCase(btOpCount);
        }


    }

    public void printSummary(ArrayList<OperationCount> operationCounts){
        for (OperationCount counter: operationCounts){
            System.out.println(counter);
        }
    }


}
