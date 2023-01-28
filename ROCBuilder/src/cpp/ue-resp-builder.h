#include "ROC_generated.h"

using namespace std;

class UeRespBuilder {
public:
string uav_id;
string attached_enb_id;
public:
UeRespBuilder();
UeRespBuilder(string, string);
flatbuffers::Offset<ROC::UeResp> buildUeResp (flatbuffers::FlatBufferBuilder&);
};