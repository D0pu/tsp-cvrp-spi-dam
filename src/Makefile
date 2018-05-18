all:
	javac tsp/*.java -d build

runargs := $(wordlist 2, $(words $(MAKECMDGOALS)), $(MAKECMDGOALS))
$(eval $(runargs):;@true)

run: tsp/*.java
	@cd build && java tsp.MainTSP -$(runargs) && cd .. && /bin/true
