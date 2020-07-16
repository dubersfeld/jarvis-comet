# jarvis-comet
Java based animated demonstration of the Jarvis March algorithm applied to build the convex hull of a points distribution. The implementation follows the publisher/subscriber pattern and uses Spring WebFlux.

1. Demo description

I present here a Java based animated demonstration of the Jarvis March algorithm that is used to build the convex hull of a random points distribution.

Javascript is used for display only. Here are the implementation details:

A random points distribution is periodically created by a Spring WebFlux application named publisher that listens on port 9090.

The distribution is published as a Mono. Then the publisher enters a SLEEP mode for some seconds. 

A separate Spring WebFlux application named subscriber gets the Mono on port 9090 and extract the points distribution.

Then the Jarvis March algorithm is executed (Java side).

All intermediate results are saved as a collection that is sent to the browser as a JSON object using STOMP protocol.

The collection is used for animation (browser side). 

All candidates are connected in green to the current vertex. The convex hull itself is drawn in black.

This implementation does not use any explicit angle. No trigonometric functions are used. Instead a cross product is used for all angle comparisons.


2. Running the Demo

To launch the demo run the command `mvn springboot:run` in project directories publisher and subscriber. When the application has started open a browser and hit URL `localhost:8080`. Then click the Connect button. The animation begins a few seconds later.

Here are some screen shots that can be seen during the demo:

Animation step
![alt text](images/step.png "Animation step")

Animation completed
![alt text](images/convexHull.png "Animation completed")

For a step-by-step demonstration of the Jarvis March algorithm please visit these repository:

https://github.com/dubersfeld/jarvis-step

For a demonstration of the Graham Scan algorithm please visit these repositories:

https://github.com/dubersfeld/graham-step

https://github.com/dubersfeld/graham-anim

For a video demo follow this link:

https://youtu.be/HS-n_xGwoGs

Dominique Ubersfeld, Cachan, France

