# API Changes 
## Slogo Team 14
### Controller
* Controller API
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
    * Added Controller.setLanguage()
        * This was necessary so that the Parser and the rest of the backend could change the language 
        scheme by which it was parsing commands so that commands in another language could be used. 
    * Added Controller.getLanguage()
        * This was necessary so that LogoVisualization could display available commands in the correct
        active language. 
    * Added throws Exception clause to parseCode()
        * This was necessary so that error handling could be incorporated by the backend to handle 
        inevitable entered command syntax issues. 
    * Added String parameter to parseCode()
        * This was added to shield the implementation of commands in the backend from the perspective
        of the frontend. 
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
    * Added TurtleController
        * This was added as in the complete specification we needed to implement
        multiple turtles for the view. This controls getting front and backend
        turtles based on their indices and whether they're active or not. The
        team made the decision to do this.
    * Added Turtle Pair
        * This is a helper class for the TurtleController that binds a view
        turtle and backend turtle together so that updating one will change
        the values of the other automatically. The team made the decision to
        do this.
    
* Model
    * CommandFactory was added to create commands and match methods through
    reflection. 
    * CommandParser has many more methods so that the logic can split up.




