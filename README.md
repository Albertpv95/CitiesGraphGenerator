# README

### Cities Graph Generator
#### Version 1.0

## Introduction

This project allows any user to get basic information of any existing city 
and calculate routes by car between two of them using the Google Maps API Web Services.
 
The interaction with this software can be done using the provided graphic interface
implemented in Swing.

## Purpose of the project

This software has been done with the objective to create a simple and attractive 
graph model of cities and routes. The "base model" generated with this tool can be used 
for academic purposes in Algorithms or Data Structure subjects. For example, to request
the students the implementation of a graph of cities in any language as well as the 
implementation of different Combinatorial algorithms to find the most optimal route
between two given cities.

Also, the project may be helpful for those who are not familiar with JSON, MVC pattern
or accessing Web Services.

## Data Model

As explained above, you can get information of cities as well as connect them 
by car. This data can be imported and/or exported in JSON format. An example is 
provided below:

```json
{
  "cities": [
    {
      "name": "Barcelona",
      "address": "Barcelona, Spain",
      "country": "ES",
      "latitude": 41.3850639,
      "longitude": 2.1734035
    },
    {
      "name": "Madrid",
      "address": "Madrid, Spain",
      "country": "ES",
      "latitude": 40.4167754,
      "longitude": -3.7037902
    },
    {
      "name": "Mataró",
      "address": "Mataró, Barcelona, Spain",
      "country": "ES",
      "latitude": 41.5381124,
      "longitude": 2.4447406
    }
  ],
  "connections": [
    {
      "from": "Barcelona",
      "to": "Madrid",
      "distance": 629423,
      "duration": 21960
    },
    {
      "from": "Barcelona",
      "to": "Mataró",
      "distance": 33997,
      "duration": 2334
    },
    {
      "from": "Madrid",
      "to": "Barcelona",
      "distance": 624634,
      "duration": 21498
    }
  ]
}
```

**Distance value** is expressed in meters and the **route duration** is expressed in seconds.

## GUI

A brief explanation of the GUI is described here. There are two main views,
the first one allows the user to add cities and the second one calculate 
routes between cities already searched and added to the system.

### Add city
![Add city panel](res/screen_add_city.png)

You can write the name of any city and, if a result is found, select the correct 
one (the name of the city can exist in different countries) and add it.

### Add route
![Add route panel](res/screen_add_route.png)

On the left panel you can select one city as origin, on the right one you are able
to choose all the cities to connect from the first one.

### File Menu

The menu allows the user to save the current data obtained as well as import
existing JSON files to the system. 

## Download


### Clone

Just write the command below on your terminal.
```bash
git clone https://github.com/Albertpv95/CitiesGraphGenerator.git
```

### Setup

To use this tool, you will need to set the API Key value you got on 
[Google Maps API get Key website](https://developers.google.com/maps/documentation/geocoding/get-api-key)
to the constant ```API_KEY```. This constant is defined inside ```WSGoogleMaps``` 
class (path: ```src/main/java/Network/WSGoogleMaps```). 

After that, you are ready to run the project and use it!