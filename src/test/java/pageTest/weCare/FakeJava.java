package pageTest.weCare;

import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;


public class FakeJava {


	public static void main(String[] args) {
		
		for(int i=0; i<300; i++) {
			
//			FakeValuesService faker1 = new FakeValuesService(new Locale("en-US"), new RandomService());
			
			Faker faker = new Faker();
//			System.out.println(faker.name().lastName());

			System.out.println(faker.numerify("#########"));

//			Date fakeDate = new Date();
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy"); //"dd/MM/yy", Locale.US
//			System.out.println(sdf.format(fakeDate));
			
//			System.out.println(faker.address().fullAddress());
//			System.out.println(faker.address().streetAddress());
//			System.out.println(faker.address().streetAddress(false));
//			System.out.println(faker.address().streetAddress(true));

			
//			System.out.print(faker.letterify("?????") + ", ");
//			System.out.print(faker.numerify("#####") + ", ");
//			System.out.print(faker.bothify("12??##ED") + ", ");
//			System.out.print(faker.regexify("[a-z1-9]{4}") + ", ");
//			System.out.print(faker.regexify("[1][9][1-9]{2}") + ", ");
//			System.out.println();
		}

	}

}
