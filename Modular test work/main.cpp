    #include <iostream>
#include "Person.h"

    int main() {
        std::cout << "Hello, World!" << std::endl;

        // Create a Person object
        Person p("Alice", 25);

        // Call the display method
        p.display();

        // Create a Student object
        Student s("Bob", 20, 123, 3.5);

        // Call the display method
        s.display();

        return 0;


    }

    // Include the header files for string and iostream
