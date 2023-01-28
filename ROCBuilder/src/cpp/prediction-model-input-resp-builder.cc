#include "prediction-model-input-resp-builder.h"

SinrModelInputElementBuilder::SinrModelInputElementBuilder()
: distance(""),
txPower(""),
bw(""),
subBw(""),
subOff("")
{}

SinrModelInputElementBuilder::SinrModelInputElementBuilder(
  string distance, string txPower,
  string bw, string subBw,
  string subOff
)
: distance(distance),
txPower(txPower),
bw(bw),
subBw(subBw),
subOff(subOff)
{}

void SinrModelInputElementBuilder::set(const vector<string> &params) {
  distance = params[0];
  txPower = params[1];
  bw = params[2];
  subBw = params[3];
  subOff = params[4];
}

flatbuffers::Offset<ROC::SinrModelInputElement> SinrModelInputElementBuilder::buildSinrModelInputElement (
  flatbuffers::FlatBufferBuilder &builder) {
  auto _distance = builder.CreateString(distance);
  auto _txPower = builder.CreateString(txPower);
  auto _bw = builder.CreateString(bw);
  auto _subBw = builder.CreateString(subBw);
  auto _subOff = builder.CreateString(subOff);
  return ROC::CreateSinrModelInputElement(builder, _distance, _txPower, _bw, _subBw, _subOff);
}

SinrModelInputElementBuilder PredictionModelInputRespBuilder::elementBuilder;

PredictionModelInputRespBuilder::PredictionModelInputRespBuilder()
: uav_id(""),
distance_to_attached(""),
input_sinr_model(0)
{}

PredictionModelInputRespBuilder::PredictionModelInputRespBuilder(
  string uav_id, string distance_to_attached, 
  const vector<vector<string>> &vec)
: uav_id(uav_id),
distance_to_attached(distance_to_attached) {
  for (const auto &e : vec) {
    input_sinr_model.emplace_back(e);
  }
}

flatbuffers::Offset<ROC::PredictionModelInputResp>
PredictionModelInputRespBuilder::buildPredictionModelInputResp (
  flatbuffers::FlatBufferBuilder &builder) {
  auto _uavId = builder.CreateString(uav_id);
  auto _distance_to_attached = builder.CreateString(distance_to_attached);
  vector<flatbuffers::Offset<ROC::SinrModelInputElement>> elements;
  for (const auto &e : input_sinr_model) {
    elementBuilder.set(e);
    elements.emplace_back(elementBuilder.buildSinrModelInputElement(builder));
  }
  auto _sinr_model_input = builder.CreateVector(elements);
  return ROC::CreatePredictionModelInputResp(builder, _uavId, _sinr_model_input, _distance_to_attached);
}