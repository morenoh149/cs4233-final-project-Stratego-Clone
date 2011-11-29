Instructions for preparing your Strategy player for the final tournament.
Follow the steps below so you can submit your project in a form that can be
used for the final project deliverable.

1. Do not move or rename any of the files and packages in this project except as specified here.
   You must rename the strategy.playerxx package to hotgammon.playerxx, where "xx" is your initials. 
   If more than one work on this, you can rename the package appropriately, including all
   of your initials. This is important since the tournament manager will create
   an instance of your player through the reflection API. It will instantiate the
   class strategy.playerxx[..].StrategyPlayerImpl.
   
2. ALL of the classes that you add to the project must go into the package containing
   your StrategyPlayerImpl class or in packages beneath this package. You can 
   easily use Eclipse to Refactor>Move your delta package below your player package.
   
3. When your StrategyPlayerImpl constructor is called, you should create a DeltaStrategy
   game that your player will use to keep track of the state of the game. It should mirror
   the game that my TournamentDirector class will be using.
   
4. When you finish your implementation, you will create a JAR file that contains all of the 
   classes required for your player to function. All of these should be in the 
   strategy.playerxx package and its subpackages. You should NOT include any files
   in the strategy.common and strategy.tournament packages. If you just put the 
   strategy.playerxx[..] package and subpackages in the JAR file, you should have no problem.
   
You will submit this project, exported to a zipped archive AND your JAR file that will
have the name strategyxx[..].jar. If your JAR file is named strategyxx.jar, the
tournament manager will try to instantiate the class strategy.playerxx.StrategyPlayerImpl.