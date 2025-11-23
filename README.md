# Github Profile
Simple Android application to search for Github users and view their profiles.

<img width="240" alt="image" src="https://github.com/user-attachments/assets/37534a2c-dd41-4206-9ff5-819fecf774b2" /> <img width="240" alt="image" src="https://github.com/user-attachments/assets/d44a70a5-6a59-4756-bc18-9792bc4f34de" /> <img width="240" alt="image" src="https://github.com/user-attachments/assets/6d9dcf80-210d-4fa9-99d3-debd508ed149" />



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
- **JUnit** : unit testing

## Build
Use the default `debug` build variant and simple build and deploy the application using the `app` configuration.

## Future Improvements
For a quick delivery, I did not go too deep with some of the features. If I had more time, I would have also added the following features:
- **Paging for the search results** : For search queries that may return many github users, I would like to page the results with window size of about 20 and add a progress spinner at the bottom of the list when loading more.
- **Colour theme** : I used the default colour theme for this project, but I'm not really a fan of the current one. I would love to change to a new colour combination if I had more time.
- **Instrumented UI Testing** : I thought it would be too much to include this given my time window. This is maybe something to consider.
