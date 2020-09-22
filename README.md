# WeatherApp

# How to Use the App

- App requires internet connection
- create in the root of the project the file named api.properties and add the field
API_KEY="Wheather_appid"that is taken from https://openweathermap.org/api or user the key suggest in the assigment description.


# Screens
- Home : 
  ![Home Map](https://github.com/aliceresponde/WeatherApp/blob/master/Demo/home_portrait.png) 
  ![Delete Mark](https://github.com/aliceresponde/WeatherApp/blob/master/Demo/home_delete_marker.png)
 
  Its an split scree that has a map wirh markers inside that can be added when the user touch the map, or cancelled 
  when the user confirm action after press any marker in the map. The list of markers is selectable than when. you clicked on,  you can navigate to cityWeather screen
  and find weather data from place location (lat/longitude)
- CityWeather :
  ![weather](https://github.com/aliceresponde/WeatherApp/blob/master/Demo/weather.png)
  Has a Search Bar for search weather by location name, for example Bogota. When the user search a place,
  then the user will se the weather info for today and 7 days later
- Help :
  ![](https://github.com/aliceresponde/WeatherApp/blob/master/Demo/help_screen.png)
  Has a Webview with the instructions how to use the app
- Config :
  ![Config](https://github.com/aliceresponde/WeatherApp/blob/master/Demo/demo.gif)
  * Screen  where the user can select a measure system (Imperial, Metric) to get values from the server
  * User can delere all the markers


  ## DEMO VIDEO:
  ![Demo]()

  ## ARCHITECTURE
  ![Diagram](https://github.com/aliceresponde/CountingApp/blob/master/info/android_clean_repository_arch.png)

  ## Used Libraries
  * Android X Preference
  * Data Binding
  * Navigation Component
  * Room
  * Retrofit2
  * Coroutines
  * Flow
  * Glide
