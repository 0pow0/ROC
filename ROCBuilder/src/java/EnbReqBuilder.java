package ROCBuilder;

import ROC.EnbReq;
import com.google.flatbuffers.FlatBufferBuilder;

public class EnbReqBuilder {
    private String eNBID;

    public String getEnBID() {
        return eNBID;
    }

    public void setEnBID(String eNBID) {
        this.eNBID = eNBID;
    }

    public EnbReqBuilder() {
        eNBID = "";
    }

    public int buildEnbReq(FlatBufferBuilder builder)  {
        int eNBID_ = builder.createString(eNBID);

        EnbReq.startEnbReq(builder);
        EnbReq.addEnbId(builder, eNBID_);
        int eNBReq = EnbReq.endEnbReq(builder);
        return eNBReq;
    }

}