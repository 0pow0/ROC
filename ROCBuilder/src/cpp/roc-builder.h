#include "sinr-resp-builder.h"
#include "throughput-resp-builder.h"
#include "enb-resp-builder.h"
#include "prediction-model-input-resp-builder.h"
#include "ue-resp-builder.h"

#include <vector>

using namespace std;

class ROCBuilder {
public:
SinrRespBuilder sinrBuilder;
ThroughputRespBuilder thrptBuilder;
EnbRespBuilder eNBRespBuilder;
UeRespBuilder ueRespBuilder;

public:
ROCBuilder();
std::pair<uint8_t*, int> buildSINRResp (string, string, string, string, string);
std::pair<uint8_t*, int> buildThroughputResp (string, string, string, string, string);
std::pair<uint8_t*, int> buildEnbResp (string, string, string);
std::pair<uint8_t*, int> buildUeResp (string, string);
};