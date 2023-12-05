package vttpday15.vttpday15;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootApplication
public class Vttpday15Application implements CommandLineRunner{

	@Autowired @Qualifier("myredis") // telling spring to look for the bean
	private RedisTemplate<String, String> template;

	public static void main(String[] args) {
		SpringApplication.run(Vttpday15Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.printf(">>> redistemplate: %s\n", template);

		// Key Value operations
		// ValueOperations<String, String> opsValue = template.opsForValue();
		// opsValue.set("name", "barney");
		// opsValue.set("email", "barney@gmail.com");
		// opsValue.increment("count", 5);
		// String count = opsValue.get("count");
		// System.out.printf("count = %s\n", count);
		// String name = opsValue.get("name");
		// System.out.printf("name: %s\n", name);
		// opsValue.set("promocode", "abc123", Duration.ofSeconds(30));

		// List operations
		// if (template.hasKey("fred_cart")){
		// 	template.delete("fred_cart");
		// }
		// ListOperations<String, String> opsList = template.opsForList();
		// opsList.leftPush("fred_cart", "apple");
		// opsList.leftPushAll("fred_cart", "orange", "pear");

		// List<String> list = new LinkedList<>();
		// list.add("durian");
		// list.add("mangoes");
		// opsList.rightPushAll("fred_cart", list);
		
		// Long size = opsList.size("fred_cart");
		// List<String> contents = opsList.range("fred_cart", 0, size);
		// if (null != contents){
		// 	for (String content: contents){
		// 		System.out.printf(">>> %s\n", content);
		// 	}
		// }
		// opsList.leftPop("fred_cart");
		// opsList.rightPop("fred_cart");

		// String item = opsList.index("fred_cart", 1);
		// System.out.printf(">>>> item[1]: %s\n", item);

		// Map operations
		// HashOperations<String, String, String> opsHash = template.opsForHash();
		// opsHash.put("c001", "name", "fred");
		// opsHash.put("c001", "email", "fred@gmail.com");
		// opsHash.put("c002", "name", "barney");
		// opsHash.put("c002", "email", "barney@gmail.com");

		// String empName = opsHash.get("c001", "name");
		// System.out.printf("empName: %s\n", empName);
	}

}
