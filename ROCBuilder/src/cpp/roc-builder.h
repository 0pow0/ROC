#include "sinr-resp-builder.h"
#include "throughput-resp-builder.h"
#include "enb-resp-builder.h"

using namespace std;

class ROCBuilder {
public:
SinrRespBuilder sinrBuilder;
ThroughputRespBuilder thrptBuilder;
EnbRespBuilder eNBRespBuilder;

public:
ROCBuilder();
std::pair<uint8_t*, int> buildSINRResp (string, string, string, string, string, string);
std::pair<uint8_t*, int> buildThroughputResp (string, string, string, string, string, string);
std::pair<uint8_t*, int> buildEnbResp (string, string, string);
};