#include <iostream>
#include "ROC_generated.h"

using namespace ROC;

int main() {
    flatbuffers::FlatBufferBuilder builder;
    auto uav_id = builder.CreateString("1");
    auto latitude = builder.CreateString("123");
    auto longitude = builder.CreateString("123");
    int master_id = 10;
    auto creation_info = CreateCreationInfo(builder, uav_id, latitude,
            longitude, master_id);
    auto roc_info_s = CreateROCInfo(builder, ROCType_Create, creation_info.Union());
    builder.Finish(roc_info_s);

    
    uint8_t *buffer_pointer = builder.GetBufferPointer(); 
    auto roc_info = GetROCInfo(buffer_pointer); 
    auto roc_type = roc_info->info_type();
    if (roc_type == ROCType_Create) {
        std::cout << "validating ... " << std::endl;
        auto creation_info = static_cast<const CreationInfo*>(roc_info->info());
        auto uav_id = creation_info->uav_id()->str();
        std::cout << "uav_id = " << uav_id << std::endl;
        assert(!uav_id.compare("1"));
        auto lat = creation_info->latitude()->str();
        std::cout << "lat = " << lat << std::endl;
        assert(!lat.compare("123"));
        auto lng = creation_info->longitude()->str();
        std::cout << "lng = " << lng << std::endl;
        assert(!lng.compare("123"));
        auto master_id = creation_info->master_id();
        std::cout << "master_id  = " << master_id << std::endl;
        assert(master_id == 10);
    }
    return 0;
}
