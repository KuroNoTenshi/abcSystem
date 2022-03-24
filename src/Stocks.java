import java.util.Scanner;

public class Stocks {

    public Stocks() { //Stocks Constructor
        DataProcessing data = new DataProcessing();
        Scanner sc = new Scanner(System.in);
        
        boolean isDone = false; // Checks if user is done viewing the Stocks Menu
        while (!isDone) {
            String[][] items = data.loadItems("items.txt"); //Loads the items of the items.txt file

            System.out.println("Stocks");
            
            //Print Stocks
            for (int i = 0; i < items.length; i++) {
                System.out.printf("[%d]\t%-20s%s%n", i+1 ,items[i][0], items[i][2]);
            }
            
            //Stocks Menu
            System.out.println(Constants.STOCK_MENU + "\n");
            
            System.out.println("Enter Option: ");
            int action = sc.nextInt();
    
            int itemNo = 0;
            String itemStock = "";

            boolean isValid = false; //checks if entered input in the Back_to_Menu Menu is valid
            String choice = "";

            switch (action) {
                case 1: //Add Stocks
                    System.out.print("Enter item: ");
                    itemNo = sc.nextInt();
                    System.out.print("Enter quantity of stocks to add: ");
                    int addQuantity = sc.nextInt();

                    itemStock = items[itemNo-1][2]; //The item Quantity(Stock) of the Selected Item
                    itemStock = String.valueOf(Integer.parseInt(itemStock) + addQuantity); //adds the entered quantity to the Item Stock

                    data.saveItems(items, "items.txt"); //Saves the items to the items.txt file
    
                    System.out.println("\nStocks added");

                    //Back_to_Menu Menu
                    System.out.println("Back to menu?\n[Y] Yes\n[N] No");
                    sc.nextLine();
                    choice = sc.nextLine();
            
                    while (!isValid) {
                        if(choice.equalsIgnoreCase("Y")){ // If yes(Y), Go Back to the Main Menu
                            isDone = true;
                            isValid = true;
                        }else if(choice.equalsIgnoreCase("N")){ // If no(N), Go Back to the Stocks Menu
                            isDone = false;
                            isValid = true;
                        }else{ //Invalid Input
                            System.out.println("Invalid Input, Please try again");
                        }
                    }

                    break;

                case 2: //Remove Stocks
                    System.out.print("Enter item: ");
                    itemNo = sc.nextInt();
                    System.out.print("Enter quantity of stocks to remove: ");
                    int removeQuantity = sc.nextInt();

                    itemStock = items[itemNo-1][2]; //The item Quantity(Stock) of the Selected Item
                    itemStock = String.valueOf(Integer.parseInt(itemStock) - removeQuantity); //subtracts the entered quantity to the Item Stock
                    
                    data.saveItems(items, "items.txt"); //Saves the items to the items.txt file
    
                    System.out.println("\nStocks removed\n");

                    //Back_to_Menu Menu
                    System.out.println("Back to Menu?\n[Y] Yes\n[N] No");
                    sc.nextLine();
                    choice = sc.nextLine();
            
                    while (!isValid) {
                        if(choice.equalsIgnoreCase("Y")){ // If yes(Y), Go Back to the Main Menu
                            isDone = true;
                            isValid = true;
                        }else if(choice.equalsIgnoreCase("N")){ // If no(N), Go Back to the Stocks Menu
                            isDone = false;
                            isValid = true;
                        }else{ //Invalid Input
                            System.out.println("Invalid Input, Please try again");
                        }
                    }

                    break;

                case 3: //Back to Main Menu
                        String key = "a";
                        if(key.equals("")){
                            sc.close();
                        }
                        isDone = true;
                        break;
                default:
                    System.out.println("Incorrect Input, Please try again");
                    break;
                }
            }
        }
       
    
}
