# LaborManagement

#Disaster Recovery Labor Training Project Documentation

#Project Flow: 
#Application consists of 7 entities. The first entity is the timesheet which is invoked first by the user has logged in from the 2 entities of user and role. Once the user has logged in to their respective account role (admin or user role) has the timesheet objects of what the user inputs. Once the user inputs on the time card the timecard is inputted the backend will start its work. Once the user has inputted the timecard they will be then directed to the machine The timecard entity has inputted by the job manager and the machine manager. The job manager manages the workload of every user and once it gets the workload it will update the timesheet. The machine manager page is controlled by
#Software Package Managers:
#Back End: 
#Spring Boot: For starting project. 
#Spring Security: Spring Security for managing user and admin authentication. Access permission.
#Thyme-Leaf: Render Spring Boot HTML files for webpage use. 
#Data JDBC: Data access layer for MySQL. 
#Spring Data JPA: Data JPA repositories. 
#Spring Web: Built from spring boot application as all web applications use spring web to initialize. 
#MySQL: MySQL for CRUD operations of data with annotation use. 
#Spring Boot Test: Testing each module based on Junit.
#Mockito: Additional testing entity.
#Server: 
#Bitnami: Remote server connectivity for local host. 
#AWS RDS: Database connectivity with MySQL to server. 
#Front End:
#Bootstrap/HTML: Creation of webpage. 
#Editors and IDE’s: 
#Eclipse: Application for creation of project.

#Stack Configuration: 
#•	Application Framework (Spring Boot)
#•	Python WSGI HTTP Server (Bitnami)
#•	Web Server (EC2)
#•	Database (MySQL)
