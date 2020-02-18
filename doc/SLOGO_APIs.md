##SLOGO API's

Achilles Dabrowski (ajd66), Achintya Kumar (ask60), Saurav Sanjay (sks61), Abebe Amare(ama100)

Back-End:

External: To separate the graphical interface and interpreter, and also
have them communicate with each other,  we will use a separate controller object that can communicate
between the two. So, this controller class should be public. We should be able to get
the turtle's position, pen status(whether up or down), a location of a trail on the screen, orientations
of objects(like turtle or drawings). We should also be able to get configuration parameters. We also need to 
get values for variables.

Internal: Needs to be able to parse commands, check for errors in the entered commands. It also needs
to be able to calculate any changes in variables, by performing any necessary mathematical operations. This parser
object will not be public because only the back-end will be using that internally. 

Front-End:

External: We will again have the controller communicating between front-end and back-end. 
Externally, we will tell the front-end to update graphics, which includes, setting a new turtle position,
adding any drawn trails(or removing), update variables window, and also update executed command history. We also
need to be able to set turtle orientation, and we also need to be able to update the command line window
with return values from any executed operations. We need to be able to tell the front-end to display an 
error message.

Internal: It should be able to recognize when a command is entered, and communicate with controller
when necessary. It should also be able to recognize if a user has changed any configuration parameters,
like pen color, size, turtle image, background color, and language. This can also configure a user-friendly
error message based on the type of error that was received. 