#include "sinr-resp-builder.h"

using namespace std;

class ROCBuilder {
public:
SinrRespBuilder sinrBuilder;

public:
ROCBuilder();
uint8_t* buildSINRResp (string, string, string);
};