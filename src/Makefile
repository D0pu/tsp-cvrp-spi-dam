all:
	javac tsp/*.java -d build
	javac cvrp/*.java -d build

runargs := $(wordlist 2, $(words $(MAKECMDGOALS)), $(MAKECMDGOALS))
$(eval $(runargs):;@true)

runTSP: tsp/*.java
	@cd build && java tsp.MainTSP -$(runargs) && cd .. && /bin/true

runVRP: cvrp/*.java
	@cd build && java cvrp.MainCVRP -$(runargs) && cd .. && /bin/true
