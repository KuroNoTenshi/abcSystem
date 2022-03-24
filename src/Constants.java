import java.time.LocalDate;

public class Constants {
    public static final LocalDate DATE = LocalDate.now(); //Gets the date today

    //Default Data for Items, Statistics, and Sold Items files;
    public static final String[][] DEFAULT_ITEMS = {
        {"Cheese Bread", "7", "100"},
        {"Ensaymada", "6", "100"},
        {"Ham and Cheese ", "6", "100"},
        {"Hopia", "5", "100"},
        {"Monay", "5", "100"},
        {"Pan De Coco", "5", "100"},
        {"Pandesal", "3", "100"},
        {"Spanish Bread", "5", "100"},
        {"Putok", "5", "100"},
        {"Tasty", "7", "100"},
    };

    public static final String[] DEFAULT_STATISTICS = {
        DATE.toString(), "0" , "0"
    };

    public static final String[][] DEFAULT_SOLD_ITEMS = {
        {"0", "0"},
        {"1", "0"},
        {"2", "0"},
        {"3", "0"},
        {"4", "0"},
        {"5", "0"},
        {"6", "0"},
        {"7", "0"},
        {"8", "0"},
        {"9", "0"},
    };

    //Different Menus
    public static final String MAIN_MENU = "Menu\n[1] New Transaction\n[2] Stocks\n[3] Report\n[4] Exit";

    public static final String STOCK_MENU = "Actions\n [1] Add Stock\n[2]Remove Stock\n[3]Back to Main Menu";

    public static final String REPORT_MENU = "Actions\n [1] Items Inventory Report\n" + 
                                                    "[2]Daily Statistics\n[3]Back to Main Menu";
    //Back to Menu Variations
    public static final String BACK_TO_MENU_1 = "Back to Menu?\n[Y] Yes\n[N] No";
    public static final String BACK_TO_MENU_2 = "Press any Key to Go Back to Menu.";


}