This project implements AndroidClean architecture(http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/). 
The objective is the separation of concers and facilitating testing. The task is to fetch movie events in Finnkino and display them in the app.

There are three modules:

1.domain: A Java module without Android dependencies.
-Application business rules
-All use cases(interactors)
-JUnit plus mockito for unit tests

2.data: An Android module from where all the data is retrieved. Finnkino Api is chosen in this module(http://www.finnkino.fi/XML) 
-Deliver data needed by app
-JUnit plus mockito for unit tests

3.app: An android module that represents the presentation layer.
-Presentation Layer
-Espresso for functional testing.
Note: NowShowingInTheatresFragment is implemented in MVP fashion

Libraries used in the project:
Dagger2 - Facilitate dependency injections and testing.
Rxjava - Faciliitate async operations and apply reactive programming
Picasso - Image download and display, there are some alternatives such as Glide and Fresco. Picasso is the easiest one to use.
Retrofit - HTTP client
LeakCanary - Memory leak detection
Retrolambda - Support Lambda Expressions
Note: please install jdk 8 before building

Mosby - Android MVP library. The intention of using this library is to facilicate implemnting Loading-Content-Error in Fragment.
Usually a Fragment is doing the same thing over and over again. It loads data in background, display a loading view (i.e ProgressBar) while loading, displays the loaded data on screen or displays an error view if loading failed.