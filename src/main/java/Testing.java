import generators.GroceryFuzzGenerator;
import org.junit.Test;
import pb_grocery.GroceryQueryProvider;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jake on 11/2/16.
 */
public class Testing {
    @Test
    public void Testing() throws IOException {
        GroceryFuzzGenerator record = new GroceryFuzzGenerator();
        record.nextObject();
        FileOutputStream output = new FileOutputStream("resources/codedOutput.txt");


        GroceryQueryProvider.InventoryDetails.Builder query = GroceryQueryProvider.InventoryDetails.newBuilder();

        query.setStoreID(record.getStoreID());
        query.setDate(record.getDate());
        query.setProductName(record.getProductName());
        query.setAmount(record.getAmount());
        query.setRecordID(record.getRecordID());
        query.setUnits(record.getUnits());

        query.build().writeTo(output);


    }
}
