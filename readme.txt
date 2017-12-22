README
Name: Barry Eom
UTLN: beom01
Date: 11/17/2017

Comp86 | Assignment 5


Overview:

This program is a more developed version of a Game of Thrones game. 
Containing a main canvas window, we can see a map of Westeros, 
the central land of conflict in the famous novel series and show, Game of 
Thrones. Playing as one of the competitors fighting for a spot on the Throne,
the player has the ability deploy units (Ravens or troop battalion
represented by a sword or Daenyrius Targaryan's three dragons). There are 
several added features in this version of the game:
  1. There is a new map on the screen. It is, without a doubt, the most
     aesthetically pleasing map out there. Feel free to explore the map with 
     your ravens
  2. There exists a border around the map of the known world, such that trying
     to leave it will destroy the troops
  3. Collisions - clashing between troops will deal damage to the collided
     troops, afterall this is Game of Thrones; there should only be one winner
  4. White Walkers - 666 white walkers are slowing creeping down south of 
     Westeros. Crashing into the White Walker with the troops will deal damage
     to each White Walker
  5. InfoPanel - prints the status of Westeros; number of total troops 
     remaining in Westeros, number of White walkers remaining, and days passed
     since the inception of the game
  6. Zoom - allows for zooming in and zooming out
     **ALERT**
          After much experimentation and deliberation, I realized that 
          adjusting for the scale of the map during zoom is convoluted to
          implement, as it would require converting the regular polygon
          into polygon that takes in doubles. As such, I've implemented a
          zoom that zooms in and out of the icons as well. In terms of the
          gameplay of this game, not keeping the size of the icons 
          constant does not take away from enjoyability of the game as well.
  7. Mouse picking - player can click on the troop to select the troop


Gameplay:

The goal of Game of Thrones is simple: get to the throne. However, much 
disregarded side of the Westerosian feud is the threat of the White Walkers. 
As the White Walker creeps down, your job is to prevent them from getting all
the way past King's Landing and taking over the entirety of Westeros. The 
urgent goal of this version of the game is to prevent White Walkers from doing
so. One can do so by deploying and controlling all the troops - ravens, 
battalions, and Daenyrius Targaryan's three dragons - using the 2 dimensional
grid that allows the selected modules to be moved accordingly. The list on 
the right of the map is the list of all the available troops. The selected 
module can be selected using the list or directly clicking on the desired
module of selection represented on the map. One can add more troops as 
reinforcement later on. The added troops will be spawned at random locations 
throughout Westeros, representing garrison troops from different lords across
Westeros. The health/power breakdown of the modules are as follows:
  - White Walker: health 2
  - Raven: health 1
  - Battalion troop: 5
  - Dragon: 100
Collision between any of the modules will lead to damage (health -= 1) being
dealt to the two colliding modules, whether it be between White Walker and a
Raven or Dragon and a battalion troop. Collision between the same type (e.g.
raven and raven) does not deal any damage. Going down to health 0 will 'kill' 
the module with the module being removed from the game.
For now, the game is structured so that destroying all the White Walkers is
defined as winning the game. In contrast, if the white walkers get past King's
Landing, the game is also over. Another interesting aspect of the game is the 
detailed map of the Known World. The player is free to explore the map.


Architecture:

Main:
      Map and the buttons are instantiated in this class, displaying the 
      window.

Map:
      Draws the diagram modules onto the map (canvas) illustrating the 
      battlefield of Westeros

Raven:
      Abstract class which contains all the diagram's information. Some of the
      information stored are position, velocity, and color.

ControlPanel:
      Adds padding and a centered border for all the buttons

RavenL:
      RavenL is a short abbreviation for List of Ravens. This stores all the
      diagram modules.

PrintButton:
      A class that prints out which button is pressed

VelocityPanel & VelocitySelector:
      Changes the velocity of the plane; additionally, VelocitySelector is in
      charge of allowing the user to move around a 2D plain.

BackGroundColorButton:
      Changes the color of the background of the battlefield.

RavenDiagram & Battalion & Dragon:
      Class that holds the polygons that visually represents the self-described
      modules.

WhiteWalker:
      class that holds polygon that visually represents a White Walker. 
      Overrides the tick() function in Raven, allowing the White Walker to move
      freely downwards

BorderBound:
      class that holds the border/boundaries of the game. Hitting this class 
      with any of the troops will destroy the troop

AddModuleButton & DeleteModuleButton:
      A button class that allow for unit of troops to be added or deleted. It 
      does so by removing or adding the plane to RavenL and from the map 

NewModule:
      A class that opens a frame containing the add and subtract buttons. Add' 
      button creates and deploys a unit at the given coordinates.

StopButton:
      A button which pauses or continues the game by pausing the main timer of
      the program. This class causes the BackGroundColorButton to be disabled
      when the gameplay is frozen.

ZButton:
      A button that allows the scale of the map (canvas) to be adjusted, acting
      as a zoom in and a zoom out button

InfoPanel:
      a panel that prints the status of Westeros to the side


Aggregation Hierarchy:

-- Main 
        -- Map
                -- RavenDiagram
                -- Battalion
                -- Dragon
                -- WhiteWalker
                -- BorderBound
        -- InfoPanel
        -- ControlPanel
                -- VelocityPanel
                        -- VelocitySelector

                -- RavenL
                -- PrintButton
                -- AddModuleButton
                        -- NewModule
                -- DeleteModuleButton
                -- StopButton
                -- BackGroundColorButton
                -- ZButton

Inheritance Hierarchy:

-- JPanel
        -- Map
        -- ControlPanel
        -- VelocityPanel
        -- VelocitySelector
        -- InfoPanel
-- Raven
        -- RavenDiagram
        -- Battalion
        -- Dragon
        -- WhiteWalker
        -- BorderBound
-- JButton
        -- PrintButton
        -- StopButton
        -- AddModuleButton
        -- DeleteModuleButton
        -- BackGroundColorButton
        -- ZButton
-- JFrame
        -- Main
        -- NewModule
-- JScrollPlane
        -- RavenL


Collaboration Relationships:

Map -- Raven
Map -- RavenL
Map -- BackGroundColorButton
Map -- AddModuleButton
Map -- DeleteModuleButton
Map -- VelocityPanel
Map -- ZButton
Main -- StopButton
RavenL -- AddModuleButton
RavenL -- DeleteModuleButton
VelocityPanel -- Selector2D

Encapsulation:

Map is the only class that has access to the diagrams (i.e. type of Ravens).
Additionally, all other classes works through the Map. VelocityPanel interacts
with the Map to specify where each of the diagram moves within the map. 
ZButton, standing for "ZoomButton", only changes the scale contained within
the Map class. Map class, overall, does a good job keeping most of its secrets
hidden from the other classes. Information that needs to reached within the 
map can be accessed using the other get functions, which I added only if they
were needed.

All the information, e.g. inner workings, of the raven is contained within the
Raven class.

AddModuleButton is the only class that modifies and controls information in
NewModule window - i.e. it is the only class that interacts with NewModule

RavenL, which is the list of all the diagrams, controls all the 
representations of the ravens in the list through methods contained in RavenL. 
Additionally, DeleteModuleButton and AddModuleButton interacts with RavenL to 
modify the list.

