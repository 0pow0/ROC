#include "ROC_generated.h"

using namespace std;

class SinrRespBuilder {
public:
string uav_id;
string sinr;
public:
SinrRespBuilder();
SinrRespBuilder(string, string);
flatbuffers::Offset<ROC::SINRResp> buildSinrResp (flatbuffers::FlatBufferBuilder&);
};