# 2nd-Semester-Exam

This repository contains the programming part of the first year examination.

## Guide for running the application

Unfortunately, it was a requirement to use the database provided by the school, therefore it will not be possible to even login to the system without physical access or a VPN connection to the schools network.

1. Make sure that you are connected to the EASV network.

2. Make sure that you have a config.properties file with the credentials to access the used database (MUST be located at the project root directory). 
    * the contents of the config file can be found in an appendix in the report handed-in

3. Open Intellij and make sure that all dependencies are downloaded through Maven (see list of dependencies below).

4. Login with the username: 'admin' and the password: 'admin' make sure to select the Administrator radio button

5. Play around and refer to the report sections concerning functionality.


## Dependencies

* javafx-fxml (17-ea+11)
    * org.openjfx

* javafx-controls (17-ea+11)
    * org.openjfx

* controlsfx (11.1.1)
    * org.controlsfx

* mssql-jdbc (10.1.0.jre17-preview)
    * com.microsoft.sqlserver

* bcrypt (0.9.0)
    * at.favre.lib

* junit (4.12)
    * junit

* javafaker (1.0.2)
    * com.github.javafaker

* annotations
    * org.jetbrains
