package com.barv;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class BarvBackEndApplicationTests {

	@Test
	void test() {
		assertEquals("1", 1, 1);
	}

}
