#include "ROC_generated.h"
#include <vector>

using namespace std;

class SinrModelInputElementBuilder {
public:
string distance;
string txPower;
string bw;
string subBw;
string subOff;
public:
SinrModelInputElementBuilder();
SinrModelInputElementBuilder(string, string, string, string, string);
void set(const vector<string>&);
flatbuffers::Offset<ROC::SinrModelInputElement>  buildSinrModelInputElement (flatbuffers::FlatBufferBuilder&);
};

class PredictionModelInputRespBuilder {
private:
static SinrModelInputElementBuilder elementBuilder;
public:
string uav_id;
string distance_to_attached;
vector<vector<string>> input_sinr_model;
public:
PredictionModelInputRespBuilder();
PredictionModelInputRespBuilder(string, string, const vector<vector<string>>&);
flatbuffers::Offset<ROC::PredictionModelInputResp>  buildPredictionModelInputResp (flatbuffers::FlatBufferBuilder&);
};


