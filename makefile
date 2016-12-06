

all: maven copy

maven:
	mvn clean package

copy:
	cp -f lanceurs/run_nc lanceurs/run_wc .