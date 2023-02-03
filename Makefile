
flatbuffer_cpp:
	~/code/flatbuffers/flatc --cpp ROC.fbs
	rm cpp/ROC_generated.h && mv ROC_generated.h cpp
flatbuffer_java:
	~/code/flatbuffers/flatc --java ROC.fbs
	rm -rf java/ROC
	mv ROC java/

roc_java: flatbuffer_java
	rm -rf java/out && mkdir java/out
	cd java && javac ./ROC/* -d out
