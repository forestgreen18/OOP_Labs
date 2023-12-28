// Writer.h
#ifndef WRITER_H // Include guard
#define WRITER_H

#include "Person.h" // Include the header that contains the base class declaration

// Derived class declaration
class Writer : public Person {
public:
    // Public members are accessible from anywhere
    std::string genre;
    int books;

    // Public constructor
    Writer(std::string n, int a, std::string g, int b);

    // Public method
    void write();
};

#endif // End of include guard