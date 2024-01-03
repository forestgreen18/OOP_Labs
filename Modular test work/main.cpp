<<<<<<< Updated upstream
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
=======
#include <iostream>
using namespace std;

// Базовий клас, що представляє тварину
class Animal {
public:
    // Метод, який робить так, щоб тварина їла
    void eat() {
        cout << "Animal eating" << endl;
    }
};

// Похідний клас, що представляє собаку
class Dog : public Animal {
public:
    // Метод, що викликає гавкіт собаки
    void bark() {
        cout << "Dog barking" << endl;
    }
};


// Main function
int main() {

    // Створити об'єкт Dog
    Dog d;
    // Викликати його успадковані та власні методи
    d.eat();
    d.bark();
    // Вивести повідомлення
    cout << "Done" << endl;
    return 0;
}
>>>>>>> Stashed changes
