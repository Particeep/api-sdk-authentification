**About**

All access to the Particeep API must be authenticated.
We use specific headers :

_DateTime_: the date of the request in the iso 8601 format. e.g: 2015-10-19T11:04:18Z

_Authorization_: a hash formed with the api keys and the DateTime.

This is an example of implementation in java, to build a authorization header.

**Usage**
Use the method buildAuthorizationHeader to get an accepted authorization header using your apikey and apisecret. 

Example:
Building a authorization header for :

_api_key_: "ce657875-613a-4636-ad53-a2f127f7811e"

_api_secret_: "1c61cb5a-294e-4085-845a-282dc479990d"

_date_time_: "2019-04-16T11:19:33Z"

`buildAuthorizationHeader("2019-04-16T11:19:33Z", "ce657875-613a-4636-ad53-a2f127f7811e", "1c61cb5a-294e-4085-845a-282dc479990d")`
 
 We get this string as result: 
 
**PTP:693fb0bc-5433-4b88-9c68-9f6ab024eb50:MjJiYTEwZTVmZTljZjMzNmE4MzlkMzE2Y2Y4OGIxZmEzNzY3Y2M2MA==**