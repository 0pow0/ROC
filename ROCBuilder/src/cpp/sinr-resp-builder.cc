#include "sinr-resp-builder.h"

using namespace std;

SinrRespBuilder::SinrRespBuilder() 
:uav_id(""),
sinr(""),
distance(""),
cqi(""),
enb_id("")
{}

SinrRespBuilder::SinrRespBuilder(string id, string sinr, string distance, string cqi, string enb_id)
:uav_id(id),
sinr(sinr),
distance(distance),
cqi(cqi),
enb_id(enb_id)
{}

flatbuffers::Offset<ROC::SINRResp> SinrRespBuilder::buildSinrResp (
    flatbuffers::FlatBufferBuilder &builder) {
    auto uav_id_ = builder.CreateString (uav_id);
    auto sinr_ = builder.CreateString (sinr);
    auto distance_ = builder.CreateString(distance);
    auto cqi_ = builder.CreateString(cqi);
    auto enb_id_ = builder.CreateString(enb_id);
    return ROC::CreateSINRResp(builder, uav_id_, sinr_, distance_, cqi_, enb_id_);
}