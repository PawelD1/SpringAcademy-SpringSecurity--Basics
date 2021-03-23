# SpringAcademy-SpringSecurity--Basics
# Exercise 1
Write an application that has 4 returning endpoints:
1. "Hello admin {name}"
2. "Hello user {name}"
3. "Hello somebody"
4. "Bye"
The administrator is to have access to all endpoints. User only for 2, 3, 4. and not logged in to 3. Configure the application in such a way that after logging out for the application user (regardless of the role) he will always be redirected to the endpoint with the word "Papa".
When the Administrator or User enter endpoint 3, they will get the message "Hello {name}"
# Exercise 2 - optional
Expand your application to add a counter that will verify how many times a given user has authenticated with the application. Display this message to him.
In addition to this exercise, the counter of visiting Start page by each user was added.
