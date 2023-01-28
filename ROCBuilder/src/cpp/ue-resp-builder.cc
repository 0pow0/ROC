#include "ue-resp-builder.h"

using namespace std;

UeRespBuilder::UeRespBuilder()
: uav_id(""),
attached_enb_id("")
{}

UeRespBuilder::UeRespBuilder(string id, string enb_id)
: uav_id(id),
attached_enb_id(enb_id)
{}

flatbuffers::Offset<ROC::UeResp> UeRespBuilder::buildUeResp (
  flatbuffers::FlatBufferBuilder &builder) {
  auto uav_id_ = builder.CreateString (uav_id);
  auto attached_enb_id_ = builder.CreateString (attached_enb_id);
  return ROC::CreateUeResp(builder, uav_id_, attached_enb_id_);
}