# kata-tondeuse
Automation of mowers
## Overview
Java project to automate virtual mowers, working on a lawn.  
The aim is to use the coding best practices.  
An imput file contains commands to control the movdment of the mowers.
 
 
## Functional specification 
The lawn is a grid, its size is given by the input file in the form of the coordinates of the top right corner NxM.  
The bottom left corner (origin) of the grid has the coordinates 0x0, and the top right corner has the coordinate NxM.

### File format
1st line contains the upper right corner of the grid as 2 number separated by a space ex: 5 5  

2nd line contains the coordinates of a mower and its orientation separated by spaces ex: 1 3 E  
which means x = 1, y = 3 and the orientation is East.  
Available orientations are N = North, W = West, S = South, E = East  

3rd line is a series of commmands to apply to the mower. The commands are a series of one letter each without spaces ex: ADAAGGA  
 where A tells the mower to move forward following its current orientation  
 D tells the mower to turn right (in place, without changing coordinates)  
 G tells the mower to turn left (in place, without changing coordinates)  
 The 2nd and 3rd lines can be repeated as needed in order to represent more mowers  
 
**Important**: a mower that is unable to execute a command (ex going of the grid, or coordinate occupierd by another mower) simply ignores it and continues to the next command.

PS mowers execute sequentially, each mower finishes its job before the next one starts 

## Architecture
The architecture is be thought as a sequence of phases as depicted in the following image.

![image](https://user-images.githubusercontent.com/403470/205603449-beead217-fa9f-47c4-aa6c-329ee700582d.png)

**Reading** : reads data from a source, such as file or database.  
**Parsing**: parses the data and converts them into a series of commands  
**Executing**: Executes these commands, into an environment containing lawn and mowers  
**Analysing**: Performs a series of result gathering to extract meaningful results from the execution, ex the position of the mowers, the exact path of each mower etc…  
**Outputing**: Sends the results of the analysis to an output medium like screen, file or other  

### File Structure

