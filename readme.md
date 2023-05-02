Homework:

1.Create a maven tests project
2.Add dependencies with the latest versions: TestNG, Selenium,
WebDriverManager
3.Create a test with before and after annotations that will open the browser
on page https://demo.placelab.com and then print in terminal “Opened
browser”
4. Enable running a test in min 2 browsers
5. When your test is being run, now print in the terminal: “Opened browser
   ${browserName}” (should work for whatever browser your config
   supports)
6. Fetch a webpage title and print it out in the terminal
7. Run your first automated test!
8. Upload your project to GitHub (Project name: placelab-qamp-ui-tests;
   Note: Create a Pull Request ready for review)

Best deadline: May 3rd

resources/testng.xml basic config

How to setup a browser driver manually: https://www.selenium.dev/documentation/webdriver/getting_started/install_drivers/

How to run a test:
1. Make sure that you are executed in the directory where your pom.xml file is
2. Run from terminal: mvn clean verify