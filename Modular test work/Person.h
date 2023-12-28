// Person.h
#ifndef MODULAR_TEST_WORK_PERSON_H
#define MODULAR_TEST_WORK_PERSON_H

#include <string> // Include the header for std::string

// Base class declaration
class Person {
public:
    // Public members are accessible from anywhere
    std::string name;
    int age;

    // Public constructor
    Person(std::string n, int a);

    // Public method
    void display();
};

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

#endif //MODULAR_TEST_WORK_PERSON_H
