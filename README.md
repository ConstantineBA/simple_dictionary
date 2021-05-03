# Simple dictionary app

### Used libraries
RxJava - for work with multithreading
Hilt - for realisation DI pattern
Gson - for work with Json
Glide - for download image from network
FastAdapter - for easy work with RecyclerView
Navigation - for organization navigation in app

### Code convention
I used Jetbrains [coding conventions](https://kotlinlang.org/docs/coding-conventions.html) with next naming improvements:
- add view name in id, example "errorImage", but allowed use "progress", "list", when use groups like LinearLayout, ConstarainLayout add Container,
- add paragraph after branches in when

### Tasks
In this progect I use [YouTrack](https://constantine.myjetbrains.com/youtrack/agiles/120-4/current).
Task has next fields:
- Type: epic, task, bug.
- Platform: android, iOS, back.
- Label (oprional): tech task, UI test,  Unit test.

### Work with git
Example naming branch in git ***feature/SD-7/search-detail***
project has 3 type branch feature for new functionals, fix for bugfix and improvement for test and other task like redesign or increment versions
Merge requests naming - ***[SD-7] Search detail***
New code splits on commits like ***[SD-7] Create UI.***

### Architecture
Application was build on MVI.
(https://github.com/ConstantineBA/simple_dictionary/blob/develop/image/Architecture.png)

### Design
For create simple and minimalistic [design](https://www.figma.com/file/iVli7YVU4XsqzkvfGzod8t/Simple-dictionary?node-id=0%3A1) i used component from material design.
