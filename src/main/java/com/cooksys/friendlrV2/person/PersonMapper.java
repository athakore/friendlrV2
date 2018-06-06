package com.cooksys.friendlrV2.person;

import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface PersonMapper {
	
	PersonDto toDto(Person person);
	
	Person fromDto(PersonDto dto);

}
