# HCL_ECommerce
HCL Capstone Project - ECommerce

# How to set up and run the project:

1. Use the database.sql script to generate the mysql database (create ecommerce schema and run the database script. The script will drop and create new tables and insert the data).
   
2. The front-end Angular project is in the EcommerceUI folder. Open this folder in VSCode and run ```ng serve``` to run the front end. App runs on ```http://localhost:4200```
   
3. The back-end Spring Boot project is at the root of the HCL_Ecommerce folder. Import this to STS and run. Go to ```http://localhost:8080/swagger-ui/index.html``` to access Swagger page
   
4. **Important:** need to update the datasource variables in **application.properties** to your database url, username, and password for the Spring Boot project to run.
