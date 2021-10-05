#include "roc-builder.h"
#include <iostream>

int main(int argc, char const *argv[])
{
    ROCBuilder rocbuilder;
    std::pair<uint8_t*, int> pair =  rocbuilder.buildSINRResp("1", "1", "1");
    std::cout << "pair second is " << pair.second<< "\n";
    return 0;
}
