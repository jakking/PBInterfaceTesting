package pb_grocery;
import com.ociweb.pronghorn.pipe.stream.LowLevelStateManager;
import com.ociweb.pronghorn.pipe.Pipe;
import com.ociweb.pronghorn.pipe.FieldReferenceOffsetManager;
import com.ociweb.pronghorn.util.Appendables;
import com.ociweb.pronghorn.pipe.MessageSchemaDynamic;
import com.ociweb.pronghorn.pipe.DataOutputBlobWriter;
import com.ociweb.pronghorn.pipe.MessageSchemaDynamic;
import com.ociweb.pronghorn.stage.phast.PhastDecoder;
import com.ociweb.pronghorn.pipe.DataInputBlobReader;
import com.ociweb.pronghorn.stage.PronghornStage;
import java.util.Arrays;
import com.ociweb.pronghorn.pipe.*;
public class GroceryExampleDecoderStage extends PronghornStage {

    public GroceryExampleDecoderStage(com.ociweb.pronghorn.stage.scheduling.GraphManager gm, Pipe<RawDataSchema>  input, Pipe<MessageSchemaDynamic>  output) {
        super(gm,input,output);
        this.output = output;
        Pipe.from(output).validateGUID(FROM_GUID);
        this.input = input;
        intDictionary = FROM.newIntDefaultsDictionary();
        longDictionary = FROM.newLongDefaultsDictionary();
        intDefaults = FROM.newIntDefaultsDictionary();
        longDefaults = FROM.newLongDefaultsDictionary();
        reader = Pipe.inputStream(input);
    }

    private Pipe<MessageSchemaDynamic> output;
    private long[] longDictionary;
    private int[] intDictionary;
    private long[] longDefaults;
    private int[] intDefaults;
    private Pipe<RawDataSchema> input;
    DataInputBlobReader<RawDataSchema> reader;
    public final static FieldReferenceOffsetManager FROM = new FieldReferenceOffsetManager(
            new int[]{0xc1400007,0x88200000,0x98800000,0xa0000000,0x80800001,0x80a00002,0xa4000001,0xc1200007},
            (short)0,
            new String[]{"InventoryDetails","StoreID","Date","ProductName","Amount","RecordID","Units",null},
            new long[]{1, 101, 102, 103, 104, 105, 106, 0},
            new String[]{"global",null,null,null,null,null,null,null},
            "groceryExample.xml",
            new long[]{4, 4, 0},
            new int[]{4, 4, 0});
    private final int[] FROM_GUID = new int[]{236463696, 1042588431, 307749989, 0, (-1421281399), (-1920029437), 1480946118, 1348572483};
    private final long BUILD_TIME = 1479219175665L;
    private static final int DO_NOTHING = -3;

    private int nextMessageIdx() {
        return Pipe.takeMsgIdx(input);
    }


    public void startup(){
    }

    @Override
    public void run() {
        while (Pipe.hasRoomForWrite(output) && Pipe.contentRemaining(input) > 0){
            switch(nextMessageIdx()) {
                case /*processPipe1WriteInventoryDetails*/0:
                    Pipe.addMsgIdx(output,0);
                    processInventoryDetails();
                    Pipe.confirmLowLevelWrite(output, 11/* fragment 0  size 8*/);
                    break;
                case -1:
                    Pipe.publishEOF(output);
                    requestShutdown();
                case DO_NOTHING:
                    return;
                default:
                    throw new UnsupportedOperationException("Unknown message type , rebuid with the new schema.");
            }
            Pipe.publishWrites(output);
        }
    }

    private void processInventoryDetails() {
        long bitMask = 1;
        int position = reader.position();
        long map = DataInputBlobReader.readPackedLong(reader);
        int StoreID = PhastDecoder.decodeCopyInt(intDictionary, reader, map, 0, bitMask, false);
        bitMask = bitMask << 1;
        long Date = PhastDecoder.decodeDeltaLong(longDictionary, reader, map, 0, bitMask, false);
        bitMask = bitMask << 1;
        String ProductName = PhastDecoder.decodeString(reader, false);
        bitMask = bitMask << 1;
        int Amount = PhastDecoder.decodeDeltaInt(intDictionary, reader, map, 1, bitMask, false);
        bitMask = bitMask << 1;
        int RecordID = PhastDecoder.decodeIncrementInt(intDictionary, map, 2, bitMask, false);
        bitMask = bitMask << 1;
        String Units = PhastDecoder.decodeString(reader, true);
        bitMask = bitMask << 2;
        Pipe.releasePendingAsReadLock(input, reader.position() - position);
        Pipe.readNextWithoutReleasingReadLock(input);
        processPipe1WriteInventoryDetails(StoreID,Date,ProductName,Amount,RecordID,Units);
    }

    private void processPipe1WriteInventoryDetails( int pStoreID,long pDate,CharSequence pProductName,int pAmount,int pRecordID,CharSequence pUnits) {
        Pipe.addIntValue(pStoreID,output);
        Pipe.addLongValue(pDate,output);
        Pipe.addASCII(pProductName,output);
        Pipe.addIntValue(pAmount,output);
        Pipe.addIntValue(pRecordID,output);
        Pipe.addASCII(pUnits,output);
    }

};
