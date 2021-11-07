
flatbuffer_cpp:
	~/Code/flatbuffers/flatbuffers-2.0.0/flatc --cpp ROC.fbs
	rm cpp/ROC_generated.h && mv ROC_generated.h cpp
flatbuffer_java:
	~/Code/flatbuffers/flatbuffers-2.0.0/flatc --java ROC.fbs
	rm -rf java/ROC
	mv ROC java/

roc_java: flatbuffer_java
	cd java && javac --release 8 ./ROC/* -d out
