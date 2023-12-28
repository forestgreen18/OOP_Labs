// Person.h
#ifndef PERSON_H // Include guard
#define PERSON_H

#include <string> // Include the header for std::string

// Base class declaration
class Person {
public:
    // Public members are accessible from anywhere
    std::string name;
    int age;

    // Public constructor
    Person(std::string n, int a);

    // Public method
    void display();
};

#endif // End of include guard