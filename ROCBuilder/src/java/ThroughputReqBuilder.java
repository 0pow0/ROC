package ROCBuilder;

import ROC.ThroughputReq;
import com.google.flatbuffers.FlatBufferBuilder;

public class ThroughputReqBuilder {
   private String uavId;
   
   public String getUavId() {
       return uavId;
   }

   public void setUavId(String uavId) {
       this.uavId = uavId;
   }

   public ThroughputReqBuilder() {
       uavId = "";
   }

   public int buildThroughputReq(FlatBufferBuilder builder) {
        int uav_id = builder.createString(this.uavId);

        ThroughputReq.startThroughputReq(builder);
        ThroughputReq.addUavId(builder, uav_id);
        int throughputReq = ThroughputReq.endThroughputReq(builder);
        return throughputReq;
   }
}
