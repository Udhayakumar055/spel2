package com.Spel.SpelLearning.service;

import java.util.List;

public class ExpressionService {
	
	
		
		String name;
		
		String address;
		
		int age;
		
		List<String> expression;

		

		public List<String> getExpression() {
			return expression;
		}

		public void setExpression(List<String> expression) {
			this.expression = expression;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public ExpressionService(String name, String address, int age) {
			super();
			this.name = name;
			this.address = address;
			this.age = age;
		}
		
	}


