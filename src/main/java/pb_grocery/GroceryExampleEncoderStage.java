package pb_grocery;
import com.ociweb.pronghorn.pipe.stream.LowLevelStateManager;
import com.ociweb.pronghorn.pipe.Pipe;
import com.ociweb.pronghorn.pipe.FieldReferenceOffsetManager;
import com.ociweb.pronghorn.util.Appendables;
import com.ociweb.pronghorn.pipe.MessageSchemaDynamic;
import com.ociweb.pronghorn.pipe.DataInputBlobReader;
import com.ociweb.pronghorn.pipe.MessageSchemaDynamic;
import com.ociweb.pronghorn.stage.phast.PhastEncoder;
import com.ociweb.pronghorn.pipe.DataOutputBlobWriter;
import com.ociweb.pronghorn.pipe.*;
import com.ociweb.pronghorn.stage.PronghornStage;

public class GroceryExampleEncoderStage extends PronghornStage {

    public GroceryExampleEncoderStage(com.ociweb.pronghorn.stage.scheduling.GraphManager gm, Pipe<MessageSchemaDynamic>  input, Pipe<RawDataSchema> output) {
        super(gm,input,output);
        this.output = output;
        this.input = input;
        Pipe.from(input).validateGUID(FROM_GUID);
        previousIntDictionary = FROM.newIntDefaultsDictionary();
        previousLongDictionary = FROM.newLongDefaultsDictionary();
        defIntDictionary = FROM.newIntDefaultsDictionary();
        defLongDictionary = FROM.newLongDefaultsDictionary();
    }

    private StringBuilder workspace0x67 = new StringBuilder();
    private StringBuilder workspace0x6a = new StringBuilder();
    private Pipe<MessageSchemaDynamic> input;
    public final static FieldReferenceOffsetManager FROM = new FieldReferenceOffsetManager(
            new int[]{0xc1400007,0x88200000,0x98800000,0xa0000000,0x80800001,0x80a00002,0xa4000001,0xc1200007},
            (short)0,
            new String[]{"InventoryDetails","StoreID","Date","ProductName","Amount","RecordID","Units",null},
            new long[]{1, 101, 102, 103, 104, 105, 106, 0},
            new String[]{"global",null,null,null,null,null,null,null},
            "groceryExample.xml",
            new long[]{4, 4, 0},
            new int[]{4, 4, 0});
    long[] previousLongDictionary;
    int[] previousIntDictionary;
    long[] defLongDictionary;
    int[] defIntDictionary;
    DataOutputBlobWriter<MessageSchemaDynamic> writer;
    private Pipe<RawDataSchema> output;

// GENERATED LOW LEVEL READER
// # Low level API is the fastest way of reading from a pipe in a business semantic way.
// # Do not change the order that fields are read, this is fixed when using low level.
// # Do not remove any field reading, every field must be consumed when using low level.

    // Details to keep in mind when you expect the schema to change over time
// # Low level API is CAN be extensiable in the sense which means ignore unrecognized messages.
// # Low level API is CAN NOT be extensiable in the sense of dealing with mising or extra/new fields.
// # Low level API is CAN NOT be extensiable in the sense of dealing with fields encoded with different types.
    private static final int[] FROM_GUID = new int[]{236463696, 1042588431, 307749989, 0, (-1421281399), (-1920029437), 1480946118, 1348572483};
    private static final long BUILD_TIME = 1479219175658L;

    public void startup() {
        Pipe.from(input).validateGUID(FROM_GUID);
    }

    @Override
    public void run() {
        if (!Pipe.hasContentToRead(input)) {
            return;
        }
        int cursor;
        if (Pipe.hasContentToRead(input)  && Pipe.hasRoomForWrite(output)){
            cursor = Pipe.takeMsgIdx(input);
            switch(cursor) {
                case /*processPipeInventoryDetails*/0:
                    processPipeInventoryDetails();
                    Pipe.confirmLowLevelRead(input, 11 /* fragment size */);
                    break;
                case -1:
                    Pipe.confirmLowLevelRead(input, Pipe.EOF_SIZE);
                    Pipe.publishEOF(output);
                    requestShutdown();
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown message type, rebuid with the new schema.");
            }
            Pipe.releaseReads(input);
        }
    }

    private void processPipeInventoryDetails() {
        businessMethodInventoryDetails(
                Pipe.takeValue(input),
                Pipe.takeLong(input),
                Pipe.readASCII(input, Appendables.truncate(this.workspace0x67), Pipe.takeRingByteMetaData(input), Pipe.takeRingByteLen(input)),
                Pipe.takeValue(input),
                Pipe.takeValue(input),
                Pipe.readOptionalASCII(input, Appendables.truncate(this.workspace0x6a), Pipe.takeRingByteMetaData(input), Pipe.takeRingByteLen(input))    );
    }

    protected void businessMethodInventoryDetails(int pStoreID, long pDate, StringBuilder workspace0x67, int pAmount, int pRecordID, StringBuilder workspace0x6a) {
        Pipe.addMsgIdx(output, 0);
        long map = 0;
        DataOutputBlobWriter<RawDataSchema> writer = Pipe.outputStream(output);
        DataOutputBlobWriter.openField(writer);
        map = PhastEncoder.pmapBuilderString(map, -1543503871, (workspace0x6a == null));
        map = PhastEncoder.pmapBuilderInt(map, 0, 5, pRecordID, previousIntDictionary[2], defIntDictionary[2], false);
        map = PhastEncoder.pmapBuilderInt(map, 0, 4, pAmount, previousIntDictionary[1], defIntDictionary[1], false);
        map = PhastEncoder.pmapBuilderString(map, -1610612736, (workspace0x67 == null));
        map = PhastEncoder.pmapBuilderLong(map, 6, 4, pDate, previousLongDictionary[0], defLongDictionary[0], false);
        map = PhastEncoder.pmapBuilderInt(map, 2, 1, pStoreID, previousIntDictionary[0], defIntDictionary[0], false);
        DataOutputBlobWriter.writePackedLong(writer, map);
        long bitMask = 1;

        PhastEncoder.copyInt(previousIntDictionary, writer, map, bitMask, 0, pStoreID, false);
        bitMask = bitMask << 1;
        PhastEncoder.encodeDeltaLong(previousLongDictionary, writer, map, bitMask, 0, pDate, false);
        bitMask = bitMask << 1;
        PhastEncoder.encodeString(writer, workspace0x67, map, bitMask, false);
        bitMask = bitMask << 1;
        PhastEncoder.encodeDeltaInt(previousIntDictionary, writer, map, bitMask, 1, pAmount, false);
        bitMask = bitMask << 1;
        PhastEncoder.incrementInt(previousIntDictionary, writer, map, bitMask, 2, false);
        bitMask = bitMask << 1;
        PhastEncoder.encodeString(writer, workspace0x6a, map, bitMask, true);
        DataOutputBlobWriter.closeLowLevelField(writer);
        Pipe.publishWrites(output);
    }

}


