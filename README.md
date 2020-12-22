#PersonInformation

To run this project using swagger, use url - http://localhost:8080/swagger-ui.html
- You will get all the list of operations 

#Add Person
POST -> http://localhost:8080/restapi/addPerson
TO Add the person, you have to pass JSON  data like -
	{
	  "firstName": "string",
	  "lastName": "string"
	}
	
#Count Person
GET  -> http://localhost:8080/restapi/countPerson
Will return total number of person added


#Delete Person
DELETE  -> http://localhost:8080/restapi/deletePerson/{id}
Will Delete the person based on id

#Update person
PUT -> http://localhost:8080/restapi/editPerson/{id}
will update the person details 
need to pass person id as parameter 
and person detail as body like
{
  "firstName": "string",   
  "lastName": "string"
}

#Get person
GET -> http://localhost:8080/restapi/getPerson/{id}
this return the person details based on id

#List of all person
GET -> http://localhost:8080/restapi/listPerson	
this will return all the person details 




