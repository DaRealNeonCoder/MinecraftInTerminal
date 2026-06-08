# MinecraftInTerminal
A version of Minecraft that runs entirely in command in the terminal.


## Instructions

This project will run as is on the VS code terminal. 
to get it to run on command prompt, follow these instructions:
1. add (new ProcessBuilder("cmd", "/c", "color").inheritIO().start().waitFor(); ) to the start of the main method, then compile Compile the file.
2. Open command prompt.
3. Set the window to full screen, then right click the command prompt window  and click properties.
4.	Go to layout and make the following changes: uncheck wrap Screen buffer size  width = 9000, window width = 860, height =  610
5. Execute the compiled file.

