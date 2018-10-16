Team 25:
Riley Grice - Game World Package (main)
Daniel Miller - GUI Package (gui)
Victor Ong - Persistence Package (parser)
Kelvin Chen - Renderer Package (renderer)


All the XML Parsing files used in persistence should be kept in the project folder,
but not the src folder.

These include:

smallmap.xml
mediummap.xml
largemap.xml
PlayerStartData.xml
PlayerSavingData.xml
InventoryStartData.xml
InventorySaveData.xml
Dungeon Map Save.xml

The arrow images should also be placed inside a folder named "arrowImages"
located in the project folder, but not the src folder.

-------------------
Game Rules:

Single player game, you are the black box
Use arrow keys shown on the GUI (navigation panel)
When there are adjacent objects like door and chest, use actions buttons to interact
A yellow chest indicates it’s a closed chest, and red chest indicates that it’s opened
Brown box is door that requires a key that are from chests

The goal of the game is to navigate through the maze using keys that are collected from
the chests to reach the golden tile.

In the commits on GitLab, there are commits from 5 different sources but only four team members.
Two of them are from Riley and Daniel, two more are both from Kelvin, who somehow committed
from two different sources. The last one is from Vogog, which is Victor Ong's personal account.
This was already configured on the laptop he worked on, which is why there are no commits from
his ECS email address.
