
# Unit 1 - Challenge
### Collections type justification
In this project I decided to use a `HashMap` for the storage of tickets fairly early on due to the nature of my ticket ID generation method and my concerns about searching. If I were using sequentially issued ticket ID's I feel as though a `ArrayList` would have been sufficient, but since I'm going with randomly generated one's more akin to a real world system, i felt the look up would be slightly faster using the `HashMap`. The only downside to this implementation that I personally see is due to some of my design decisions there is a duplicate set of `int`s in memory for the key of the map as well as the one's actually stored in the ticket itself, though this is trivial unless the system were to be scaled up massively.
### UML diagram
![uml diagram](https://raw.githubusercontent.com/lsko-dev/adv-java-unit-one-challenge/master/uml.png)
