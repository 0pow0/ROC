#include "roc-builder.h"
#include <iostream>

ROCBuilder::ROCBuilder() {}

std::pair<uint8_t*, int> ROCBuilder::buildSINRResp (
    string delay,
    string id, 
    string sinr) {
    flatbuffers::FlatBufferBuilder builder(0);
    sinrBuilder.uav_id = id;
    sinrBuilder.sinr = sinr;
    auto sinrResp = sinrBuilder.buildSinrResp(builder);
    auto delay_ = builder.CreateString(delay);
    auto rocInfo = ROC::CreateROCInfo(
        builder,
        delay_,
        ROC::ROCType_SINRResp,
        sinrResp.Union());
    builder.Finish(rocInfo);
    //std::cout << "[ROCBuilde]Size = " << (int) builder.GetSize() << "\n";
    uint8_t* buffer_pointer = builder.GetBufferPointer();
    return std::make_pair(buffer_pointer, (int) builder.GetSize());
    //return (int) builder.GetSize();
}