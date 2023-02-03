all: compile
	mv ./*.o ../../out/cpp/
	cd ../../out/cpp && ar rcs libroc.a ./*.o
	cd ../../out/cpp && rm ./*.o
compile:
	g++ -c -fPIC -std=c++11 ../../src/cpp/*.cc -I ../../../cpp/include -I ../../../cpp
