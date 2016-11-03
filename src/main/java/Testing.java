import com.google.protobuf.CodedOutputStream;
//import com.ociweb.pronghorn.pipe.Pipe;
//import com.ociweb.pronghorn.pipe.PipeConfig;
//import com.ociweb.pronghorn.pipe.RawDataSchema;
//import com.ociweb.pronghorn.pipe.util.StreamRegulator;
import generators.GroceryFuzzGenerator;
import org.junit.Test;
import pb_grocery.GroceryQueryProvider;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by jake on 11/2/16.
 */
public class Testing {
    @Test
    public void TestingWrites() throws IOException {
        GroceryFuzzGenerator record = new GroceryFuzzGenerator();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1000];
        CodedOutputStream output = CodedOutputStream.newInstance(stream, buffer.length);

        record.nextObject();



        GroceryQueryProvider.InventoryDetails.Builder query = GroceryQueryProvider.InventoryDetails.newBuilder();

        query.setStoreID(record.getStoreID());
        query.setDate(record.getDate());
        query.setProductName(record.getProductName());
        query.setAmount(record.getAmount());
        query.setRecordID(record.getRecordID());
        query.setUnits(record.getUnits());

        query.build().writeTo(output);

    }

    @Test
    public void testingReads() throws IOException {
        String fout = "src/resources/records10.txt";
        GroceryFuzzGenerator gf = new GroceryFuzzGenerator();
        try{
            FileWriter fw = new FileWriter(fout);
            BufferedWriter bw = new BufferedWriter(fw);

            for(int i = 0; i < 10; i++){
                gf.nextObject();
                bw.write("RecordID: " + gf.getRecordID() + "\n" +
                         "StoreID: " + gf.getStoreID() + "\n" +
                         "Date: " + gf.getDate() + "\n" +
                         "Amount: " + gf.getAmount() + "\n\n");

            }

            bw.close();

        }catch(IOException e) {
            e.printStackTrace();
        }

//        FileInputStream output = new FileInputStream("src/resources/codedOutput.txt");
//        GroceryQueryProvider.InventoryDetails inv = GroceryQueryProvider.InventoryDetails.parseFrom(output);
//        System.out.println(inv.getStoreID());
//        System.out.println(inv.getDate());
//        System.out.println(inv.getProductName());
//        System.out.println(inv.getAmount());
//        System.out.println(inv.getRecordID());
//        System.out.println(inv.getUnits());



    }
}
