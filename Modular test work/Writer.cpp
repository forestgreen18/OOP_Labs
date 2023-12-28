// Writer.cpp
#include "Writer.h" // Include the header that contains the class declaration
#include <iostream> // Include the header for std::cout and std::endl

// Derived class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Writer::Writer(std::string n, int a, std::string g, int b) : Person(n, a) {
    // Calling the base class constructor
    genre = g;
    books = b;
}

// Public method
void Writer::write() {
    std::cout << "Writing " << genre << std::endl;
}