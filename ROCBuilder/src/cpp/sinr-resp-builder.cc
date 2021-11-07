#include "sinr-resp-builder.h"

using namespace std;

SinrRespBuilder::SinrRespBuilder() 
:uav_id(""),
sinr(""),
distance(""),
cqi("")
{}

SinrRespBuilder::SinrRespBuilder(string id, string sinr, string distance, string cqi)
:uav_id(id),
sinr(sinr),
distance(distance),
cqi(cqi)
{}

flatbuffers::Offset<ROC::SINRResp> SinrRespBuilder::buildSinrResp (
    flatbuffers::FlatBufferBuilder &builder) {
    auto uav_id_ = builder.CreateString (uav_id);
    auto sinr_ = builder.CreateString (sinr);
    auto distance_ = builder.CreateString(distance);
    auto cqi_ = builder.CreateString(cqi);
    return ROC::CreateSINRResp(builder, uav_id_, sinr_, distance_, cqi_);
}