# About

This is a small project created for fun. 

I wanted to create a thermal map of a hovering mouse over a blank canvas. 

This project was created using:  
- JavaFx
- SceneBuilder

In the current implementation, the color of the screen under the mouse changes every 5 ms based on the length of time 
the mouse is spent hovering on one position of the screen. The color gradient ranges from blue to green, to yellow, to 
red. The initial color change between blue and dark green is subtle -- the lightest green shade blends in to the blue. 
This may be updated in a later commit to be more distinct.

For a future implementation, I am considering a different algorithm to change the screen color depending on the total 
amount of time (in ms) pixels have been visited vs the duration the mouse has been sitting in a particular spot. 