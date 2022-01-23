#include "ROC_generated.h"
#include <iostream>

using namespace std;

class ThroughputRespBuilder {
public:
string uav_id;
string throughput;
string time_stamp;
string total_bytes;
string no_connection;
public:
ThroughputRespBuilder();
ThroughputRespBuilder(string, string, string, string, string);
flatbuffers::Offset<ROC::ThroughputResp> buildThroughputResp (flatbuffers::FlatBufferBuilder&);
};