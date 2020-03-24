# API Changes 

* **Changes I Chose To Do**
    * Removed getTurtleHeading()
        * Duplicate method. getHeading() already exists and has the exact same function (return the
        turtle's current heading.)
        
    * Removed clearScreen() 
        * Clearing the screen is done automatically by LogoVisualization because it automatically checks
        the state of each of the display elements' backend counterparts through ParserController's other
        methods, rendering the method clearScreen() unnecessary. 
        
    * Removed updateViewTurtlePosition()
        *  updating the ViewTurtle's position (frontend turtle object) on the display window 
        is done automatically by LogoVisualization because it automatically checks
        the state of each of the display elements' backend counterparts through ParserController's other
        methods, rendering the method updateViewTurtlePosition() unnecessary.
        
    * Removed updateTrails()
        *  updating the turtle's trails that appear on the display window 
        is done automatically by LogoVisualization because it automatically checks
        the state of each of the display elements' backend counterparts through ParserController's other
        methods, rendering the method updateTrails() unnecessary.
        
    * Changed getVariable() return type to UserVariable
        * This was changed from String to make it possible for LogoVisualization to view the both the 
        variable name and its value, which are both contained in Variable objects, 
        after only one method call to ParserController. 
 
    * Added Controller.getLanguage()
        * This was necessary so that LogoVisualization could display available commands in the correct
        active language. 

    * Removed Controller.toggleVisibility()
        * updating the turtle's visibility on the display window 
        is done automatically by LogoVisualization because it automatically checks
        the state of each of the display elements' backend counterparts through ParserController's other
        methods, rendering the method toggleVisibility() unnecessary. 
        
    * Added ViewTurtle.changePenSize()
    * Added ViewTurtle.getPenStatusProperty()
    * Added ViewTurtle.getPenSizeProperty()
    * Added ViewTurtle.getPenSize();
    * Added ViewTurtle.getPenColorIndex();
    * Added ViewTurtle.setPenColorIndex();
    * Added ViewTurtle.setImageWithIndex();
        * These are major changes as there are a significant addition here.
        However, it is a major improvement. With the basic specification, pen color,
        turtle image and pen size were all confined to the front-end and the user
        could not use code to change those properties. So, we kept it in our front-end internal
        API. However, once the complete specification came out, we realized we needed
        to make this information accessible and connected to the back-end, so we added to our API.
        
* **Changes Others Asked For**

    * ViewTurtle.getActiveProperty();
     * ViewTurtle.getPenColorProperty();
     * ViewTurtle.getShapeProperty();
     
        * These additions were needed so binding between the ViewTurtle
        and Turtle could occur in the TurtlePair object needed
        for the TurtleController
          





