Mubarak Telegraph Solution

#Application Description 
This Spring Boot-based application provides weather data by integrating with the **Visual Crossing Weather API**

## Table of Contents
- [Key Classes](#introduction)
- [Code choices](#code-structure)
- [API Endpoints](#api-endpoints)


### Key Classes:
- **WeatherController**: The controller that handles incoming requests, such as comparing daylight hours, checking rain status, etc.
- **WeatherService**: The service layer that interacts with the Visual Crossing API to fetch the weather and daylight data.
- **CityInfo**: A model that holds the weather data for each city (e.g., sunrise, sunset, and current conditions).

## Code Choices
--HTTPS REQUESTS 
To handle HTTP requests, I used the @RestController annotation, which automatically serializes responses to JSON, 
and @RequestMapping("/weather") to set a base path for all weather-related endpoints. This makes my code cleaner and easier to maintain.
For dynamic inputs, I used @PathVariable for the city in the /forecast/{city} endpoint, and @RequestParam for the cities in the /compare-daylight 
and /rain-check endpoints. This approach allows users to pass data via URL easily and intuitively

-- AUTOWIRED
I used @Autowired to inject the WeatherService class. This keeps my controller clean and adheres to dependency injection, which decouples the logic, 
making the code more testable and maintainable.In the endpoints, I checked for null data to prevent errors when weather data isn’t available. 
This ensures my API returns meaningful error messages instead of crashing. For calculating daylight duration, I used the sunrise and sunset times,
 converting them into minutes for easy comparison. This approach keeps the calculation logic simple and focused. The method is reusable, and by separating the concerns, 
 I keep the controller’s role clear.In the rain check logic, I check if the weather conditions contain "rain" (case-insensitive) to determine if it’s raining.
This is a quick and effective way to identify rain in the forecast.

-- Good Practises 
I kept the methods descriptive like forecastByCity, compareDaylight, and checkRain to make the code self-explanatory. This helps with readability and maintainability.

-- Structure 
Finally, I structured the code with a service layer to handle data fetching, keeping the controller focused on handling requests and responses. This separation makes it easier to test and modify the logic without affecting other parts of the app.

## API ENDPOINTS
### 1. `GET /weather/forecast/{city}`

**Description**: This endpoint retrieves the weather forecast for a specific city.

#### Example Request:
```plaintext
GET http://localhost:8081/weather/rain-check?city1=Paris&city2=Berlin

#### Example RESPONSE: 
{
  "city1": "Paris",
  "city2": "Berlin",
  "rainStatus": "It is not raining in either city."
}


