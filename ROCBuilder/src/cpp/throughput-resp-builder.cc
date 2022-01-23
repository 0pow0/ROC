#include "throughput-resp-builder.h"

using namespace std;

ThroughputRespBuilder::ThroughputRespBuilder()
:uav_id(""),
throughput(""),
time_stamp(""),
total_bytes(""), 
no_connection("") {}

ThroughputRespBuilder::ThroughputRespBuilder(string id, string thrpt, string time_stamp, string total_bytes, string no_connection)
:uav_id(id),
throughput(thrpt),
time_stamp(time_stamp),
total_bytes(total_bytes),
no_connection(no_connection) {}

flatbuffers::Offset<ROC::ThroughputResp> ThroughputRespBuilder::buildThroughputResp (
    flatbuffers::FlatBufferBuilder& builder) {
    auto uav_id_ = builder.CreateString (uav_id);
    auto thrpt = builder.CreateString (throughput);
    auto time_stamp_ = builder.CreateString (time_stamp);
    auto total_bytes_ = builder.CreateString (total_bytes);
    auto no_conn = builder.CreateString(no_connection);
    return ROC::CreateThroughputResp(builder, uav_id_, thrpt, time_stamp_, total_bytes_, no_conn);
}