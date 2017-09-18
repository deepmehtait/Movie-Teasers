Android based application which helps to view and find currently ongoing movies near by.

# Movie Teasers

**Movie Teasers** shows the latest movies currently playing in theaters. The app utilizes the Movie Database API to display images and basic information about these movies to the user.

Time spent: **12** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current movies** from the Movie Database API
* [x] Layout is optimized with the ViewHolder pattern and Recycler View with material cards.
* [x] For each movie displayed, user can see the following details:
  * [x] Title, Poster Image, Release date, Gener, Ratings (Portrait mode)
  * [x] Title, Backdrop Image, Overview, Release date, Gener, Ratings (Landscape mode)

The following **optional** features are implemented:

* [x] Display a nice default for each image during loading or failure to load images.

The following **bonus** features are implemented:

* [x] Allow user to view details of the movie including ratings and popularity within a separate activity or dialog fragment.
* [x] When viewing a popular movie (i.e. a movie voted for more than 5 stars) the video should show the full backdrop image as the layout.  Uses [Heterogenous ListViews](http://guides.codepath.com/android/Implementing-a-Heterogenous-ListView) or [Heterogenous RecyclerView](http://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView) to show different layouts.
* [x] Allow video trailers to be played in full-screen using the YouTubePlayerView.
    * [x] Overlay a play icon for videos that can be played.
    * [x] More popular movies should start a separate activity that plays the video immediately.
    * [x] Less popular videos rely on the detail page should show ratings and a YouTube preview.
* [x] Apply the popular [Butterknife annotation library](http://guides.codepath.com/android/Reducing-View-Boilerplate-with-Butterknife) to reduce boilerplate code.
* [x] Apply rounded corners for the poster or background images using [Picasso transformations](https://guides.codepath.com/android/Displaying-Images-with-the-Picasso-Library#other-transformations)
* [x] Replaced android-async-http network client with the popular [OkHttp](http://guides.codepath.com/android/Using-OkHttp) networking libraries.

The following **additional** features are implemented:

* [x] Dynamic color Palette in Detail View of movies based on movie poster color domincance to create rich material UI. Used `compile 'com.android.support:palette-v7:25.3.1'` for implementation.
* [x] Impmented connection staus snackbar for better user experience.

## Video Walkthrough


## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [Square okhttp3](http://square.github.io/okhttp/) - For better networking.
- [Butterknife](http://jakewharton.github.io/butterknife/) - Field and method binding for Android views
- [Picasso Transformations](https://github.com/wasabeef/picasso-transformations) - Better transformation effects using Picasso Transformations.
- [YouTube Android Player](https://developers.google.com/youtube/android/player/downloads/) - Adding YouTube in app support.

## License

    Copyright [2017] [Deep Mehta]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
