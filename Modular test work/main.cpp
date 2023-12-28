    #include <iostream>
#include "Person.h"
#include "Author.h" // Include the header that contains the class declaration


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



        // Create an Author object
        Author a("John", 40, "Math", 5000, "Sci-Fi", 10);

        // Call the display method
        a.display();

        // Call the teach method
        a.teach();

        // Call the write method
        a.write();

        return 0;


    }

    // Include the header files for string and iostream
