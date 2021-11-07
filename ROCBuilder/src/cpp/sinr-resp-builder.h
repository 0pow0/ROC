#include "ROC_generated.h"

using namespace std;

class SinrRespBuilder {
public:
string uav_id;
string sinr;
string distance;
string cqi;
public:
SinrRespBuilder();
SinrRespBuilder(string, string, string, string);
flatbuffers::Offset<ROC::SINRResp> buildSinrResp (flatbuffers::FlatBufferBuilder&);
};