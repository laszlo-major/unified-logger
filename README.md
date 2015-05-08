# Unified Logger

A logging framework that wraps around SLF4J and Crashlytics (Fabric.io)

## Download
* ###If you are including including the library off jCenter:
```
    repositories {
        jcenter()
    }
    ...
    compile(group: 'unified-logger-public', name: 'unified-logger', version: 'versionNumber', ext: 'aar') {
        transitive = true;
    }
```

* ###If you want to include the library locally:
* Download the aar from GitHub, or clone the project and build it yourself
* Copy the aar into your project, for example the 'libs' folder
* Add the folder (that you copied the aar into) in your repositories. For example, if you have added Fabric.io to your project and copied the aar in libs it would look like:
```
repositories {
    maven { url 'https://maven.fabric.io/public' }
    flatDir {
        dirs 'libs'
    }
}
```
* Add the dependency itself:
```
compile name: 'unified-logger', ext: 'aar'
```
* Add the dependencies of the library:
```
    compile 'org.slf4j:slf4j-api:1.7.10'
    compile 'com.github.tony19:logback-android-core:1.1.1-3'
    compile 'com.github.tony19:logback-android-classic:1.1.1-3'
```


##Permissions
* Unified-Logger does **not** ask for **any** permissions by itself.
* Crashlytics requires the INTERNET permission to be able to upload reports, and it will automatically add it to the project during the auto-configuration of the project.
* WRITE_EXTERNAL_STORAGE would be needed if you wanted to write logs on the SD card, like the demo application does.

## Configuration
* You need to configure the SLF4J with a configuration file **Logback.xml** that goes in the /assets folder. An example configuration file is provided in the sample project, and more detailed explanation is provided at [logback-android]
* Fabric.io has to be added to the project, as every project must acquire it's own unique API key for Crashlytics to work. This can be done easily via the [Fabric.io] Android Studio plugin.

## Usage

Usage is very similar to how you'd use SLF4J, while the API is mirrored from android's own logger.
* Instantiate the logger for **each** class in a **private static final** field as such:
```
private static final UnifiedLogger LOG = UnifiedLogger.getUnifiedLogger(YourClass.class);
```
* Use it freely
```
LOG.d(TAG, "your message", true);
```

This will log a message at the DEBUG priority level into both SLF4J and Crashlytics. Logs that were passed into Crashlytics (with true) will accompany the crash report that you get on the site, if there's been a crash.


[logback-android]:https://github.com/tony19/logback-android
[Fabric.io]:https://get.fabric.io/
