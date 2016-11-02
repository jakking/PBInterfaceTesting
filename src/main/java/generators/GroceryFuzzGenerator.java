package generators;

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

    public GroceryFuzzGenerator(){
        rnd = new Random();
        this.RecordID = 0;
    }

    public void nextObject(){
        this.StoreID = rnd.nextInt();
        this.date = rnd.nextLong();
        this.amount = rnd.nextInt();
        this.RecordID++;
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
