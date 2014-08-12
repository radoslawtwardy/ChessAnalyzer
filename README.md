ChessAnalyzer
=============

Finder of all possible unique configurations of a set of normal chess pieces on a chess board with dimensions M×N where none of the pieces is in a position to take any of the others.

Program takes following arguments:
* -h horizontal size of chess board (high) - mandatory
* -w vertical size of chess board (width) - mandatory
* -K number of kings - optionally (if not set then 0)
* -Q number of queen - optionally (if not set then 0)
* -R number of rooks - optionally (if not set then 0)
* -B number of bishops - optionally (if not set then 0)
* -N number of knights - optionally (if not set then 0)
* --onlyNumber - if this argument exist then will not print all possible solution in output (printed will be only number of possible solution) - useful if number of solution is pretty much
* --sequential - if set then sequential version of algorithm will be run (in default parallel algorithm based on MxN futures). 
Parallel solutions is faster, but use more memory. Remember to set appropriate to size of task amount of memory 

If you want try you may download this app, go to project directory named "ChessAnalyzer" and next run: `sbt run args`
for example
```
sbt "run -h 7 -w 7 -Q 2 -B 2 -K 2 -N 1 --onlyNumber"
```
For this example you will get:
```
INFO  AnalyzerApp$ {chessanalyzer.AnalyzerApp$ withLogging} - Total allowed setups is: 3063828
INFO  AnalyzerApp$ {chessanalyzer.AnalyzerApp$ withLogging} - Total time: 36402 ms (~ 36 s)
```
