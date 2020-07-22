Try-This 12-1 from Herbert Schildt's Java: A Beginner's Guide.

A simulation of a traffic light that uses an enumeration to describe the light's color 

Below is the portion that has been left as an exercise (Chapter 12 self test):

The traffic light simulation can be improved with a few simple changes that take advantage of an enumeration’s class features.

In the version shown, the duration of each color was controlled by the TrafficLightSimulator class by hardcoding these values into the run() method.

Change this so that the duration of each color is stored by the constants in the TrafficLightColor enumeration.

To do this, you will need to add a constructor, a private instance variable, and a method called getDelay().

After making these changes, what improvements do you see? On your own, can you think of other improvements? 
(Hint: Try using ordinal values to switch light colors rather than relying on a switch statement.)




