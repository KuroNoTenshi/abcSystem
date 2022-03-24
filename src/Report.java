import java.util.Scanner;

public class Report {
    
    public Report() { //Report Constructor
        DataProcessing data = new DataProcessing();
        Scanner sc = new Scanner(System.in);
        String[][] items = data.loadItems("items.txt");

        boolean isDone = false;

        while (!isDone) {
            //Report Menu Selection
            System.out.println("Reports\n");
            System.out.println(Constants.REPORT_MENU + "\n");
    
            System.out.println("Enter Option: ");
            int option = sc.nextInt();
    
            switch (option) {
                case 1: //Items Inventory Report
                    System.out.println("Items Inventory Report");
                    //Print Stocks
                    for (int i = 0; i < items.length; i++) {
                        System.out.printf("[%d]\t%-20s%s%n", i+1 ,items[i][0], items[i][2]);
                    }
                    //Press any key to return to menu
                    System.out.println("\n" + Constants.BACK_TO_MENU_2);
                    sc.nextLine();
                    String key = sc.nextLine();
                    break;

                case 2: //Daily Statistics Report
                    System.out.println("Daily Statistics Report");
                    //Load Statistics and Sold Items
                    String[] statistics = data.loadStatistics("statistics.txt");
                    int[][] soldItems = data.loadSoldItems("soldItems.txt");

                    System.out.println("Date: " + statistics[0]);
                    System.out.println("\nItems Sold");

                    //Print List of Sold Items
                    for (int i = 0; i < soldItems.length; i++) {
                        System.out.printf("%-20s%d%n", items[i][0], soldItems[i][1]);
                    }

                    System.out.println("\nTotal Customers: " + statistics[1]);
                    System.out.println("Total Earnings Today: " + statistics[2]);
                    
                    //Press any key to return to the Report Menu
                    System.out.println("\n" + Constants.BACK_TO_MENU_2);
                    sc.nextLine();
                    key = sc.nextLine();
                    break;

                case 3: //Back to Main Menu
                    isDone = true;
                    key = "a";
                    if(key.equals("")){
                        sc.close();
                    }
                    break;

                default: // For Invalid Input
                    System.out.println("Incorrect Input, Please try again");
                    break;

            }
    
            
        }
       
    }
}
