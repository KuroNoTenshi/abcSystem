import java.time.LocalDate;
import java.util.Scanner;

public class NewTransaction {

    public NewTransaction() { //New Transaction Constructor
        DataProcessing data = new DataProcessing();
        Scanner sc = new Scanner(System.in);

        String[][] items = data.loadItems("items.txt");
        String order = "";

        //Print Items
        System.out.printf("%s\t%-20s%-15s%-20s\n","No.", "Name", "Price", "Quantity");
        int itemNo = 1;
        for (String[] item : items) {
            System.out.print("[" + itemNo + "]\t");
            for (int i = 0; i < item.length; i++) {
                System.out.printf(i == 1? "%-15s\n" : "%-20s\n", item[i]);
            }
            itemNo++;
        }

        System.out.println("\nCustomer Name: ");
        String customerName = sc.nextLine();

        int orderStatus = 1;
        while (orderStatus == 1) {
            System.out.println("Enter item number: ");
            int itemNumber = sc.nextInt();
            System.out.println("Quantity: ");
            int quantity = sc.nextInt();

            order += String.format("%d,%d ", itemNumber, quantity);

            boolean isValid = false;
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
        checkDate(statistics);
        statistics[1] = String.valueOf(Integer.parseInt(statistics[1]) + 1);
        
        double total = computeTotal(getTotals(items, orderedItems));
        double cash = 0;

        boolean isNegative = true;
        while (isNegative) {
            System.out.println("Total: " + total);
            System.out.println("Enter cash: ");
            cash = sc.nextDouble();
            if (cash >= total) {
                isNegative = false;
            }else{
                System.out.println("You do not have enough money, please pay a bigger amount");
                isNegative = true;
            }
    
        }
       
        System.out.println("Order Summary\n\nName of Customer: " + customerName + "\n\nOrders: \n");
       

        for (int i = 0; i < orderedItems.length; i++) {
            String[] itemData = orderedItems[i].split(",");
            int ID = Integer.parseInt(itemData[0]) - 1;
            String itemName = items[ID][0];
            int itemPrice = Integer.parseInt(items[ID][1]);
            int itemQuantity = Integer.parseInt(itemData[1]);
            double itemTotal = itemQuantity * itemPrice;

            soldItems[ID][1] += itemQuantity;

            System.out.printf("%-20s%s x %-15s%.2f\n", itemName , itemData[1], itemPrice, itemTotal);
            
            int oldQuantity = Integer.parseInt(items[ID][2]);
            items[ID][2] = String.valueOf(oldQuantity - itemQuantity);
        }



        
        statistics[2] = String.valueOf(Double.parseDouble(statistics[2]) + total);
        System.out.println("\n\nTotal: " + total);
        System.out.println("Cash: " + cash);
        System.out.println("Change: " + (cash - total));

        data.saveItems(items, "items.txt");
        data.saveSoldItems(convertSoldItems(soldItems), "soldItems.txt");
        data.saveStatistics(statistics, "statistics.txt");

        System.out.println("\n" + Constants.BACK_TO_MENU_2);
        sc.nextLine();
        String key = sc.next();

        if(key.equals("")){
            sc.close();
        }

    }

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

    private static double computeTotal(double[] itemTotals){
        double total = 0;
        for (double itemTotal : itemTotals) {
            total += itemTotal;
        }
        return total;
    }

    private static void checkDate(String[] statistics){
        LocalDate date = LocalDate.now();
        if(!date.toString().equals(statistics[0])){
            statistics = Constants.DEFAULT_STATISTICS;
        }
    }

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
