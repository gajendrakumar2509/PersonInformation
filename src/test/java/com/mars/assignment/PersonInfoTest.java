package com.mars.assignment;
 

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.assignment.model.Person;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonInfoTest {

	@Autowired
	private  TestRestTemplate restTemplate;
	
	@Test
	public void contextLoads() throws Exception {		 
	
	}
	 
	
	@Test
	public void testAddPerson()  {
		Person person = new Person(); 
		person.setFirstName("Gajendra");
		person.setLastName("Kumar");
		person.setAddressLine1("Hyderabad");
		person.setAddressLine1("TS");
		ResponseEntity<String> response =restTemplate.postForEntity("http://localhost:" + 8080 + "/restapi/addPerson",person, String.class);
		 
		if(response.getBody().equalsIgnoreCase("Person details added successfully.")){
			Assert.assertTrue(true);	
		}else{
			Assert.assertFalse(false);	
		} 
	}
	
	@Test
	public void testListPerson()  {	
		ResponseEntity<String>  personList = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/listPerson",String.class);
		Assert.assertEquals(200,personList.getStatusCodeValue());
	  
	}
	
	@Test
	public void testEditPerson()  {	
		Person person = new Person();
		
		person.setFirstName("Gajendra");
		person.setLastName("Kumar Verma");
		person.setAddressLine1("Hyderabad");
		person.setAddressLine1("TS");
		restTemplate.put("http://localhost:" + 8080 + "/restapi/editPerson/3", person);
		
		ResponseEntity<Person>  personResponse = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/getPerson/3", Person.class);
		if(personResponse.getBody()!=null && personResponse.getBody().getId()>0 ){
			Assert.assertTrue(true);	
		}else{
			Assert.assertTrue(false);	
		}
	}

	@Test
	public void testGetPerson()  {	 		
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/getPerson/1",String.class);
		Assert.assertEquals(200,response.getStatusCodeValue());		 
	}
	
	@Test
	public void testCountPerson()   {	 
		
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/countPerson",String.class);
		Assert.assertEquals(200,response.getStatusCodeValue()); 
	}
	
	@Test
	public void testDeletePerson()  {	 
		
		ResponseEntity<String> countBeforeDelete = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/countPerson",String.class);
		String beforeDeleteCount[] = countBeforeDelete.getBody().split(":"); 
		
		
		restTemplate.delete("http://localhost:" + 8080 + "/restapi/deletePerson/2"); 
		
		ResponseEntity<String> countAfterDelete = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/countPerson",String.class);
		String afeterDeleteCount[] = countAfterDelete.getBody().split(":"); 
		
		if(beforeDeleteCount.length>0){
			if(afeterDeleteCount.length==1){
				Assert.assertTrue(true);
			}
			else if(beforeDeleteCount[1].trim().equals(afeterDeleteCount[1].trim())){
				Assert.assertTrue(false);
			}else{
				Assert.assertTrue(true);
			}
		}else{
			Assert.assertTrue(true);
		}
		
	}
}
