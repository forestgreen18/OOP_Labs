cmake_minimum_required(VERSION 3.16)
project(Modular_test_work)

set(CMAKE_CXX_STANDARD 20)

add_executable(Modular_test_work main.cpp Person.cpp Person.h Author.cpp Author.h Student.cpp Student.h Graduate.cpp Graduate.h)