package test.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ref = "hi, " + random.nextFloat() + " at " + sdf.format(new Date());
		System.out.println(ref);
		return ref;
	}
}
