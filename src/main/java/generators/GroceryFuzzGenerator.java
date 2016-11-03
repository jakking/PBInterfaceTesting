package generators;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by jake on 11/2/16.
 */
public class GroceryFuzzGenerator {

    private int StoreID;
    private long date;
    private String ProductName = "Product Name";
    private int amount;
    private int RecordID;
    private String units = "units";
    private Random rnd;
    private String string_date = "2-November-2016";


    public GroceryFuzzGenerator(){
        rnd = new Random();
        this.RecordID = 0;
    }

    public GroceryFuzzGenerator(int storeID, long date, String productName, int amount, int recordID, String units) {
        this.StoreID = storeID;

    }

    public void nextObject(){
        this.StoreID = rnd.nextInt(Integer.SIZE - 1);
        this.date = setDate();
        this.amount = rnd.nextInt(Integer.SIZE - 1);
        this.RecordID++;
    }

    private long setDate() {
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date d = f.parse(string_date);
            long milliseconds = d.getTime();
            date = milliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getDate();
    }

    public int getStoreID() {
        return StoreID;
    }

    public long getDate() {
        return date;
    }

    public String getProductName() {
        return ProductName;
    }

    public int getAmount() {
        return amount;
    }

    public int getRecordID() {
        return RecordID;
    }

    public String getUnits() {
        return units;
    }
}
