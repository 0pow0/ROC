#include "ROC_generated.h"

using namespace std;

class EnbRespBuilder {
public:
string enb_id;
string used_RB;
public:
EnbRespBuilder();
EnbRespBuilder(string, string);
flatbuffers::Offset<ROC::EnbResp> buildEnbRespBuilder (flatbuffers::FlatBufferBuilder&);
};