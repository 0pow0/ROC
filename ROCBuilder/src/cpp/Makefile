all: compile
	mv ./*.o ../../out/cpp/
	cd ../../out/cpp && ar rcs libroc.a ./*.o
	cd ../../out/cpp && rm ./*.o
compile:
	clang++ -c -std=c++11 ../../src/cpp/*.cc -I ../../../cpp/include -I ../../../cpp
