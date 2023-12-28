// Author.cpp
#include "Author.h" // Include the header that contains the class declaration
#include <iostream> // Include the header for std::cout and std::endl

// Derived class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Author::Author(std::string n, int a, std::string s, int sal, std::string g, int b) : Teacher(n, a, s, sal), Writer(n, a, g, b) {
    // Calling the base class constructors
}


// Public method
void Author::display() {
    // Specify which base class to access the name and age members from
    std::cout << "Name: " << Teacher::name << std::endl;
    std::cout << "Age: " << Teacher::age << std::endl;
    std::cout << "Subject: " << subject << std::endl;
    std::cout << "Salary: " << salary << std::endl;
    std::cout << "Genre: " << genre << std::endl;
    std::cout << "Books: " << books << std::endl;
}
