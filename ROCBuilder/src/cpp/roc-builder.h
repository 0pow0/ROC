#include "sinr-resp-builder.h"
#include "throughput-resp-builder.h"

using namespace std;

class ROCBuilder {
public:
SinrRespBuilder sinrBuilder;
ThroughputRespBuilder thrptBuilder;

public:
ROCBuilder();
std::pair<uint8_t*, int> buildSINRResp (string, string, string, string, string, string);
std::pair<uint8_t*, int> buildThroughputResp (string, string, string);
};