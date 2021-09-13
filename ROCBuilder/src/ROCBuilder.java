package ROCBuilder;

import ROCBuilder.*;
import ROC.ROCInfo;
import ROC.ROCType;
import com.google.flatbuffers.FlatBufferBuilder;

public class ROCBuilder {
    public ActionInfoBuilder actionInfoBuilder;
    public CreationInfoBuilder creationInfoBuilder;
    public DeletionInfoBuilder deletionInfoBuilder;
    public SINRReqBuilder sinrReqBuilder;

    public ROCBuilder() {
        this.actionInfoBuilder = new ActionInfoBuilder(); 
        this.creationInfoBuilder = new CreationInfoBuilder();
        this.deletionInfoBuilder = new DeletionInfoBuilder();
        this.sinrReqBuilder = new SINRReqBuilder();
    }

    public byte[] buildSINRReq(String timestep, String uavId) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        sinrReqBuilder.setUavId(uavId);
        int sinrReq = sinrReqBuilder.buildSINRReq(builder);
        int ts = builder.createString(timestep);
        
        ROCInfo.startROCInfo(builder);
        ROCInfo.addTimestep(builder, ts);
        ROCInfo.addInfoType(builder, ROCType.SINRReq);
        ROCInfo.addInfo(builder, sinrReq);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        return buf;
    }

    public byte[] buildDeletionInfo(String timestep, String uavId) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        deletionInfoBuilder.setUavId(uavId);
        int deletionInfo = deletionInfoBuilder.buildDeletionInfo(builder);
        int ts = builder.createString(timestep);

        ROCInfo.startROCInfo(builder); 
        ROCInfo.addTimestep(builder, ts);
        ROCInfo.addInfoType(builder, ROCType.Delete);
        ROCInfo.addInfo(builder, deletionInfo);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        return buf;
    }

    public byte[] buildCreationInfo(String timestep, String uavId, String lat, String lng, int masterId) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        creationInfoBuilder.setUavId(uavId);
        creationInfoBuilder.setLat(lat);
        creationInfoBuilder.setLng(lng);
        creationInfoBuilder.setMasterId(masterId);
        int creationInfo = creationInfoBuilder.buildCreationInfo(builder);

        int ts = builder.createString(timestep);

        ROCInfo.startROCInfo(builder);
        ROCInfo.addTimestep(builder, ts);
        ROCInfo.addInfoType(builder, ROCType.Create);
        ROCInfo.addInfo(builder, creationInfo);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        return buf;
    }

    public byte[] buildActionInfo(String timestep, String uavId, String lat, String lng) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        actionInfoBuilder.setUavId(uavId);
        actionInfoBuilder.setLat(lat);
        actionInfoBuilder.setLng(lng);
        int actionInfo = actionInfoBuilder.buildActionInfo(builder);

        int ts = builder.createString(timestep);

        ROCInfo.startROCInfo(builder);
        ROCInfo.addTimestep(builder, ts);
        ROCInfo.addInfoType(builder, ROCType.Action);
        ROCInfo.addInfo(builder, actionInfo);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        return buf;
    }
}
