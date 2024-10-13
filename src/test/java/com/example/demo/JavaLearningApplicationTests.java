package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Iterator;
//@SpringBootTest
class JavaLearningApplicationTests {

	@Test
	void contextLoads() {
		String s = "/foo/./bar/./../bar/qwe/..";
		String expected = "/foo/bar";
		Assertions.assertEquals(expected, simplifyPath(s));
	}
	public String simplifyPath(String s){
		char[] charArray = s.toCharArray();
		for(char el : charArray){
			if (el == '.'){
			}
		}
		return null;
	}
}
