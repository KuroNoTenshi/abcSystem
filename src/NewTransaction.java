import java.time.LocalDate;
import java.util.Scanner;

public class NewTransaction {

    public NewTransaction() { //New Transaction Constructor
        DataProcessing data = new DataProcessing();
        Scanner sc = new Scanner(System.in);

        String[][] items = data.loadItems("items.txt"); //load data from items.txt file
        String order = "";

        //Print Items
        System.out.printf("%s\t%-20s%-15s%-20s\n","No.", "Name", "Price", "Quantity");
        int itemNo = 1;
        for (String[] item : items) {
            System.out.print("[" + itemNo + "]\t");
            for (int i = 0; i < item.length; i++) {
                System.out.printf(i == 1? "%-15s" : "%-20s", item[i]);
            }
            System.out.println("");
            itemNo++;
        }

        System.out.println("\nCustomer Name: ");
        String customerName = sc.nextLine();

        int orderStatus = 1; //checks whether user wants another order or not
        while (orderStatus == 1) {
            //order System
            System.out.println("Enter item number: ");
            int itemNumber = sc.nextInt();
            System.out.println("Quantity: ");
            int quantity = sc.nextInt();

            order += String.format("%d,%d ", itemNumber, quantity); //This will be splitted to form the arrays

            boolean isValid = false;// checks if the input is valid
            while(!isValid)
            {
                if (orderStatus == 1 || orderStatus == 2){
                    System.out.println("Do you want another order [1] yes [2] no? ");
                    orderStatus = sc.nextInt();
                    isValid = true;
                }else{
                    System.out.println("Incorrect Input, please try again\n");
                    isValid = false;
                }
            }

        }

        String[] orderedItems = order.split("\\s");
        
        int[][] soldItems = data.loadSoldItems("soldItems.txt");
        String[] statistics = data.loadStatistics("statistics.txt");
        checkDate(statistics); //checks if the date stored in the statistics.txt file is the same date as the date today
        statistics[1] = String.valueOf(Integer.parseInt(statistics[1]) + 1); //this statistic stores the total customers today
        
        //Payment system
        double total = computeTotal(getTotals(items, orderedItems));
        double cash = 0;

        //check if the cash is more than the total balance;
        boolean isNegative = true;
        while (isNegative) {
            System.out.println("\nTotal: " + total);
            System.out.println("Enter cash: ");
            cash = sc.nextDouble();
            if (cash >= total) {
                isNegative = false;
            }else{
                System.out.println("You do not have enough money, please pay a bigger amount");
                isNegative = true;
            }
    
        }
        
        //Order Summary
        System.out.println("\nOrder Summary\n\nName of Customer: " + customerName + "\n\nOrders: \n");
       
        for (int i = 0; i < orderedItems.length; i++) {
            String[] itemData = orderedItems[i].split(","); //each String in the orderedItems array will be splitted to get each ordered item's ID and Quantity
            int itemID = Integer.parseInt(itemData[0]) - 1;
            String itemName = items[itemID][0];
            int itemPrice = Integer.parseInt(items[itemID][1]);
            int itemQuantity = Integer.parseInt(itemData[1]);
            double itemTotal = itemQuantity * itemPrice; //compute the item total

            soldItems[itemID][1] += itemQuantity;

            System.out.printf("%-20s%s x %-15s%.2f\n", itemName , itemData[1], itemPrice, itemTotal);
            
            //subtract the items from the stock --Improvements needed (check if stock is enough)
            int oldQuantity = Integer.parseInt(items[itemID][2]);
            items[itemID][2] = String.valueOf(oldQuantity - itemQuantity);
        }

        
        statistics[2] = String.valueOf(Double.parseDouble(statistics[2]) + total);// This statistic gets the total profits earned
        System.out.println("\n\nTotal: " + total);
        System.out.println("Cash: " + cash);
        System.out.println("Change: " + (cash - total));

        //save respective text files
        data.saveItems(items, "items.txt");
        data.saveSoldItems(convertSoldItems(soldItems), "soldItems.txt");
        data.saveStatistics(statistics, "statistics.txt");

        //Go back to the menu
        System.out.println("\n" + Constants.BACK_TO_MENU_2);
        sc.nextLine();
        String key = sc.next();

        if(key.equals("")){
            sc.close();
        }

    }

    //Method to get a list of the totals of the ordered items
    private static double[] getTotals(String[][] items, String[] orderedItems){

        double[] itemTotals = new double[orderedItems.length];
        
        for (int i = 0; i < itemTotals.length; i++) {
            String[] itemData = orderedItems[i].split(",");
            int ID = Integer.parseInt(itemData[0]) - 1;
            double itemTotal = Double.parseDouble(itemData[1]) * Double.parseDouble(items[ID][1]);
            itemTotals[i] = itemTotal;
        }

        return itemTotals;
        }
    
    //Method to compute the total from the ordered items
    private static double computeTotal(double[] itemTotals){
        double total = 0;
        for (double itemTotal : itemTotals) {
            total += itemTotal;
        }
        return total;
    }

    //Method to check the date, if stored date is not the same(i.e. stored date: March 24, today: March 25), reset the statistics.txt file
    private static void checkDate(String[] statistics){
        LocalDate date = LocalDate.now();
        if(!date.toString().equals(statistics[0])){
            statistics = Constants.DEFAULT_STATISTICS;
        }
    }

    //Method to convert the Sold Items from int[][] to String[][] (for writing in the text file)
    private static String[][] convertSoldItems(int[][] soldItems){
        String[][] newSoldItems = new String[soldItems.length][2];
        
        for (int i = 0; i < newSoldItems.length; i++) {
            for (int j = 0; j < newSoldItems[i].length; j++) {
                newSoldItems[i][j] = String.valueOf(soldItems[i][j]);
            }
        }
        
        return newSoldItems;
    }
}
