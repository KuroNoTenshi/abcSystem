import java.io.*;
import java.nio.file.*;
import java.util.Scanner;
public class DataProcessing{
    
    public DataProcessing(){ //Data Processing Constructor
        createFile("items.txt");
        createFile("statistics.txt");
        createFile("soldItems.txt");
    }
    
    //Method to Create the files needed for the Program
    private static void createFile(String fileName){
        try {
            File itemsFile = new File(fileName);
            
            if (itemsFile.createNewFile()) { //If we can create a new file, create a new file, otherwise disregard
                
                if(fileName.equals("items.txt")){ 
                    addItems(Constants.DEFAULT_ITEMS, fileName); //add the default items to the file
                }else if (fileName.equals("statistics.txt")) {
                    addStatistics(Constants.DEFAULT_STATISTICS, fileName); //add the default statistics to the file
                }else if (fileName.equals("soldItems.txt")) {
                    addSoldItems(Constants.DEFAULT_SOLD_ITEMS, fileName); //add the default sold items to the file
                }

            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    //Method to Get the Items from the items.txt file
    public String[][] loadItems(String fileName){
        String[][] items = new String[countItems(fileName)][3];;

        try {
            Scanner reader = new Scanner(new File(fileName));// Scanner to read all items in the file
            
            int counter = 0;
            while(reader.hasNextLine()){ //Loop will continue until all lines have been read
                
                //String format in the file is ItemName-ItemPrice=ItemQuantity, so we split the string to get the individual item data
                items[counter] = reader.nextLine().split("-"); 
                counter++;

            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace(); //Prints the Error if there is one without terminating the program
        }
        return items;
    }

    //Method to add the items to the items.txt file
    private static void addItems(String[][] items, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName); //this will be used to write the data to the file

            for(int i = 0; i < items.length; i++){
                for(int j = 0; j < items[i].length; j++){
                    writer.write(j == items[i].length - 1? items[i][j] : items[i][j] + "-");
                }
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    /*This Method is basically the same with the addItems Method. The reason why we duplicate is 
    because static methods(like addItems()) cannot be used in other classes if we create an object of
    this class (i.e. DataProcessing data = new DataProcessing(); --found in every class except Constants class and Main class)*/
    public void saveItems(String[][] items, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);

            for(int i = 0; i < items.length; i++){
                for(int j = 0; j < items[i].length; j++){
                    writer.write(j == items[i].length - 1? items[i][j] : items[i][j] + "-");
                }
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        
    }

    //Method to get the Statistics from the statistics.txt file
    public String[] loadStatistics(String fileName){
        String[] items = new String[4];

        try {
            Scanner reader = new Scanner(new File(fileName));
            
            int counter = 0;
            while(reader.hasNextLine()){
                items[counter] = reader.nextLine();
                counter++;
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }
    
    //Method to add the statistics data to the statistics.txt file
    private static void addStatistics(String[] items, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);

            for(int i = 0; i < items.length; i++){
                    writer.write(items[i] + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This is basically the same as the addStatistics() Method. Same reason as the saveItems() Method
    public void saveStatistics(String[] items, String fileName){
        try {
            FileWriter writer = new FileWriter(fileName);

            for(int i = 0; i < items.length; i++){
                    writer.write(items[i] + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method to get the Sold Items data from the soldItems.txt file
    public int[][] loadSoldItems(String fileName) {
        int[][] soldItems = new int[countItems(fileName)][2];

        try {
            Scanner reader = new Scanner(new File(fileName));
            
            int counter = 0;
            while(reader.hasNextLine()){
                
                String[] items = reader.nextLine().split("-");
                soldItems[counter][0] = Integer.parseInt(items[0]);
                soldItems[counter][1] = Integer.parseInt(items[1]);
                counter++;

            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return soldItems;
    }
   
    //Method to add the sold items to the soldItems.txt file
    private static void addSoldItems(String[][] soldItems, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);

            for(int i = 0; i < soldItems.length; i++){
                for(int j = 0; j < soldItems[i].length; j++){
                    writer.write(j == soldItems[i].length - 1? soldItems[i][j] : soldItems[i][j] + "-");
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This is basically the same as the addSoldItems() Method. Same reason as the saveItems() Method
    public void saveSoldItems(String[][] soldItems, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);

            for(int i = 0; i < soldItems.length; i++){
                for(int j = 0; j < soldItems[i].length; j++){
                    writer.write(j == soldItems[i].length - 1? soldItems[i][j] : soldItems[i][j] + "-");
                }
                writer.write("\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method used to count the lines in a text file
    private static int countItems(String fileName){

        Path filePath = Paths.get(fileName);
        int itemCount = 0;
        
        try {
            itemCount = (int)Files.lines(filePath).count();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemCount;
    }

}