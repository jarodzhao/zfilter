package test.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController
{
	@ResponseBody
	@RequestMapping("/hi")
	public String hi()
	{
		Random random = new Random();
		String ref = "hi, " + random.nextFloat();
		System.out.println(ref);
		return ref;
	}
}
