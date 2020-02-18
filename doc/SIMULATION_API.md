##Simulation Discussion

Achilles Dabrowski (ajd66), Achintya Kumar (ask60), Saurav Sanjay (sks61), Abebe Amare(ama100)

**API We're Looking At: Simulation Team 6**

Methods that should be a part of External API:

- ControllerException constructors
- Class constructors should be public
- updateGrid()
- getCellStates()
- any public accessors methods should be part of external API

Methods that should be a part of Internal API:

- Protected methods should be internal, like 
    - updateCellState()
    - initializeCells()
    - inBounds()
    - findNeighborIndices()
    - makeGraph()
    

Methods that should not be a part of API: 

- connectEdgeIndices() this is a helper method for finding neighbors which is always called,
so should not be a part of API

**Designing New API**

External: Working on the front-end, you expect to be able to get information on cell states, grid styling, like
shapes and borders. You also expect to get parameter types and names so that you can set those dynamically on the 
simulation UI. Generally, this API will be used to transfer data between the front-end and back-end. 

Internal: You want to be able to findNeighborhoods, calculateCellStates, update back-end grid, and also set parameters
based on XML files. Generalel this API will be used to handle calculations and determining what kind of data you will send
to the front-end. 








