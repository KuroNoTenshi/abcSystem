import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean isDone = false; //checks if the user is done using the program

        while(!isDone){
            System.out.println("\nWelcome to ABC Bakery!");

            System.out.println("\n" + Constants.MAIN_MENU);

            System.out.println("\nEnter Option: ");
            int option = sc.nextInt();

            switch(option){
                case 1: // Start a New Transaction
                    new NewTransaction();
                    break;
                case 2: // View the Stocks Menu
                    new Stocks();
                    break;
                case 3: // View the Reports Menu
                    new Report();
                    break;
                case 4: //Exit the Program
                    isDone = true;
                    sc.close();
                    break;
                default: //Invalid Input
                    System.out.println("Incorrect input, please try again");
                    break;
        }
       }

    }

    
}
