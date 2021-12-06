# DictionaryImplementation

Implementation of a simple dictionary data structure viz. a double hash table. The functions h1 and h2 in this case are defined within
a driver main method located in the double hash table class. These hash functions are created using the Java Function object. These are 
simply an example, and not meant to be ideal hashing functions. 

In this case : 

-The function h1 takes a key and hashes it to the index of its leftmost digit, modulo tablesize.</br>
-The function h2 takes a key and hashes it to the index of its rightmost digit, modulo tablesize.
