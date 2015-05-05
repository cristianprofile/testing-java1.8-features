package com.cristian.mylab;

import java.util.Optional;


public class Father {

	 Father(Optional<Father> father, String name) {
		super();
		this.name = name;
		this.father = father;
	}

	private Optional<Father> father;

	private String name;

	public String getName() {
		return name;
	}

	public Optional<Father> getFather() {
		return father;
	}

}
