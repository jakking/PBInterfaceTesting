import com.google.protobuf.CodedOutputStream;
//import com.ociweb.pronghorn.pipe.Pipe;
//import com.ociweb.pronghorn.pipe.PipeConfig;
//import com.ociweb.pronghorn.pipe.RawDataSchema;
//import com.ociweb.pronghorn.pipe.util.StreamRegulator;
import generators.GroceryFuzzGenerator;
import org.junit.Test;
import pb_grocery.GroceryQueryProvider;
import PBBuffBefore.Grocery;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by jake on 11/2/16.
 */
public class Testing {
    @Test
    public void TestingWrites() throws IOException {
        GroceryFuzzGenerator record = new GroceryFuzzGenerator();
        FileOutputStream pronghornoutput = new FileOutputStream("src/resources/pronghornoutput.txt");
        FileOutputStream pbBuffOutput = new FileOutputStream("src/resources/pboutput.txt");
        PrintWriter regOutput = new PrintWriter("src/resources/regularOutput.txt", "UTF-8");

        GroceryQueryProvider.InventoryDetails.Builder query = GroceryQueryProvider.InventoryDetails.newBuilder();
        for (int i = 0; i < 100; i++) {
            record.nextObject();
            query.setStoreID(record.getStoreID());
            query.setDate(record.getDate());
            query.setProductName(record.getProductName());
            query.setAmount(record.getAmount());
            query.setRecordID(record.getRecordID());
            query.setUnits(record.getUnits());

            query.build().writeTo(pronghornoutput);

            regOutput.print(record.getStoreID());
            regOutput.print(record.getDate());
            regOutput.print(record.getProductName());
            regOutput.print(record.getAmount());
            regOutput.print(record.getRecordID());
            regOutput.print(record.getUnits());

            if (i==0){
                System.out.println(record.getStoreID());
                System.out.println(record.getDate());
                System.out.println(record.getProductName());
                System.out.println(record.getAmount());
                System.out.println(record.getRecordID());
                System.out.println(record.getUnits());
            }

        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pronghornoutput.close();
        regOutput.close();
    }

    @Test
    public void testingReads() throws IOException {

        FileInputStream input = new FileInputStream("src/resources/pronghornoutput.txt");
        GroceryQueryProvider.InventoryDetails inv = GroceryQueryProvider.InventoryDetails.parseFrom(input);
        for (int i = 0; i < 3; i++) {
            System.out.println(inv.getStoreID());
            System.out.println(inv.getDate());
            System.out.println(inv.getProductName());
            System.out.println(inv.getAmount());
            System.out.println(inv.getRecordID());
            System.out.println(inv.getUnits());
        }



    }

}
