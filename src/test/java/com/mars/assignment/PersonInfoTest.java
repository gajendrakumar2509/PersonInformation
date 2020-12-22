package com.mars.assignment;

import java.util.List;

//import static org.junit.Assert.*;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mars.assignment.exception.PersonNotFoundException;
import com.mars.assignment.model.Person;

import ch.qos.logback.core.net.SyslogOutputStream;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PersonInfoTest {

	/*@Test
	public void test() {
		Assert.assertEquals(200,200);
		
	}*/
	@Autowired
	private  TestRestTemplate restTemplate;
	
	@Test
	public void contextLoads() throws Exception {
		//assertThat(personController).isNotNull();
	}
	
	@Test
	public void testAddPerson() throws JSONException {
		Person person = new Person();
		//person.setId(1L);
		person.setFirstName("Gajendra");
		person.setLastName("Kumar");
		ResponseEntity<Person> response =restTemplate.postForEntity("http://localhost:" + 8080 + "/restapi/addPerson",person, Person.class);
		 
		Assert.assertEquals(200,response.getStatusCodeValue());
		Assert.assertEquals(Person.class,response.getBody().getClass());
				
	
	}
	
	@Test
	public void testListPerson() throws JSONException {	
		ResponseEntity<List>  personList = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/listPerson",List.class);
		Assert.assertEquals(200,personList.getStatusCodeValue());
	  
	}
	
	@Test
	public void testEditPerson() throws JSONException {	
		Person person = new Person();
		
		person.setFirstName("Ram");
		person.setLastName("Chandra");
		restTemplate.put("http://localhost:" + 8080 + "/restapi/editPerson/1", person);
		
		Person  personBody = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/getPerson/1", Person.class).getBody();
		if(person.getFirstName().equals(personBody.getFirstName())){
			Assert.assertTrue("Updated", true);	
		}else{
			Assert.assertFalse("Failed to update", false);	
		}
	}

	@Test
	public void testGetPerson() throws JSONException {	 
		
		ResponseEntity<Person> response = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/getPerson/1",Person.class);
		Assert.assertEquals(200,response.getStatusCodeValue());
		Assert.assertEquals(Person.class,response.getBody().getClass());
	}
	
	@Test
	public void testCountPerson()   {	 
		
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/countPerson",String.class);
		Assert.assertEquals(200,response.getStatusCodeValue());
		//Assert.assertEquals(Person.class,response.getBody().getClass());
	}
	
	@Test
	public void testDeletePerson() throws PersonNotFoundException {	 
		
		ResponseEntity<String> countBeforeDelete = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/countPerson",String.class);
		String beforeDeleteCount = countBeforeDelete.getBody().split(":")[1].trim();
		
		restTemplate.delete("http://localhost:" + 8080 + "/restapi/deletePerson/5"); 
		
		ResponseEntity<String> countAfterDelete = restTemplate.getForEntity("http://localhost:" + 8080 + "/restapi/countPerson",String.class);
		String afeterDeleteCount = countAfterDelete.getBody().split(":")[1].trim();
		
		if(beforeDeleteCount.equals(afeterDeleteCount)){
			Assert.assertTrue(false);
		}else{
			Assert.assertTrue(true);
		}
	}
}
