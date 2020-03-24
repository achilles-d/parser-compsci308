parser
====

This project implements a development environment that helps users write SLogo programs.

Names: Saurav Sanjay, Achintya Kumar, Abebe Amare, Achilles Dabrowski 


### Timeline

Start Date: 2/18/20

Finish Date: 3/7/20

Hours Spent: 100+

### Primary Roles

Saurav: Worked on the front-end primarily. Designed the different window components and components 
for the front-end and styled all of them. Also helped integrate front-end with back-end
by doing some work with controllers.

Achintya: Worked on the back-end primarily and a little on the controller. Worked
to create a majority of the commands and the helped think out the logic for the
parser. In the controller, worked on the TurtleController. 

Abebe: Worked primarily on the back-end. Responsible for the parsing and
a few of the commands. Created all the classes for the parser and wrote
a majority of the code to handle parsing.

Achilles: Worked primarily on the controller package, exception classes and exception handling, 
and resource files. 

### Resources Used
Saurav: Used example code from Labs and Professor Duvall's lectures to learn about
more javaFX properties. Also used more online resources to learn about bindings and properties,
like this [website](https://www.dummies.com/programming/java/javafx-binding-properties).
Also used this [website](https://stackoverflow.com/questions/25570803/image-in-javafx-listview) to
figure out how to override default list cell functionality for my palette view. 

Achintya: Used code examples from Professor Duvall to implement generics when
needed. The idea for the parsing mechanism with the double stacks (that represent
a tree structure) came from Professor Duvall's lectures. Reflection code was
referenced from labs as well as how to get data from property files. 

Achilles: used my work from lab_advanced, particularly the BetFactory task to guide my implementation
of the InvalidCommandException and ExecutionException error message properties file reader in their 
constructors. 

Abebe: Used notes from class, lab materials, reading and Tutorial point
for doing reflection. I also used the weekly journals I wrote to revise
the concepts we learned in class.
### Running the Program

Main class: Main.java is the main class needed to run the program. 

Data files needed: 
- User needs to choose a configuration file in beginning (\src\resources\configuration\)

These files are needed regardless of what configuration file is chosen
- parameternames.properties
- parameters.properties
- UIText.properties
- uistyle.css
- ExceptionTypes.properties
- ExecutionExceptionText.properties
- InvalidCommandExceptionText.properties
- All language property files
- HelpWindowText.txt

Features implemented:
Front-End:
- Can create multiple workspaces
- Can load and save code files
- Can load different configurations for workspace
    - Rearranges UI elements
    - Changes image and color palettes
- Palette of images and colors 
- Pane of current state of turtle and pen
- Available Commands Window
- Variables Window
    - Can click on variables to edit their values
- Command history window
    - Can click on commands to execute them
- Buttons to move turtle graphically without coding
- Button text for commands automatically change when language is changed
- Can change pen properties graphically
- Can click on turtle to make inactive
- Toolbar to set background, pen color, turtle image
- Can see help screen for commands tutorial

Back-End:
- All basic commands
    - Movement 
    - Math operations
    - Control flow (if, if else)
    - Boolean commands
    - Turtle queries
    - Loops (for, repeat)
    - User defined commands
- Extended commands
    - Frontend controls (set background, pen color, palette, etc)
    - Multiple turtles are implemented. Clicking on them can make some active
    or inactive.

### Notes/Assumptions

Assumptions or Simplifications:
- Saving a code file will not just save console text, rather, it will save all commands
executed in the history for that workspace
- The commands should be separated by space.


Interesting data files:
- Dragon.logo
- Square.logo
- Pinwheel.logo

Known Bugs:
- If the turtle moves forward large amounts, like from one end of the screen to the other,
there are sometimes errors in preventing the turtle from moving out of bounds.
This can happen for example if a large distance is moved and it hits a side wall at an angle,
then the x coordinate will be limited to be in bounds, but the y-coordinate is not adjusted accordingly.
It is just kept constant, so it looks like the turtle just moved horizontally.
- Some of the procedures with multiple parameters are a little buggy. They do
not parse correctly but don't cause the program to crash.
- One bug with multiple turtles is that you have to say tell first individually
for the turtles.
- Also with the tell command passing in just one argument like 2 will move two turtles.
- Additionally, the error handling for writing and invalid command is bugged.
It ends up crashing the program.

- commands setbackground, setpc, and setshape don't work 

Extra credit:
- The buttons for moving the turtle will change automatically to match the chosen language


### Impressions
Overall, this was a very interesting project to work on as it really 
emphasized the importance of API design so that the front-end and back-end can 
communicate with each other in the code without too much hassle.


