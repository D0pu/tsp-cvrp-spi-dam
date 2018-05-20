all:
	javac util/*.java -d build
	javac tsp/*.java  -d build
	javac cvrp/*.java -d build -cp build

runargs := $(wordlist 2, $(words $(MAKECMDGOALS)), $(MAKECMDGOALS))
$(eval $(runargs):;@true)

runTSP: tsp/*.java
	@cd build && java tsp.MainTSP -$(runargs) && cd .. && /bin/true

runCVRP: cvrp/*.java
	@cd build && java cvrp.MainCVRP -$(runargs) && cd .. && /bin/true
