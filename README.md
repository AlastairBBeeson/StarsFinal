# StarsFinal
Running the Code:
I did most of my testing inside Intellij using its run/terminal feature. I would select Run main.main() by right clicking Main.java or the green play button at the top of the IDE. If there are any issues I am more than happy to do a zoom walkthrough or film a quick demo. 

Design Decisions:
I have three modular or reusable pieces of code. My REPL, CSVparser and CommandManager are all reusable. In the case of the REPL, the REPL constructor implements a CommandManager class to handle project specific commands while keeping the REPL modular and customizable. I then specifically define a StarsCommandManager that takes in the CommandManager and registers specific stars commands to the CommandManager which is then ingested  by the REPL. In other projects this StarsCommandManager could instead be changed to TBNCommandManager or GoogleCommandManager without needing to change the core parts of the REPL. Likewise, you can have multiple of these specialized CommandManagers together to implement commands from different projects/areas of a project while keeping things organized in their separate files. This would enable one to run TBN, Stars or Google commands from one REPL. 

The CSVparser is a pretty straightforward class and method that ingests file data and stores the read data as a CSVparser object. It uses a filereader then a bufferedreader. It stores the read data in the CSVparser object as an ArrayList<String[]>
. I feel this is by far the best way to store CSV data since Java has methods like ParseInt, ParseDouble to convert data stored as a String to other variables. There is an accessor method to get the data which is returned as an ArrayList<String[]>.  

CommandManager is a modular class for handling commands. The CommandManager object stores commands as a hashmap, allowing the manager to handle a large number of commands. There are methods to access the commands, register new commands to the CommandManager, and process a command inputted into the REPL (splitting it, verifying it exists, and passing along arguments), it also handles invalid commands or inadequate arguments. CommandManager defines an interface called Command. This is used for defining methods as commands that the CommandManager can interpret and execute. The interface has a mandatory method called execute which handles executing a specific action when a valid command is input into the REPL. The CommandManager itself starts with no commands and must have commands registered to it by project specific CommandManager/Commands files. 

To handle and implement Stars specific commands, I created the StarsCommandManager class. This class is where I define the StarsCommandManager object that ingests a CommandManager and registers its Stars specific commands to it. There is a method for importing specific stars commands to the StarsCommandManager, and defines stars specific commands and how they execute when the command is passed through the REPL. ImportCommands makes it incredibly easy to add new commands and potentially many more commands to the CommandManager. I also created a few variables necessary throughout the program including a hashmap needed for the radius name and neighbors name searches. I also have an ArrayList of Stars to store the output from the Stars method for usage with neighbors and radius methods. 

Otherwise much of the project's design is based on the parameters that we have discussed. In January you stated you wanted a project where “you could run a few Stars commands”. Obviously the Stars method has to work for the project to function which it does. I also targeted a naive implementation of radius and neighbors using an arraylist and both methods work well and perform the intended functionality. To look up specific star names I used the hashmap from StarsCommandManager. 

Suppose that in addition to the commands specified in Command Line/REPL Specification, you wanted to support 10+ more commands. How would you change your code - particularly your REPL parsing - to do this? Don't worry about specific algorithmic details; we're interested in the higher-level design.
I touched on this a bit in the StarsCommandManager paragraph but my current implementation is pretty effective at handling a lot of commands. There isn’t any need to change my REPL parsing at all. The hashmap of commands and process gives fast validation times. The only changes would need to be in StarsCommandManager or similar project specific CommandManager/Commands file. One would need to add the new commands to Import Commands, which has super simple syntax and also define specific commands/methods using the Command Interface and specific Execute() actions. Then when the REPL is instantiated all of those commands will be ingested by the REPL while still remaining modular. Likewise this allows the REPL to ingest from multiple command managers at once with potentially hundreds of commands from programs with completely different functionality. Likewise this allows for the creation of larger scale software projects that may need specific commands for aspects of the project like LoginCommands(login, register, forgotpassword) or MailCommands(send, forward, reply, delete)

Testing and Error:

My Error handling is pretty robust. I avoided aggressive error handling like System.exit(1) and instead tried to keep the user in the REPL. Much of my error testing and finding was conducted through brute force attempts in my REPL to break my code or find areas of weakness. 

I also used the terminal for other testing purposes to ensure proper output. For instance, I used the terminal to ensure my stars output matched the basic system tests and a few others I came up with. 

For automated unit tests I also employ JUnit tests. These similarly tackle the system tests and a few others. Most of these were written once I already felt the project was in a strong state. That said, making these tests was useful as it challenged me to intake console data, modify it and turn it into a string for comparison which I’m embarrassed to admit I didn’t know how to do before.  

For my checkstyle I employed Google’s checks. 