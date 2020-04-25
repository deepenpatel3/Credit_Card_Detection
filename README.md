## Credit card number verification and classification from different file formats in java.

#### Demo of program execution:
![Screen Shot 2020-04-23 at 8 53 10 PM](https://user-images.githubusercontent.com/52833369/80174212-d0a8a500-85a6-11ea-9172-bbf1befef240.png)

#### Testing:
• <strong>Implementation</strong>: I have utilized Chain of responsibility and Strategy pattern to solve this problem. To describe about strategy pattern, based on the input filename is entered, appropriate strategy is selected, and suitable file-reader object is created, and first concrete handler is called. In case of match, concrete handler returns the string with the correct card-type, otherwise the successor is invoked to process the request. 

• <strong>Testing</strong>: For testing, I have hard-coded the directory path (where input files are present) instead of taking input from terminal. Each test reads the output file which has been recently created and compares the card-type with the expected result to see if the test passed. 


#### Class diagram of the architecture:
![Screen Shot 2020-04-23 at 9 08 41 PM](https://user-images.githubusercontent.com/52833369/80174421-72c88d00-85a7-11ea-9321-6ad2d23de5a7.png)

