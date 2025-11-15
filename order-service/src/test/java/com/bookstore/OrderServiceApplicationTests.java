package com.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@Import(ContainersConfig.class)
class OrderServiceApplicationTests extends BaseIntegrationTest{

	@Test
	void contextLoads() {
	}

}
