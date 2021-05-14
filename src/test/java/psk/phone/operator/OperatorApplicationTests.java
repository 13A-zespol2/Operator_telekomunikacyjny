package psk.phone.operator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DecimalFormat;
import java.util.Random;

@SpringBootTest
class OperatorApplicationTests {

    @Test
    void contextLoads() {

	for(int i = 0; i < 100 ; i++)
	{
		Random rand = new Random();
		int num1 = rand.nextInt(999);
		int num2 = rand.nextInt(999);
		int num3 = rand.nextInt(999);

		DecimalFormat df3 = new DecimalFormat("000");

		String phoneNumber = df3.format(num1) + " " + df3.format(num2) + " " + df3.format(num3);

	}

    }

}
