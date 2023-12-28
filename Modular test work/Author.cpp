// Author.cpp
#include "Author.h" // Include the header that contains the class declarations
#include <iostream> // Include the header for std::cout and std::endl

// Base class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Teacher::Teacher(std::string s, int sal) {
    subject = s;
    salary = sal;
}

// Public method
void Teacher::teach() {
    std::cout << "Teaching " << subject << std::endl;
}

// Base class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Writer::Writer(std::string g, int b) {
    genre = g;
    books = b;
}

// Public method
void Writer::write() {
    std::cout << "Writing " << genre << std::endl;
}

// Derived class definition
// Use the scope resolution operator :: to specify the class name before the member name

// Public constructor
Author::Author(std::string n, int a, std::string s, int sal, std::string g, int b) : Teacher(s, sal), Writer(g, b) {
    // Calling the base class constructors
    name = n;
    age = a;
}

// Public method
void Author::display() {
    std::cout << "Name: " << name << std::endl;
    std::cout << "Age: " << age << std::endl;
    std::cout << "Subject: " << subject << std::endl;
    std::cout << "Salary: " << salary << std::endl;
    std::cout << "Genre: " << genre << std::endl;
    std::cout << "Books: " << books << std::endl;
}
