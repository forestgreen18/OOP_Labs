// Graduate.h
#ifndef GRADUATE_H // Include guard
#define GRADUATE_H

#include "Student.h" // Include the header that contains the base class declaration

// Derived class declaration
class Graduate : public Student {
public:
    // Public members are accessible from anywhere
    std::string degree;
    std::string thesis;

    // Public constructor
    Graduate(std::string n, int a, int i, double g, std::string d, std::string t);

    // Public method
    void display();
};

#endif // End of include guard