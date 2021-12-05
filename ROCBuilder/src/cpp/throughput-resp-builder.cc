#include "throughput-resp-builder.h"

using namespace std;

ThroughputRespBuilder::ThroughputRespBuilder()
:uav_id(""),
throughput(""){}

ThroughputRespBuilder::ThroughputRespBuilder(string id, string thrpt)
:uav_id(id),
throughput(thrpt) {}

flatbuffers::Offset<ROC::ThroughputResp> ThroughputRespBuilder::buildThroughputResp (
    flatbuffers::FlatBufferBuilder& builder) {
    auto uav_id_ = builder.CreateString (uav_id);
    auto thrpt = builder.CreateString (throughput);
    auto time_stamp_ = builder.CreateString (time_stamp);
    auto total_bytes_ = builder.CreateString (total_bytes);

    return ROC::CreateThroughputResp(builder, uav_id_, thrpt, time_stamp_, total_bytes_);
}