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

    return ROC::CreateThroughputResp(builder, uav_id_, thrpt);
}