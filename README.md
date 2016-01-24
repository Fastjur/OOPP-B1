# MindMatch
Delft University of Technology
Computer Sciences and Engineering Bachelor 2015-2016, Group **B1-1**
**O**bject **O**riented **P**rogramming **P**roject 


## Information regarding developers

### 1. Cloning the repository
  * **This repository includes IntelliJ files!**
    Clone it to a location where you would like your files to reside during your work on this project
  * This folder should be **opened** in IntelliJ, do not *create* or *import* a New Project in IntelliJ.
    To do this, navigate to `File --> Open...` OR click `Open` in the IntelliJ Splash Screen
  * There should now be 3 separate modules visible in your project named `client`, `server` and `shared`
  
### 2. Running the Server
  There are 2 options available for running the server
  
  1. Using IntelliJ
    * Navigate to `server\src` in IntelliJ, right click `Server` and choose `Run 'Server.main()'`
      This will also create a configuration in IntelliJ, so the server can now be started in the top right dropdown menu in IntelliJ
  2. Using `StartServer.bat` in the root folder
    * Make sure you have compiled *at least* `server` and `shared`
      Do this by right clicking on the respective Module in IntelliJ and click `Compile Module ...`
    * Double click the batch file, the server will now open inside a Command Prompt window
    * **NOTE:** Whenever changes are made to files in `server` or `shared` they should be recompiled! Otherwise the batch file will be running old files.

Link to database file: https://drive.google.com/file/d/0Bwrri4JoN_aIVC01OWF2U054TVU/view?usp=sharing
