# Readme

RSSReader is a concept application to show how to build a multi-screen enabled RSS feed reader with material design elements.

### Version
0.0.1

### Tech
RSSReader uses a number of open source projects to work properly:

* [ION] - Android Asynchronous Networking and Image Loading
* [Android-RSS-Reader-Library] - Awesome RSS parsing library developed by Mats Hofman. Why reinventing the wheel with something so error prone like parsing RSS when there's something simple and working out there.
* [JSoup] - HTML parser with capabilities like CSS selectors to pick the elements you need from an HTML document or string
* [Android support libraries] - Backward compatiblity with previous versions of Android.

### Building
You can fire up Android Studio and build the project as usual or open up a terminal and execute

```sh
$ gradlew assemble
```

License
----
Apache 2