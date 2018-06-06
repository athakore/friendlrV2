package com.cooksys.friendlrV2.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cooksys.custom.exceptions.IdNotAvailableException;
import com.cooksys.custom.exceptions.PersonNotFoundException;

@Service
public class PersonService {
	
	private static Long autoGenId = new Long(7);
	
	private List<Person> people = new ArrayList<Person>();
	private PersonMapper personMapper;

	public PersonService(PersonMapper personMapper) {
		this.personMapper = personMapper;
		Person matt = new Person("Matthew", "Riddle", new ArrayList<Person>());
		Person cruz = new Person("Cruz","Muniz", new ArrayList<Person>());
		Person jason = new Person("Jason", "Kwak", new ArrayList<Person>());
		Person kyle = new Person("Kyle", "Serdutz", new ArrayList<Person>());
		Person pat = new Person("Patrick", "Doane", new ArrayList<Person>());
		Person evan = new Person("Evan", "Susag", new ArrayList<Person>());
		Person aarth = new Person("Aarth", "Thakore", new ArrayList<Person>());

		matt.getFriends().addAll(Arrays.asList(cruz, jason, kyle, pat, evan, aarth));
		cruz.getFriends().addAll(Arrays.asList(matt, jason, kyle, pat, evan, aarth));
		jason.getFriends().addAll(Arrays.asList(cruz, matt, kyle, pat, evan, aarth));
		kyle.getFriends().addAll(Arrays.asList(cruz, jason, matt, pat, evan, aarth));
		pat.getFriends().addAll(Arrays.asList(cruz, jason, kyle, matt, evan, aarth));
		evan.getFriends().addAll(Arrays.asList(cruz, jason, kyle, pat, matt, aarth));
		aarth.getFriends().addAll(Arrays.asList(cruz, jason, kyle, pat, evan, matt));
		people.addAll(Arrays.asList(matt, cruz, jason, kyle, pat, evan, aarth));
	}

	public List<PersonDto> getDtoList() {
		// TODO Auto-generated method stub
		return people.stream().map(personMapper::toDto).collect(Collectors.toList());
	}

	public PersonDto getDtoById(Long id) throws PersonNotFoundException {
		for(Person person: people) {
			if(person.getId() == id)
				return personMapper.toDto(person);
		}
		throw new PersonNotFoundException(id);
	}

	public List<PersonDto> getFriendsDto(Long id) throws PersonNotFoundException{
		for(Person person: people) {
			if(person.getId() == id)
				return person.getFriends().stream().map(personMapper::toDto).collect(Collectors.toList());
		}
		throw new PersonNotFoundException(id);
	}

	public void addPerson(PersonDto person) throws IdNotAvailableException {
		if(person.getId() != null)
			throw new IdNotAvailableException(person.getId());
		Person temp = personMapper.fromDto(person);
		temp.setId(autoGenId++);
		temp.setFriends(new ArrayList<Person>(people)); //Initialized friends lists with list of all people just to have a list to test.
		people.add(temp);
	}

	private void checkId(Long id) throws IdNotAvailableException {
		for(Person person : people) {
			if(person.getId().equals(id))
				throw new IdNotAvailableException(id);
		}
	}

	public PersonDto editPerson(PersonDto person, Long id) throws PersonNotFoundException {
		for(Person p : people) {
			ListIterator<Person> temp = p.getFriends().listIterator();
			while(temp.hasNext()) {
				Person t = temp.next();
				if(t.getId().equals(id)) {
					Person pers = personMapper.fromDto(person);
					pers.setFriends(t.getFriends());
					temp.set(pers);
				}
			}
		}
		ListIterator<Person> iter = people.listIterator();
		while(iter.hasNext()) {
			Person temp = iter.next();
			if(temp.getId().equals(id)) {
				Person p = personMapper.fromDto(person);
				p.setFriends(temp.getFriends());
				iter.set(p);
				return person;
			}
		}
		throw new PersonNotFoundException(id);		
	}

	public void deletePerson(Long id) throws PersonNotFoundException {
		for(Person p : people) {
			ListIterator<Person> temp = p.getFriends().listIterator();
			while(temp.hasNext()) {
				if(temp.next().getId().equals(id)) {
					temp.remove();
				}
			}
		}
		ListIterator<Person> iter = people.listIterator();
		while(iter.hasNext()) {
			if(iter.next().getId().equals(id)) {
				iter.remove();
				return;
			}
		}
		throw new PersonNotFoundException(id);
	}
	
	
}
