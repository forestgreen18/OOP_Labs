// Student.h
#ifndef STUDENT_H // Include guard
#define STUDENT_H

#include "Person.h" // Include the header that contains the base class declaration

// Derived class declaration
class Student : public Person {
public:
    // Public members are accessible from anywhere
    int id;
    double gpa;

    // Public constructor
    Student(std::string n, int a, int i, double g);

    // Public method
    void display();
};

#endif // End of include guard