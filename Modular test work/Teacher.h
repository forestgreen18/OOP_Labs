// Teacher.h
#ifndef TEACHER_H // Include guard
#define TEACHER_H

#include "Person.h" // Include the header that contains the base class declaration

// Derived class declaration
class Teacher : public Person {
public:
    // Public members are accessible from anywhere
    std::string subject;
    int salary;

    // Public constructor
    Teacher(std::string n, int a, std::string s, int sal);

    // Public method
    void teach();
};

#endif // End of include guard