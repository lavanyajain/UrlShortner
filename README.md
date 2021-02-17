##URL shortner service

DataBase:
* H2 DB 
* schema is found in resources/data.sql file 

This application has following services

### /api/create - POST 
converts long URL to short URL and this entry into h2 DB
This will allow unique entry encoding is hashcode of url object
Request Body:
```
{
	"url": "https://www.facebook.com"
}
```

Response:
```
{
    "status": "success",
    "code": "1721158175"
}
```

### /api/{url} - GET

This will fetch full URL based on {url} in path variable

```
localhost:8080/1721158175
```
Executing this in browser will redirect to long URL mapped to {url} path variable

### /api/history - GET

This will fetch full URL and it's access count

Response:
```
{
    "https://www.facebook.com": 0,
    "https://www.hackerrank.com": 1,
    "https://www.gmail.com": 2
}
```

### /api/entries - GET
This will fetch all URL's and their short URL entries added in DB

```
{
    "https://www.gmail.com": "944548821",
    "https://www.facebook.com": "1721158175",
    "https://www.hackerrank.com": "1963157357"
}
```