all:
	javac tsp/*.java -d build

runargs := $(wordlist 2, $(words $(MAKECMDGOALS)), $(MAKECMDGOALS))
$(eval $(runargs):;@true)

ins:
	cd build && java tsp.MainTSP -h1 $(runargs) && cd ..

dec:
	cd build && java tsp.MainTSP -h2 $(runargs) && cd ..
