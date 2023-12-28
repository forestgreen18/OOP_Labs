    #include <iostream>
#include "Person.h"
#include "Author.h" // Include the header that contains the class declaration
#include "Student.h" // Include the header that contains the first derived class declaration
#include "Graduate.h" // Include the header that contains the second derived class declaration

    int main() {

        // Create a Graduate object
        Graduate g("Alice", 25, 123, 3.5, "MSc", "Machine Learning");

        // Call the display method
        g.display();






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
