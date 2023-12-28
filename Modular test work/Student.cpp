
// Student.cpp
#include "Student.h" // Include the header that contains the class declaration
#include <iostream> // Include the header for std::cout and std::endl

// Derived class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Student::Student(std::string n, int a, int i, double g) : Person(n, a) {
    // Calling the base class constructor
    id = i;
    gpa = g;
}

// Public method
void Student::display() {
    // Calling the base class method
    Person::display();
    std::cout << "ID: " << id << std::endl;
    std::cout << "GPA: " << gpa << std::endl;
}