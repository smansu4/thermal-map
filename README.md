# Heat Map / Thermal Map

Project was created using:
- JavaFx
- SceneBuilder

This is a small project I created for fun. I wanted to create a thermal map of a hovering mouse over a blank canvas.

The color of the screen changes depending on the total amount of time the mouse has spent on the screen. The color 
gradient ranges from blue to green, to yellow, to red. The initial color change between blue and green is subtle -- the 
lightest green shade blends in to the blue. This may be updated in a later commit to be more distinct.

The longer the mouse has spent on a certain part of the screen the redder the screen becomes. 

In the original implementation of this app, the heat map was rendered over a blank canvas and the color of the screen 
under the mouse changed every 50 ms based on the length of time the mouse spent hovering over one section of the screen
(without moving). This current implementation renders the map over a webpage and changes the color not based on the
length of time spent idle on one spot, but based on the total length of time the pixels have been visited. Adjacent 
pixels also change color but in a lower intensity.  

I wanted the heat map to look something similar to an image on the webpage 
here: https://sitetuners.com/blog/do-heat-maps-really-work/ under the `Eye Tracking Heat Map` section. Similar to that 
image, I wanted to render a gradient of color radiating outwards.

#### Potential Future Updates:
- Enable mouse / trackpad scrolling of the webpage.
- Update the heatmap to scroll when the webpage scrolls 


Image of heat map on a blank canvas:
![screenshot of app recording](/src/main/resources/recordings/Image_of_thermal_app.png)

Image of what the heat map looks like on a webpage:
![screenshot of heat map on webpage](/src/main/resources/recordings/heat-map-on-webpage.png)

Image with latest color scheme:
![screenshot of heat map on webpage](/src/main/resources/recordings/heat-map-color2.png)

### Notes: 

A recording of the heat map against a blank canvas can be found in the `/resources/recordings` directory.

<b>Currently, the user cannot scroll the rendered webpage with a trackpad mouse. However, they can use the keyboard 
arrows to scroll instead.</b>

The gradient palette was selected from here: https://www.rapidtables.com/web/color/RGB_Color.html.
Also, I originally named the app `Temperature Map`, but ended up liking `Heat Map` better so used that naming convention 
within the code. 
