// main.cpp
#include <iostream> // Include the header for std::cout and std::endl
#include "Person.h" // Include the header that contains the base class declaration
#include "Teacher.h" // Include the header that contains the first derived class declaration
#include "Writer.h" // Include the header that contains the second derived class declaration
#include "Author.h" // Include the header that contains the third derived class declaration

int main() {
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