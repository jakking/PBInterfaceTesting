/*
THIS CLASS HAS BEEN GENERATED DO NOT MODIFY
*/
package pb_grocery;
import com.ociweb.pronghorn.pipe.*;
import com.ociweb.pronghorn.stage.PronghornStage;
import com.ociweb.pronghorn.stage.scheduling.GraphManager;
import java.util.LinkedList;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import com.ociweb.pronghorn.stage.scheduling.ThreadPerStageScheduler;

public final class GroceryQueryProvider extends PronghornStage{
  public final static FieldReferenceOffsetManager FROM = new FieldReferenceOffsetManager(
          new int[]{0xc1400007,0x88200000,0x98800000,0xa0000000,0x80800001,0x80a00002,0xa4000001,0xc1200007},
          (short)0,
          new String[]{"InventoryDetails","StoreID","Date","ProductName","Amount","RecordID","Units",null},
          new long[]{1, 101, 102, 103, 104, 105, 106, 0},
          new String[]{"global",null,null,null,null,null,null,null},
          "groceryExample.xml",
          new long[]{4, 4, 0},
          new int[]{4, 4, 0});
  private final int Unitsloc = FROM.getLoc("InventoryDetails", "Units");
  private final int RecordIDloc = FROM.getLoc("InventoryDetails", "RecordID");
  private final int Amountloc = FROM.getLoc("InventoryDetails", "Amount");
  private final int ProductNameloc = FROM.getLoc("InventoryDetails", "ProductName");
  private final int Dateloc = FROM.getLoc("InventoryDetails", "Date");
  private final int StoreIDloc = FROM.getLoc("InventoryDetails", "StoreID");
  private GraphManager gm;
  private final static MessageSchemaDynamic messageSchema = new MessageSchemaDynamic(FROM);
  private final static Pipe<MessageSchemaDynamic> inPipe = new Pipe<MessageSchemaDynamic>(new PipeConfig<MessageSchemaDynamic>(messageSchema));
  private final static Pipe<MessageSchemaDynamic> outPipe = new Pipe<MessageSchemaDynamic>(new PipeConfig<MessageSchemaDynamic>(messageSchema));
  private final static Pipe<RawDataSchema> transmittedPipe = new Pipe<RawDataSchema>(new PipeConfig<RawDataSchema>(RawDataSchema.instance));
  private final static Pipe<RawDataSchema> recievedPipe = new Pipe<RawDataSchema>(new PipeConfig<RawDataSchema>(RawDataSchema.instance));
  private GroceryExampleEncoderStage enc;
  private GroceryExampleDecoderStage dec;

  private boolean isWriting;
  InputStream in;
  OutputStream out;
  ThreadPerStageScheduler scheduler;
  Boolean isPrimed;
  public void run(){
    if(isWriting){
      while(Pipe.hasContentToRead(transmittedPipe)){
        int msg = Pipe.takeMsgIdx(transmittedPipe);
        try{
          Pipe.writeFieldToOutputStream(transmittedPipe, out);
        } catch (IOException e) {
          e.printStackTrace();
        }
        Pipe.releaseReadLock(transmittedPipe);
        Pipe.confirmLowLevelRead(transmittedPipe, Pipe.sizeOf(transmittedPipe, msg));

      }
    } else{
      try{
        boolean isOpen = true;
          while (in == null){
          }
        while(in.available() > 0 && Pipe.hasRoomForWrite(recievedPipe) && isOpen){
          int size = Pipe.addMsgIdx(recievedPipe, 0);
          isOpen = Pipe.readFieldFromInputStream(recievedPipe, in, in.available());
          Pipe.publishWrites(recievedPipe);
          Pipe.confirmLowLevelWrite(recievedPipe, size);
        }
        if (!isOpen){
          Pipe.publishEOF(recievedPipe);
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  public GroceryQueryProvider(Boolean isWriting, GraphManager gm){
    super(gm, transmittedPipe, NONE);
    this.isWriting = true;
    inPipe.initBuffers();
    enc = new GroceryExampleEncoderStage(gm, inPipe, transmittedPipe);
    scheduler = new ThreadPerStageScheduler(gm);
    scheduler.startup();
    isPrimed = false;
  }
  public GroceryQueryProvider(GraphManager gm, Pipe<RawDataSchema> transmittedPipe){
    super(gm, NONE, transmittedPipe);
    this.isWriting = false;
    MessageSchemaDynamic messageSchema = new MessageSchemaDynamic(FROM);
    outPipe.initBuffers();
    dec = new GroceryExampleDecoderStage(gm, transmittedPipe, outPipe);
    scheduler = new ThreadPerStageScheduler(gm);
    scheduler.startup();
  }
  public static final class InventoryDetails{

      public static InventoryDetails messages;
      public static GroceryQueryProvider query;
      static {
          GraphManager gm= new GraphManager();
          recievedPipe.initBuffers();
          query = new GroceryQueryProvider(gm, recievedPipe);
          messages = new InventoryDetails();
      }

      public static InventoryDetails parseFrom(InputStream in){
            Builder.query.in = in;
          return Builder.messages;
      }
    public String getUnits(){
      StringBuilder str = new StringBuilder();
      PipeReader.readASCII(query.outPipe, query.Unitsloc, str);
      return str.toString();
    }
    public int getRecordID(){
      return PipeReader.readInt(query.outPipe, query.RecordIDloc);
    }
    public int getAmount(){
      return PipeReader.readInt(query.outPipe, query.Amountloc);
    }
    public String getProductName(){
      StringBuilder str = new StringBuilder();
      PipeReader.readASCII(query.outPipe, query.ProductNameloc, str);
      return str.toString();
    }
    public long getDate(){
      return PipeReader.readLong(query.outPipe, query.Dateloc);
    }
    public int getStoreID(){
      return PipeReader.readInt(query.outPipe, query.StoreIDloc);
    }
    public static Builder newBuilder(){
      return new Builder();
    }

    public InventoryDetails writeTo(OutputStream out){
      Builder.query.out = out;
      PipeWriter.publishWrites(inPipe);
      return this;
    }

    public static final class Builder{
      public static InventoryDetails messages;
      public static GroceryQueryProvider query;

      static {
        GraphManager gm= new GraphManager();
        query = new GroceryQueryProvider(true, gm);
        messages = new InventoryDetails();
      }

      private Builder(){

      }

      public void setUnits(String Units) {
        if (!query.isPrimed){
          while (!PipeWriter.tryWriteFragment(inPipe, 0)) {}
          query.isPrimed = true;
        }
        PipeWriter.writeASCII(query.inPipe, query.Unitsloc, Units);
      }
      public void setRecordID(int RecordID) {
        if (!query.isPrimed){
          PipeWriter.tryWriteFragment(inPipe, 0);
          query.isPrimed = true;
        }
        PipeWriter.writeInt(query.inPipe, query.RecordIDloc, RecordID);
      }
      public void setAmount(int Amount) {
        if (!query.isPrimed){
          PipeWriter.tryWriteFragment(inPipe, 0);
          query.isPrimed = true;
        }
        PipeWriter.writeInt(query.inPipe, query.Amountloc, Amount);
      }
      public void setProductName(String ProductName) {
        if (!query.isPrimed){
          PipeWriter.tryWriteFragment(inPipe, 0);
          query.isPrimed = true;
        }
        PipeWriter.writeASCII(query.inPipe, query.ProductNameloc, ProductName);
      }
      public void setDate(long Date) {
        if (!query.isPrimed){
          PipeWriter.tryWriteFragment(inPipe, 0);
          query.isPrimed = true;
        }
        PipeWriter.writeLong(query.inPipe, query.Dateloc, Date);
      }
      public void setStoreID(int StoreID) {
        if (!query.isPrimed){
          PipeWriter.tryWriteFragment(inPipe, 0);
          query.isPrimed = true;
        }
        PipeWriter.writeInt(query.inPipe, query.StoreIDloc, StoreID);
      }
      public InventoryDetails build(){
        query.isPrimed = false;
        return messages;
      }
    }
  }
}
