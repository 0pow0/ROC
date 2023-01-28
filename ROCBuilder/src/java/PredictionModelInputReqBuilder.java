package ROCBuilder;

import ROC.PredictionModelInputReq;
import ROC.PredictionModelInputResp;

import com.google.flatbuffers.FlatBufferBuilder;

public class PredictionModelInputReqBuilder {
    private String uavId;

    public String getUavId() {
        return uavId;
    }

    public void setUavId(String uavId) {
        this.uavId = uavId;
    }

    public PredictionModelInputReqBuilder() {
        uavId = "";
    }

    public int buildPredictionModelInputReq(FlatBufferBuilder builder) {
        int uav_id = builder.createString(uavId);

        PredictionModelInputReq.startPredictionModelInputReq(builder);
        PredictionModelInputReq.addUavId(builder, uav_id);
        int predictionModelInputReq = PredictionModelInputReq.endPredictionModelInputReq(builder);
        return predictionModelInputReq;
    }
}
