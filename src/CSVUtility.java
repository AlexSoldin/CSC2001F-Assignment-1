import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CSVUtility {

    public interface UserListener {
        public void powerUserRead(PowerUser user);
    }


    public static void readUsers(UserListener userListener) {

        final String fileName = "cleaned_data.csv";
        final String csvComma = ",";
        String line = "";


        try (BufferedReader bReader = new BufferedReader(new FileReader(fileName))) {

            line = bReader.readLine();
            line = bReader.readLine();

            while (line != null) {

                String[] userDetails = line.split(csvComma);
                PowerUser user = extractUser(userDetails);
                if (user != null) {
                    userListener.powerUserRead(user);
                }
                line = bReader.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeCSV(String columns, ArrayList<String> rows) {

        File file = new File("/Users/alexsoldin/Documents/University/Third Year/First Semester/CSC2001F/Assignments/1/src/results.csv");


        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write(columns.toString());
            for (String s : rows) {
                pw.write(s.toString());
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    private static PowerUser extractUser(String[] userDetails) {
        PowerUser user;
        if(userDetails.length >= 3) {


            String dateTime = userDetails[0];
            String power;
            String voltage;
            if(userDetails[1] != null && !userDetails[1].isEmpty()) {
                power = userDetails[1];
            }else{
                return null;
            }
            if(userDetails[3] != null && !userDetails[2].isEmpty()) {
                voltage = userDetails[3];
            }else{
                return null;
            }
            user = new PowerUser(dateTime, power, voltage);


        }else{
            return null;
        }
        return user;
    }
}
