## Android_Simple_Management_Employee
From slide 5 and 6 of MOB1032 - Lập trình Android cơ bản (SP18)

# Employee Management App
This is a simple Android app to view and manage employee records.

### Features
 - [x] View a list of employees
 - [x] Add a new employee
 - [x] Edit existing employees
 - [x] Delete employees
### Tech Stack
 - Android
 - Java
 - SQLite database
### Architecture
The app uses a Model-View-Controller architecture pattern.

 - ***Employee*** - Model class representing an employee
 - ***EmployeeDAO*** - Data Access Object for database operations
 - ***DBHelper*** - Helper class to manage SQLite database
 - ***AddOrEditEmployeeActivity*** - Controls adding and editing employees
 - ***MainActivity*** - Displays list of employees
 - ***Layout XML files*** - View layer
### Usage
Upon launching the app, the user sees a list of employees.

 - Tap on an employee to edit
 - Tap on the button 'Create' to add a new employee
 - Long press on an employee to delete.

The add/edit screens allow entering employee details like name, salary, etc.
