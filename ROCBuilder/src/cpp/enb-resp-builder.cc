#include "enb-resp-builder.h"

using namespace std;

EnbRespBuilder::EnbRespBuilder()
: enb_id(""),
  used_RB(""),
  number_of_ue("")
{}

EnbRespBuilder::EnbRespBuilder(string id, string used_RB_, string number_of_ue)
: enb_id(id),
  used_RB(used_RB_),
  number_of_ue(number_of_ue)
{}

flatbuffers::Offset<ROC::EnbResp> EnbRespBuilder::buildEnbRespBuilder (flatbuffers::FlatBufferBuilder& builder)
{
    auto enb_id_ = builder.CreateString(enb_id);
    auto used_RB_ = builder.CreateString(used_RB);
    auto number_of_ue_ = builder.CreateString(number_of_ue);
    return ROC::CreateEnbResp(builder, enb_id_, used_RB_, number_of_ue_);
}