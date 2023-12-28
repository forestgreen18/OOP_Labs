// Author.h
#ifndef AUTHOR_H // Include guard
#define AUTHOR_H

#include <string> // Include the header for std::string

// Base class declaration
class Teacher {
public:
    // Public members are accessible from anywhere
    std::string subject;
    int salary;

    // Public constructor
    Teacher(std::string s, int sal);

    // Public method
    void teach();
};

// Base class declaration
class Writer {
public:
    // Public members are accessible from anywhere
    std::string genre;
    int books;

    // Public constructor
    Writer(std::string g, int b);

    // Public method
    void write();
};

// Derived class declaration
// Use virtual inheritance to avoid the diamond problem
class Author : public virtual Teacher, public virtual Writer {
public:
    // Public members are accessible from anywhere
    std::string name;
    int age;

    // Public constructor
    Author(std::string n, int a, std::string s, int sal, std::string g, int b);

    // Public method
    void display();
};

#endif // End of include guard
