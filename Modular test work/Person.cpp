// Person.cpp
#include "Person.h" // Include the header that contains the class declaration
#include <iostream> // Include the header for std::cout and std::endl

// Base class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Person::Person(std::string n, int a) {
    name = n;
    age = a;
}

// Public method
void Person::display() {
    std::cout << "Name: " << name << std::endl;
    std::cout << "Age: " << age << std::endl;
}