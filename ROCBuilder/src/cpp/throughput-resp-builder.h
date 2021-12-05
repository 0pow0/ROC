#include "ROC_generated.h"

using namespace std;

class ThroughputRespBuilder {
public:
string uav_id;
string throughput;
string time_stamp;
string total_bytes;
public:
ThroughputRespBuilder();
ThroughputRespBuilder(string, string);
flatbuffers::Offset<ROC::ThroughputResp> buildThroughputResp (flatbuffers::FlatBufferBuilder&);
};