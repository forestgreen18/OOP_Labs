// Graduate.cpp
#include "Graduate.h" // Include the header that contains the class declaration
#include <iostream> // Include the header for std::cout and std::endl

// Derived class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Graduate::Graduate(std::string n, int a, int i, double g, std::string d, std::string t) : Student(n, a, i, g) {
    // Calling the base class constructor
    degree = d;
    thesis = t;
}

// Public method
void Graduate::display() {
    // Calling the base class method
    Student::display();
    std::cout << "Degree: " << degree << std::endl;
    std::cout << "Thesis: " << thesis << std::endl;
}