package ROCBuilder;

import ROCBuilder.*;
import ROC.ROCInfo;
import ROC.ROCType;

import java.util.Arrays;

import com.google.flatbuffers.FlatBufferBuilder;

public class ROCBuilder {
    public ActionInfoBuilder actionInfoBuilder;
    public CreationInfoBuilder creationInfoBuilder;
    public DeletionInfoBuilder deletionInfoBuilder;
    public SINRReqBuilder sinrReqBuilder;
    public ThroughputReqBuilder throughputReqBuilder;
    public EnbReqBuilder eNBReqBuilder;
    private int messageLength = 1024;

    public ROCBuilder() {
        this.actionInfoBuilder = new ActionInfoBuilder(); 
        this.creationInfoBuilder = new CreationInfoBuilder();
        this.deletionInfoBuilder = new DeletionInfoBuilder();
        this.sinrReqBuilder = new SINRReqBuilder();
        this.throughputReqBuilder = new ThroughputReqBuilder();
        this.eNBReqBuilder = new EnbReqBuilder();
    }

    public byte[] buildEnbReq(String delay, String eNBID) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        eNBReqBuilder.setEnBID(eNBID);
        int eNBReq = eNBReqBuilder.buildEnbReq(builder);
        int delay_ = builder.createString(delay);

        ROCInfo.startROCInfo(builder);
        ROCInfo.addDelay(builder, delay_);
        ROCInfo.addInfoType(builder, ROCType.EnbReq);
        ROCInfo.addInfo(builder, eNBReq);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        if (buf.length < 1024) buf = Arrays.copyOf(buf, messageLength);
        return buf;
    }

    public byte[] buildThroughputReq(String delay, String uavId) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        throughputReqBuilder.setUavId(uavId);
        int throughputReq = throughputReqBuilder.buildThroughputReq(builder);
        int delay_ = builder.createString(delay);

        ROCInfo.startROCInfo(builder);
        ROCInfo.addDelay(builder, delay_);
        ROCInfo.addInfoType(builder, ROCType.ThroughputReq);
        ROCInfo.addInfo(builder, throughputReq);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        if (buf.length < 1024) buf = Arrays.copyOf(buf, messageLength);
        return buf;
    }

    public byte[] buildSINRReq(String delay, String uavId) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        sinrReqBuilder.setUavId(uavId);
        int sinrReq = sinrReqBuilder.buildSINRReq(builder);
        int delay_ = builder.createString(delay);
        
        ROCInfo.startROCInfo(builder);
        ROCInfo.addDelay(builder, delay_);
        ROCInfo.addInfoType(builder, ROCType.SINRReq);
        ROCInfo.addInfo(builder, sinrReq);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        if (buf.length < 1024) buf = Arrays.copyOf(buf, messageLength);
        return buf;
    }

    public byte[] buildDeletionInfo(String delay, String uavId) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        deletionInfoBuilder.setUavId(uavId);
        int deletionInfo = deletionInfoBuilder.buildDeletionInfo(builder);
        int delay_ = builder.createString(delay);

        ROCInfo.startROCInfo(builder); 
        ROCInfo.addDelay(builder, delay_);
        ROCInfo.addInfoType(builder, ROCType.Delete);
        ROCInfo.addInfo(builder, deletionInfo);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        if (buf.length < 1024) buf = Arrays.copyOf(buf, messageLength);
        return buf;
    }

    public byte[] buildCreationInfo(String delay, String uavId, String lat, String lng, int masterId) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        creationInfoBuilder.setUavId(uavId);
        creationInfoBuilder.setLat(lat);
        creationInfoBuilder.setLng(lng);
        creationInfoBuilder.setMasterId(masterId);
        int creationInfo = creationInfoBuilder.buildCreationInfo(builder);

        int delay_ = builder.createString(delay);

        ROCInfo.startROCInfo(builder);
        ROCInfo.addDelay(builder, delay_);
        ROCInfo.addInfoType(builder, ROCType.Create);
        ROCInfo.addInfo(builder, creationInfo);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        if (buf.length < 1024) buf = Arrays.copyOf(buf, messageLength);
        return buf;
    }

    public byte[] buildActionInfo(String delay, String uavId, String lat, String lng) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        actionInfoBuilder.setUavId(uavId);
        actionInfoBuilder.setLat(lat);
        actionInfoBuilder.setLng(lng);
        int actionInfo = actionInfoBuilder.buildActionInfo(builder);

        int delay_ = builder.createString(delay);

        ROCInfo.startROCInfo(builder);
        ROCInfo.addDelay(builder, delay_);
        ROCInfo.addInfoType(builder, ROCType.Action);
        ROCInfo.addInfo(builder, actionInfo);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        if (buf.length < 1024) buf = Arrays.copyOf(buf, messageLength);
        return buf;
    }
}
