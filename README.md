#problem description - counter to start counting down from the period specified in the home page and verify that the countdown is happening every second and that the remaining time decreases in one-second increments.

Programming langugae used - JAVA

Tools used - selenium , TestNG (6.14.3), Cucumber, GIt, GitHub, Maven


Framework details - Maven based Cucumber and TestNG Framework


Skills used - BDD, Automation, devops

#Instructions to run framework in different ways -

1) right click on countDownApp.java (src/test/java) -> run as a testng (project will run as testng)
2) right click on Runner.java (src/test/java/TestRunner) -> run as a testNg (project will run as cucumber)
3) right click on testng.xml -> run as a testng (project will run as testng)
4) command prompt under project directory -> mvn clean test -> (project will run as testng)

#Chrome Browser- Version 96.0.4664.110

#Note: user can send any timer value from env.properties file. Program will pick the updated value at runtime and start the countdown timer.
