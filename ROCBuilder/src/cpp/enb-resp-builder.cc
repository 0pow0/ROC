#include "enb-resp-builder.h"

using namespace std;

EnbRespBuilder::EnbRespBuilder()
: enb_id(""),
  used_RB("")
{}

EnbRespBuilder::EnbRespBuilder(string id, string used_RB_)
: enb_id(id),
  used_RB(used_RB_)
{}

flatbuffers::Offset<ROC::EnbResp> EnbRespBuilder::buildEnbRespBuilder (flatbuffers::FlatBufferBuilder& builder)
{
    auto enb_id_ = builder.CreateString(enb_id);
    auto used_RB_ = builder.CreateString(used_RB);
    return ROC::CreateEnbResp(builder, enb_id_, used_RB_);
}