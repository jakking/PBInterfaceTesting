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

    int[] intLastValues;
    long[] longLastValues;


    public GroceryFuzzGenerator(){
        rnd = new Random();
        this.RecordID = 0;
        intLastValues = new int[3];
        longLastValues = new long[3];
    }

    public GroceryFuzzGenerator(int storeID, long date, String productName, int amount, int recordID, String units) {
        this.StoreID = storeID;
        this.date = date;
        this.ProductName = productName;
        this.amount = amount;
        this.RecordID = recordID;
        this.units = units;
    }

    public void nextObject(){
        this.StoreID = rnd.nextInt(Integer.SIZE - 1);
        this.date = rnd.nextLong();
        this.amount = rnd.nextInt(Integer.SIZE - 1);
        this.RecordID++;
    }

    public void run() {

        int deltaInt, incrementInt, copyInt, defaultInt;
        long deltaLong, incrementLong, copyLong, defaultLong;
        String optionalString, noneString;

        //test all ints
        intLastValues[0] = deltaInt = (rnd.nextInt(50000) < 25000) ? rnd.nextInt(50000) : intLastValues[0];
        intLastValues[1] = incrementInt = (rnd.nextInt(50000) < 25000) ? intLastValues[1] : ++intLastValues[1];
        intLastValues[2] = copyInt = (rnd.nextInt(50000) < 25000) ? rnd.nextInt(50000) : intLastValues[2];
        defaultInt = (rnd.nextInt(50000) < 25000) ? rnd.nextInt(50000) : 122;

        //test all longs
        longLastValues[0] = deltaLong = (rnd.nextInt(50000) < 25000) ? rnd.nextInt(50000) : longLastValues[0];
        longLastValues[1] = incrementLong = (rnd.nextInt(50000) < 25000) ? longLastValues[1] : ++longLastValues[1];
        longLastValues[2] = copyLong = (rnd.nextInt(50000) < 25000) ? rnd.nextInt(50000) : longLastValues[2];
        defaultLong = (rnd.nextInt(50000) < 25000) ? rnd.nextInt(50000) : 122;

        optionalString = "This is optional.";
        noneString = "This is none";

        setStoreID(copyInt);
        setDate(deltaLong);
        setProductName(optionalString);
        setAmount(deltaInt);
        setRecordID(incrementInt);
        setUnits(noneString);
    }


    public int getStoreID() { return StoreID; }

    private void setStoreID(int id) {
        this.StoreID = id;
    }

    public long getDate() {
        return date;
    }

    private void setDate(long value) {
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            Date d = f.parse(string_date);
            value = d.getTime();
            this.date = value;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getProductName() {
        return ProductName;
    }

    private void setProductName(String value) {
        this.ProductName = value;
    }

    public int getAmount() {
        return amount;
    }

    private void setAmount(int value) {
        this.amount = value;
    }

    public int getRecordID() {
        return RecordID;
    }

    private void setRecordID(int value) {
        this.RecordID = value;
    }

    public String getUnits() {
        return units;
    }

    private void setUnits(String value) {
        this.units = value;
    }
}
