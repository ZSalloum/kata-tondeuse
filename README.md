# kata-tondeuse
Automation of mowers
## Overview
Java project to automate virtual mowers, working on a lawn.  
The aim is to use the coding best practices, such as SOLID principle and unit testing.  
An imput file is used to send commands to mowers in order to control their movdments.
 
 
## Functional specification 
The lawn is a grid, its size is given by the input file in the form of the coordinates of the top right corner NxM.  
The bottom left corner (origin) of the grid has the coordinates 0x0, and the top right corner has the coordinate NxM.

### File format
1st line contains the upper right corner of the grid as 2 numbers separated by a space ex: 5 5  

2nd line contains the coordinates of a mower and its direction separated by spaces ex: 1 3 E  
where x = 1, y = 3 and the direction is East.  
Available directions are N = North, W = West, S = South, E = East  

3rd line is a series of commmands to apply to the mower. Each command is a one-letter, there are no spaces between commands ex: ADAAGGA  
 where A tells the mower to move forward following its current direction  
 D tells the mower to turn right (in place, without changing coordinates)  
 G tells the mower to turn left (in place, without changing coordinates)  
 The 2nd and 3rd lines can be repeated as needed in order to represent more mowers  
 
**Important**: a mower that is unable to execute a command (ex going off the grid, or coordinate occupied by another mower) simply ignores it and continues to the next command.

PS mowers execute sequentially, each mower finishes its job before the next one starts 

## Architecture
The architecture is thought as a sequence of phases (or a workflow) as depicted in the following image.

![image](https://user-images.githubusercontent.com/403470/205603449-beead217-fa9f-47c4-aa6c-329ee700582d.png)

**Reading** : reads data from a source, such as file or database.  
**Parsing**: parses the data and converts them into a series of commands  
**Executing**: Executes these commands, into an environment containing lawn and mowers  
**Analysing**: Performs a series of result gathering to extract meaningful results from the execution, ex the position of the mowers, the exact path of each mower etc???  
**Outputing**: Sends the results of the analysis to an output medium like screen, file or other  

![image](https://user-images.githubusercontent.com/403470/205922013-50d75089-5b38-412d-b386-60dd8014402b.png)


### Modules
The project is divided into 2 modules:
- core: The core module contains all the interfaces that are implemented by the 'app' module.
- app: The app module is the module that implements interfaces defined in 'core' module.

### Folders Structure
The folders structure of the modules is almost identical, it shows where the components of the architecture are located:  
- models: contains structures for mower and lawn
- readers: contains classes to read data from sources such as files or databases
- parsers: contains classes to parse data coming from readers
- automation: defines commands and engine that executes the commands that are generated by the parsers
- analysis: defines classes that anaylse and exploit meaningful results
- output: defines classes to output results/analysis to screen or other

### Extensibility
The application supports extensibility by letting custom command be inserted into the engine.
A json file should be given to the CommandFactory.loadCustomCommandsMapping() that maps the name of the command with the cusom ICommand instance.
for example:
> {  
> 'CreateLawn':'sg.kata.mower.app.automation.commands.CreateGridLawnCommand',
> 'CreateMower':'sg.kata.mower.app.automation.commands.CreateMowerCommand',
> 'D':'sg.kata.mower.app.automation.commands.TurnRightCommand',  
> 'G':'sg.kata.mower.app.automation.commands.TurnLeftCommand',  
> 'A':'sg.kata.mower.app.automation.commands.ForwardCommand',  
> }

A good use case for the extensibility is when you need to use English commands such as R (right) L (left) F (forward) to control the mower, or when you need a completely new command (see Creating Custom Command).  
By default CommandFactory has a built-in mapping.

### Creating Custom Command
To create a new custom command, you must implement the ICommand interface.  
It is important to use one and only one constructor so that the engine can instantiate the command appropriately.

### Running the Program
**java -classpath** &lt;path-to-compiled-classes-and-other-jars&gt; **sg.kata.mower.app.Main** &lt;path-to-input-file&gt;
