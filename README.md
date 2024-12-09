# About

This is a small project created for fun. 

I wanted to create a thermal map of a hovering mouse over a blank canvas. 

This project was created using:  
- JavaFx
- SceneBuilder

The color of the screen changes depending on the total amount of time the mouse has spent on the screen. The color 
gradient ranges from blue to green, to yellow, to red. The initial color change between blue and green is subtle -- the 
lightest green shade blends in to the blue. This may be updated in a later commit to be more distinct.

The longer the mouse has spent on a certain part of the screen the redder the screen becomes. 

In the original implementation of this app, the color of the screen under the mouse changed every 50 ms based on the 
length of time the mouse spent hovering over (without moving) one section of the screen. This current implementation
changes the color not based on length of time idle on a spot, but total length of time the pixels have been visited. 
Adjacent pixels also change color in a lower intensity.  

I wanted the heat map to look something similar to the image in the `Eye Tracking Heat Map` section, here:
https://sitetuners.com/blog/do-heat-maps-really-work/

including the addition of a gradient radiating outwards like in the image above. 
In the future, I may add a webview on the screen with the translucent heat map rendering on top of the page.

Note: The gradient palette was selected from here: https://www.rapidtables.com/web/color/RGB_Color.html