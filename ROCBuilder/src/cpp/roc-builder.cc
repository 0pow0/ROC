#include "roc-builder.h"

ROCBuilder::ROCBuilder() {}

uint8_t* ROCBuilder::buildSINRResp (
    string timestep,
    string id, 
    string sinr) {
    flatbuffers::FlatBufferBuilder builder;
    sinrBuilder.uav_id = id;
    sinrBuilder.sinr = sinr;
    auto sinrResp = sinrBuilder.buildSinrResp(builder);
    auto ts = builder.CreateString(timestep);
    auto rocInfo = ROC::CreateROCInfo(
        builder,
        ts,
        ROC::ROCType_SINRResp,
        sinrResp.Union());
    builder.Finish(rocInfo);
    uint8_t* buffer_pointer = builder.GetBufferPointer();
    return buffer_pointer;
}