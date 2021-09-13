package ROCBuilder;

import ROC.CreationInfo;
import com.google.flatbuffers.FlatBufferBuilder;

public class CreationInfoBuilder {
    private String uavId;
    private String lat;
    private String lng;
    private int masterId;

    public CreationInfoBuilder() {
        uavId = "";
        lat = "";
        lng = "";
        masterId = -1;
    }

    public void setUavId(String uavId) {
        this.uavId = uavId; 
    }

    public String getUavId() {
        return this.uavId;
    }

    public void setLat(String lat) {
        this.lat = lat;  
    } 

    public String getLat() {
        return this.lat; 
    }

    public void setLng(String lng) {
        this.lng = lng; 
    }

    public String getLng() {
        return this.lng; 
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId; 
    }

    public int getMasterId() {
        return this.masterId; 
    }

    public int buildCreationInfo(FlatBufferBuilder builder) {
        int uav_id = builder.createString(uavId);
        int latitude = builder.createString(lat);
        int longitude = builder.createString(lng);

        CreationInfo.startCreationInfo(builder);
        CreationInfo.addUavId(builder, uav_id);
        CreationInfo.addLatitude(builder, latitude);
        CreationInfo.addLongitude(builder, longitude);
        CreationInfo.addMasterId(builder, masterId);
        int creationInfo = CreationInfo.endCreationInfo(builder);
        return creationInfo;
    }
}
