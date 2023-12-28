// Teacher.cpp
#include "Teacher.h" // Include the header that contains the class declaration
#include <iostream> // Include the header for std::cout and std::endl

// Derived class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Teacher::Teacher(std::string n, int a, std::string s, int sal) : Person(n, a) {
    // Calling the base class constructor
    subject = s;
    salary = sal;
}

// Public method
void Teacher::teach() {
    std::cout << "Teaching " << subject << std::endl;
}