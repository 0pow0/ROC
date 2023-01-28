#include "ROC_generated.h"

using namespace std;

class EnbRespBuilder {
public:
string enb_id;
string used_RB;
string number_of_ue;
public:
EnbRespBuilder();
EnbRespBuilder(string, string, string);
flatbuffers::Offset<ROC::EnbResp> buildEnbRespBuilder (flatbuffers::FlatBufferBuilder&);
};