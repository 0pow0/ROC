#include "sinr-resp-builder.h"

using namespace std;

SinrRespBuilder::SinrRespBuilder() 
:uav_id(""),
sinr("") {}

SinrRespBuilder::SinrRespBuilder(string id, string sinr)
:uav_id(id),
sinr(sinr) {}

flatbuffers::Offset<ROC::SINRResp> SinrRespBuilder::buildSinrResp (
    flatbuffers::FlatBufferBuilder &builder) {
    auto uav_id_ = builder.CreateString (uav_id);
    auto sinr_ = builder.CreateString (sinr);

    return ROC::CreateSINRResp(builder, uav_id_, sinr_);
}