package ROCBuilder;

import ROC.SINRReq;
import com.google.flatbuffers.FlatBufferBuilder;

public class SINRReqBuilder {
    private String uavId;

    public String getUavId() {
        return uavId;
    }

    public void setUavId(String uavId) {
        this.uavId = uavId;
    }

    public SINRReqBuilder() {
        uavId = "";
    }

    public int buildSINRReq(FlatBufferBuilder builder)  {
        int uav_id = builder.createString(uavId);

        SINRReq.startSINRReq(builder);
        SINRReq.addUavId(builder, uav_id);
        int sinrReq = SINRReq.endSINRReq(builder);
        return sinrReq;
    }

}