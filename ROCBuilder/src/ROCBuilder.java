package ROCBuilder;

import ROCBuilder.ActionInfoBuilder;
import ROCBuilder.CreationInfoBuilder;
import ROC.ROCInfo;
import ROC.ROCType;
import com.google.flatbuffers.FlatBufferBuilder;

public class ROCBuilder {
    public ActionInfoBuilder actionInfoBuilder;
    public CreationInfoBuilder creationInfoBuilder;

    public ROCBuilder() {
        this.actionInfoBuilder = new ActionInfoBuilder(); 
        this.creationInfoBuilder = new CreationInfoBuilder();
    }

    public byte[] buildCreationInfo(String uavId, String lat, String lng, int masterId) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        creationInfoBuilder.setUavId(uavId);
        creationInfoBuilder.setLat(lat);
        creationInfoBuilder.setLng(lng);
        creationInfoBuilder.setMasterId(masterId);
        int creationInfo = creationInfoBuilder.buildCreationInfo(builder);

        ROCInfo.startROCInfo(builder);
        ROCInfo.addInfoType(builder, ROCType.Create);
        ROCInfo.addInfo(builder, creationInfo);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        return buf;
    }

    public byte[] buildActionInfo(String uavId, String lat, String lng) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        actionInfoBuilder.setUavId(uavId);
        actionInfoBuilder.setLat(lat);
        actionInfoBuilder.setLng(lng);
        int actionInfo = actionInfoBuilder.buildActionInfo(builder);

        ROCInfo.startROCInfo(builder);
        ROCInfo.addInfoType(builder, ROCType.Action);
        ROCInfo.addInfo(builder, actionInfo);
        int rocInfo = ROCInfo.endROCInfo(builder);
        builder.finish(rocInfo);
        byte[] buf = builder.sizedByteArray();
        return buf;
    }
}
