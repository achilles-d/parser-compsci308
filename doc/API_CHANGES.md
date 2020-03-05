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





