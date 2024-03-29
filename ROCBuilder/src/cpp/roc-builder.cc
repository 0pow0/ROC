#include "roc-builder.h"
#include <iostream>

ROCBuilder::ROCBuilder() {}

std::pair<uint8_t *, int> ROCBuilder::buildThroughputResp(
  string id,
  string thrpt,
  string time_stamp,
  string total_bytes,
  string no_connection)
{
  flatbuffers::FlatBufferBuilder builder(0);
  thrptBuilder.uav_id = id;
  thrptBuilder.throughput = thrpt;
  thrptBuilder.time_stamp = time_stamp;
  thrptBuilder.total_bytes = total_bytes;
  thrptBuilder.no_connection = no_connection;
  auto thrptResp = thrptBuilder.buildThroughputResp(builder);
  auto rocInfo = ROC::CreateMessage(
      builder,
      ROC::MessageType_ThroughputResp,
      thrptResp.Union());
  builder.Finish(rocInfo);
  uint8_t *buffer_pointer = builder.GetBufferPointer();
  return std::make_pair(buffer_pointer, (int)builder.GetSize());
}

std::pair<uint8_t *, int> ROCBuilder::buildSINRResp(
  string id,
  string sinr,
  string distance,
  string cqi,
  string enb_id)
{
  flatbuffers::FlatBufferBuilder builder(0);
  sinrBuilder.uav_id = id;
  sinrBuilder.sinr = sinr;
  sinrBuilder.distance = distance;
  sinrBuilder.cqi = cqi;
  sinrBuilder.enb_id = enb_id;
  auto sinrResp = sinrBuilder.buildSinrResp(builder);
  auto rocInfo = ROC::CreateMessage(
      builder,
      ROC::MessageType_SINRResp,
      sinrResp.Union());
  builder.Finish(rocInfo);
  // std::cout << "[ROCBuilde]Size = " << (int) builder.GetSize() << "\n";
  uint8_t *buffer_pointer = builder.GetBufferPointer();
  return std::make_pair(buffer_pointer, (int)builder.GetSize());
  // return (int) builder.GetSize();
}

std::pair<uint8_t *, int> ROCBuilder::buildEnbResp(string id, string used_RB, 
  string number_of_ue)
{
  flatbuffers::FlatBufferBuilder builder(0);
  eNBRespBuilder.enb_id = id;
  eNBRespBuilder.used_RB = used_RB;
  eNBRespBuilder.number_of_ue = number_of_ue;
  auto eNBResp = eNBRespBuilder.buildEnbRespBuilder(builder);
  auto rocInfo = ROC::CreateMessage(
      builder,
      ROC::MessageType_EnbResp,
      eNBResp.Union());
  builder.Finish(rocInfo);
  uint8_t *buffer_pointer = builder.GetBufferPointer();
  return std::make_pair(buffer_pointer, (int)builder.GetSize());
}

std::pair<uint8_t*, int> ROCBuilder::buildUeResp (string id, string enb_id) {
  flatbuffers::FlatBufferBuilder builder(0);
  ueRespBuilder.uav_id = id;
  ueRespBuilder.attached_enb_id = enb_id;
  auto resp = ueRespBuilder.buildUeResp(builder);
  auto rocInfo = ROC::CreateMessage(
    builder,
    ROC::MessageType_UeResp,
    resp.Union());
  builder.Finish(rocInfo);
  uint8_t *buffer_pointer = builder.GetBufferPointer();
  return std::make_pair(buffer_pointer, (int)builder.GetSize());
}
