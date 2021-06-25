package ROCBuilder;

import ROC.DeletionInfo;
import com.google.flatbuffers.FlatBufferBuilder;

public class DeletionInfoBuilder {
    private String uavId;

    public DeletionInfoBuilder() {
        uavId = ""; 
    }

    public void setUavId(String uavId) {
        this.uavId = uavId; 
    }

    public String getUavId() {
        return uavId; 
    }

    public int buildDeletionInfo(FlatBufferBuilder builder) {
        int uav_id = builder.createString(uavId);
        DeletionInfo.startDeletionInfo(builder);
        DeletionInfo.addUavId(builder, uav_id);
        int deletionInfo = DeletionInfo.endDeletionInfo(builder);
        return deletionInfo;
    }
}
