# Github Profile
Simple Android application to search for Github users and view their profiles.



## Features
**Github Profile** allows its user to search for other Github users by simply entering their usernames. Selecting a user from the search results opens the selected user's details such as number of their public repositories, following and follower counts. Users can also visit their Github profiles on external browsers by clicking a button link provided.

## Development Environment
**Github profile** uses the Gradle build system and can be imported directly into Android Studio.

Make sure the run configuration to `app`.

<img width="242" height="41" alt="image" src="https://github.com/user-attachments/assets/264e550a-ca94-4dbe-839f-fd5360d762a2" />

The `debug (default)` build variant needs to be used to build and run.

<img width="561" height="111" alt="image" src="https://github.com/user-attachments/assets/a9a09fc8-be79-46d9-b1e5-5fad1edd9848" />

## Architecture
The architecture employed in this project is **MVVM (Model-View-ViewModel)** with a **Repository Pattern**, powered by **Hilt** for dependency injection. This architecture is chosen because it is highly reommended by Google for building robust, scalable and testable Android applications.
Other libraries I used were:
- **Coil Compose** : to lazy load and cache avatar images
- **Retrofit** : for type-safe HTTP client for Android
- **Coroutines** : for asynchronous tasks

## Build
Use the default `debug` build variant and simple build and deploy the application using the `app` configuration.
