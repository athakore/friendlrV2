package com.cooksys.friendlrV2.person;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.custom.exceptions.IdNotAvailableException;
import com.cooksys.custom.exceptions.PersonNotFoundException;

@RestController
@RequestMapping("person")
public class PersonController {
	
	PersonService personService;
	
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping
	public List<PersonDto> get() {
		return personService.getDtoList();
	}
	
	@GetMapping("{id}")
	public PersonDto getById(@PathVariable Long id) throws PersonNotFoundException {
		return personService.getDtoById(id);
	}
	
	@GetMapping("{id}/friends")
	public List<PersonDto> getFriends(@PathVariable Long id) throws PersonNotFoundException {
		return personService.getFriendsDto(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PersonDto addPerson(@RequestBody PersonDto person) throws IdNotAvailableException {
		personService.addPerson(person);
		return person;
		
	}
	
	@PutMapping("{id}")
	public PersonDto editPerson(@RequestBody PersonDto person, @PathVariable Long id) throws PersonNotFoundException {
		return personService.editPerson(person, id);
	}

	@DeleteMapping("{id}")
	public void deletePerson(@PathVariable Long id) throws PersonNotFoundException {
		personService.deletePerson(id);
	}
}
