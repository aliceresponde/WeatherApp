# WeatherApp

# How to Use the App

- App requires internet connection
- create in the root of the project the file named api.properties and add the field
API_KEY="Wheather_appid"that is taken from https://openweathermap.org/api or user the key suggest in the assigment description.

# Branch ab/states: Has 2 screen the left load USA states, then the user can select any and get the current weather, or check the right tab to see the forecast.
             Here the weather and forecast, are persisted into room database, then when the user has not internet connection, the previous data will be displayed
             or a toast message that indicate no data able, possible internet error.

# Screens:
- Current : Display USA states and show selected state current weather
![Current](https://github.com/aliceresponde/WeatherApp/blob/ab/states/Demo/current_weather.png)
- Forecast: Display Forecast related to the selected state
![ForeCast](https://github.com/aliceresponde/WeatherApp/blob/ab/states/Demo/forecast_weather.png)



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
