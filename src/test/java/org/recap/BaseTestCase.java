package org.recap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = Main.class)
@Rollback(false)
public class BaseTestCase {

	@Test
	public void contextLoads() {
	}

}
