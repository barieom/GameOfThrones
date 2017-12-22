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