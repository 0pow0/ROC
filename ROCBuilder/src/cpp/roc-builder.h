#include "sinr-resp-builder.h"

using namespace std;

class ROCBuilder {
public:
SinrRespBuilder sinrBuilder;

public:
ROCBuilder();
std::pair<uint8_t*, int> buildSINRResp (string, string, string);
};