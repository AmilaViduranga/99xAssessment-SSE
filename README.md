# 99x Assessment

This is the answer for the assessment that given by 99x technologies for the position full stack SSE. This assessment is on develop small calculator to calculate the price that customer purchased. According to the assessment the price structures are bellow.

 - The rare product "Penguin-ears" is 20 units per carton. A carton is 175
 - The product "Horseshoe" is 5 units per carton, a carton is 825
 - If you purchase single units, the price is acquired using the carton price multiplied by an increase of 30% (1.3). (to compensate for manual labor)
 - If you purchase 3 cartons or more, you will receive a 10% discount off the carton price

And the requirements are also listed here
 - You are to create a price engine and a small calculation feature
 - The calculation will determine the price of two products for a store
 - The price engine is to optimize the price, meaning if you order 25 units and you have 20 units per carton, the price will be set at 1 carton and 5 single units
 - One GUI is to list the prices in a table, listing the actual prices for a product from 1-50 units
 - Another GUI is to present a simple calculator which lets the user choose product and quantity of either single units or cartons (Like a shopping cart in a web store)
 - The price is to be calculated server side (not in Javascript)
 - The service is to be created using test-driven-development (JUnit or Spock)
 - Use a database (e.g. PostgreSQL) to store the initial prices and products, (in the event of time shortage, this can be omitted in favor of hard coding/simple file)
 - The calculation logic is to be developed in Java
According to above requirement I have built spring boot application to handle back end logic, angular app to handle front end logic, JUnit has used for testing purposes. Addition to requirement I have built swagger ui and docker file for built image. I have used hibernate for ORM purposes. 

# Spring Boot App ( Back end)

## how to run back end API
To run this back end api you should need bellow 

 - JAVA 8
 - My SQL
 - Maven
 If you don't have above you have to install them. Here I suppose that you are using some IDE like eclipse or IntelJ. You have to import the **API** folder as **Maven** import. Then you have to create empty database named **assessment**. I have develop this database with the username **demon** and password **Demo_pwd9**. You can change that at **application.properties** file which is available under ***api/src/main/resources*** folder. If the every thing in above are completed then you can run this project with the help of ide. There is a file called **ApiApplication.java**. That file is the main file with main method. You have to run that file using ide or maven.
 
 - It is no need to run database.sql file. If you want to add initial items then you have to run that one. Otherwise you can insert any item by adding new item using front end app

## How to run front end application
To run this front end api you should install bellow thing in your machine
 - angular CLI - Latest
 - Node JS - Latest

After installation above things then you can run the front end app.
 - You have to navigate into view folder
 - Then you have to run ```npm install``` in terminal 
 - Then you have to run ```ng serve --aot``` or  ```ng serve``` command
 - Then open any browser and type ```http://localhost:4200``` in address bar.
 
 ## Scrennshots of application
 - Home Page
![enter image description here](https://raw.githubusercontent.com/AmilaViduranga/99xAssessment-SSE/master/application%20screenshots/home.png)
 - Add Item
 
![enter image description here](https://raw.githubusercontent.com/AmilaViduranga/99xAssessment-SSE/master/application%20screenshots/purchased-item.png)

- Edit Item
![enter image description here](https://raw.githubusercontent.com/AmilaViduranga/99xAssessment-SSE/master/application%20screenshots/edit-item.png)

- Edit Purchased Item
![enter image description here](https://raw.githubusercontent.com/AmilaViduranga/99xAssessment-SSE/master/application%20screenshots/edit-purchased-items.png)

- Purchased Item
![enter image description here](https://raw.githubusercontent.com/AmilaViduranga/99xAssessment-SSE/master/application%20screenshots/purchased-item.png)
