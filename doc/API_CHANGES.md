# API Changes 
## Slogo Team 14
### Controller
* Controller API
    * Removed getTurtleHeading()
        * Duplicate method
    * Removed clearScreen() 
        * Done automatically by Visualization upon checking for updated state of the turtle, its lines, the variables, etc.
    * Removed updateViewTurtlePosition()
        * Done automatically by Visualization
    * Removed updateTrails()
        * Done automatically by Visualization
    * Changed getVariable() return type to UserVariable 
    * Added Controller.setLanguage()
    * Added Controller.getLanguage()
    * Added throws Exception clause to parseCode()
        * Necessary for error checking 
    * Added String parameter to parseCode()
        * Assumes command being passed from Visualization is of type String
    * Removed Controller.toggleVisibility()
        * Done automatically by Visualization 
    * Added ViewTurtle.changePenSize()
    * Added ViewTurtle.getPenStatusProperty()
    *Added ViewTurtle.getPenSizeProperty()
    *Added ViewTurtle.getPenSize();
    *Added ViewTurtle.getPenColorIndex();
    *Added ViewTurtle.setPenColorIndex();
    *Added ViewTurtle.setImageWithIndex();
        *These are major changes as there are a significant addition here.
        However, it is a major improvement. With the basic specification, pen color,
        turtle image and pen size were all confined to the front-end and the user
        could not use code to change those properties. So, we kept it in our front-end internal
        API. However, once the complete specification came out, we realized we needed
        to make this information accessible and connected to the back-end, so we added to our API.
        





