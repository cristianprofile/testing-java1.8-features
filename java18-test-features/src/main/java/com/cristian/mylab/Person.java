package com.cristian.mylab;

class Person {
    String firstName;
    String lastName;

    //Constructor with no parameters
    Person() {}
    
    //Constructor with one parameters
    Person( String firstName) {
        this.firstName = firstName;
    }
    
  //Constructor with two parameters
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
   

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    
    
}
