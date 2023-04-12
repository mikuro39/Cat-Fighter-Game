# Cat Fighter Game

## The best game you will ever play.

Cat Fighter Game is a game where users will be able to 
collect cats with varying rarities. Each cat has
unique stats and users will be able to feed them, 
upgrade them during fights, and customize their names.
These cats are born to fight. Users will be able to
build up their cat fighter team to compete against
**bots** and use items in-round to buff their 
cats' stats. This game is geared towards those who
enjoy cats and fighter games who are looking for
a game to collect cute characters. I personally like 
cats and I want to create a game that has a similar
functionality to the *Pokémon* games as I grew up
playing them. 


## User Stories


- As a user, I want to be able to customize my cats by 
changing their name and size
- As a user, I want to view my cat inventory
- As a user, I want to be able to collect cats with different
rarities
- As a user, I want to be able to collect multiple cats
and add it to my inventory of cats
- As a user, I want to be able to use items to upgrade
my cats' stats during battles
- As a user, I want to be given the option to save 
my collection of my cats
- As a user, I want to be able to load my collection
of cats from file with the same stats if I want to

## Citations
- Description: Java Serialization Demo
- Author: Paul Carter
- Date Created: Oct 16, 2021
- Date Accessed: Mar 12, 2023
- URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

# Instructions for Grader
- You can generate the first required action related
to adding cats to a collection of cats by clicking the 
"Create new cat" button and entering in a name, then
pressing the button labelled "Done!". Then, press the
"view cat collection" button, and you will be able to
see that a cat has been added to the collection.
- You can generate the second required action
related to adding cats to a collection by clicking the
"view cat collection" button, then double-clicking on
any of the names in the list of cats. Then, click the
"delete cat" button. Then, press the "main menu" button.
Then, press the "view cat collection" button again
to verify that the cat has been deleted.
- You can locate my visual component by clicking the
  "Create new cat" button and entering in a name, then
  pressing the button labelled "Done!". the next screen
that shows up will include an image.
- You can save the state of my application by
clicking the "Save and exit" button.
- You can reload the state of my application by
clicking the "load cat collection" button.

# Phase 4: Task 2
Sat Apr 08 15:49:20 PDT 2023
added cat

Sat Apr 08 15:49:22 PDT 2023
added cat

Sat Apr 08 15:49:25 PDT 2023
added cat

Sat Apr 08 15:49:29 PDT 2023
removed cat

Sat Apr 08 15:49:32 PDT 2023
added cat

Sat Apr 08 15:49:34 PDT 2023
removed cat

Sat Apr 08 15:49:36 PDT 2023
removed cat

Sat Apr 08 15:49:36 PDT 2023
removed cat

# Phase 4: Task 3
Upon reflection of my UML diagram, I noticed that my Fight
class lacked cohesion, as it was 
responsible for both managing the cat collection and 
completing one round of the fight. These are two separate 
responsibilities and they should be placed in different 
classes. As I was working on the project and adding
more functionality to it, I continued to add
more methods to the Fight class, and as a result
it began to lose its cohesion. To address this, 
I would refactor my project by splitting the Fight class into
two separate classes - one for managing the cat collection
and another for the methods involved in the result of 
the fight. By doing so, I can improve the overall 
cohesion of my project by ensuring each class is 
responsible for a single task.


Although I intentionally included all UI elements in one 
class for both the console-based and graphical 
interfaces, I realized that splitting these 
functionalities into separate classes would be greatly
beneficial towards increasing cohesion. 
As I worked on the GUI, I created
many methods related to each aspect of the game, 
causing the MainMenu class to have a high level of 
coupling. I would refactor this class and split it 
into multiple classes, each with a different 
functionality, such as creating new cats, 
editing the cat collection, engaging in battle, and 
saving/loading the game. This would create a more 
cohesive program design, as each class would 
have a single responsibility and overall contribute to a
more organized program design.