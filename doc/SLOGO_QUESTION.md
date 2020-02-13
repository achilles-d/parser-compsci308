##SLogo High Level Design


1. When does parsing need to take place and what does it need to start properly?
    - Parsing takes place after a command is input(typing and then hit enter). It needs to check
    for any errors in the parsing and then be able to recognize commands in different languages to start properly. 
2. What is the result of parsing and who receives it?
    - The result of parsing is the command is executed and the front-end is told how to update itself.
    Specifically, where should the turtle, what kind of trail it leaves behind(color,size etc.), it also updates
    the command history of executed commands. It will always update state of variables being displayed. 
3. When are errors detected and how are they reported?
    - Errors are detected during parsing, and they are reported in a user-friendly on the front-end
    visual, so it will not just be on the console and the user will understand why the error was thrown. 
4. What do commands know, when do they know it, and how do they get it?
    - Commands know what they should do if executed, as in, they know what their parameters are and 
    what they do with them. They also know what kind of data to send to the front-end so it can update
    appropriately. It also needs to know current state, like turtle position, pen color, size, etc and it needs
    to know when it has been executed. It will get all this information once parsing occurs after the user
    has hit enter and errors are checked for. They get this information from some intermediary controller that 
    communicates between the front-end and back-end. 
5. How is the GUI updated after a command has completed execution?
    - So, the back-end will tell the front-end how to update itself based on what command was
    executed. For example, it will tell it where the turtle's new position will be, what it's heading is,
    if any trail was left behind, and also if any variables have changed for the variable window. It will
    also tell it to update the command history window to display the newly executed command. 