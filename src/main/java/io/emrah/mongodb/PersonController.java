package io.emrah.mongodb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController 
{
	@Autowired
	private PersonRepository repository;
	
	@RequestMapping("/")
	public String main()
	{
		return "this is main page";
	}
	
	@PostMapping("/addperson")
	public String savePerson(@RequestBody Person person)
	{
		repository.save(person);
		return "Saved book with id:" + person.getId();
	}
	
	@GetMapping("/allpersons")
	public List<Person> getPersons()
	{
		return repository.findAll();
	}
	
	
	@RequestMapping(method= RequestMethod.GET, value="/person/{id}")
	public Person getPerson(@PathVariable int id)
	{
		return repository.findById(id).get();
	}
	
	
	
	@RequestMapping(method= RequestMethod.PUT, value="/update")
	public String updatePerson(@RequestBody Person person)
	{
		String old = repository.findById(person.getId()).get().toString();
		repository.save(person);
		
		return old + " is updated to " +person.toString();
	}
	
	
	
	@RequestMapping(method=RequestMethod.DELETE,value="/person/{id}")
	public String deleteById(@PathVariable int id)
	{
		Person person = repository.findById(id).get();
		repository.deleteById(id);
		return person.toString() + " is deleted!!";
	}
	
	
	
}
