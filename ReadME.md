# Monitoring-System-API

| HTTP Mehtod | API URL        | Description                                                                | Success Response                                 | Failure Response  |
|-------------|----------------|----------------------------------------------------------------------------|--------------------------------------------------|-------------------|
| POST        | "api/icu"      | This adds a new ICU to the system, adds a new ICU to nursing_station Table | 201 status code  URI response = "/api/icu/"+ id  | 400 - Bad Request |
| GET         | "api/icu"      | Fetches list of all ICUs in the system(from nursing_station)               | List of all ICU or Empty List                    | --                |
| GET         | "api/icu/{id}" | Fetches an ICU with the stationID = "id"                                   | 200 'OK' NursingStation Object in response Body  | 404 Not Found     |
| DELETE      | "api/icu/{id}  | Deletes specified record                                                   | 204 No Content                                   | 404 Not Found     |



http://localhost:8080/api/icu/6 recursively fetches all icu -> beds - >icu ....

@RequestMapping(value = "/api/icu/{id}/beds", method = RequestMethod.GET) this also doesnt work

see this first 	@RequestMapping(value="/api/publishers", method=RequestMethod.GET)

{"isAvailable": false,
"station":
{		"stationId": 6,
        "name": "general-ward",
        "level": 2,
        "bedCapacity": 15
        
}
}


{"isAvailable": true,
"station":{
"stationId": 5,
        "name": "childrem-ward",
        "level": 2,
        "bedCapacity": 15
}}


{
	"name": "succhi",
	"age": "14",
	"phoneNumber": "912344325678",
	"bed": {
		"bedId":2,
        "isAvailable": true,
        "station": {
            "stationId": 5,
            "name": "childrem-ward",
            "level": 2,
            "bedCapacity": 15
        }
	}
}

1. General logic -> input validation should be done by browser and not by server= client side validation


<plugin>
<groupId>org.jacoco</groupId>
<artifactId>jacoco-maven-plugin</artifactId>
<executions>
<execution>
<id>prepare-agent</id>
<goals>
<goal>prepare-agent</goal>
</goals>
</execution>
<execution>
<id>report</id>
<phase>prepare-package</phase>
<goals>
<goal>report</goal>
</goals>
</execution>
</executions>
</plugin>
mvn clean jacoco:prepare-agent install jacoco:report 
 

then go to jacoco in target>site>jacoco then Alt+Shift+R to see the folder and then open the index file there to see coverage


ADD BED last branch coverage
ADD patient occupied bed

mvn clean jacoco:prepare-agent install jacoco:reports# CaseStudy-Phase2
