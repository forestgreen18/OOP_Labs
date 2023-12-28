// Author.h
#ifndef AUTHOR_H // Include guard
#define AUTHOR_H

#include "Teacher.h" // Include the header that contains the first base class declaration
#include "Writer.h" // Include the header that contains the second base class declaration

// Derived class declaration
// Use virtual inheritance to avoid the diamond problem
class Author : public virtual Teacher, public virtual Writer {
public:
    // Public constructor
    Author(std::string n, int a, std::string s, int sal, std::string g, int b);

    // Public method
    void display();
};

#endif // End of include guard