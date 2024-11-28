package rahulacademay.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.testcomponent.BaseClass;

public class DataReader extends BaseClass{
	public static void main(String[] args) throws IOException {
		Properties prop=new Properties();
		FileInputStream stream=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\resources\\Globaldata.properties");
		prop.load(stream);
		String jsonfilepath=System.getProperty("user.dir").concat(prop.getProperty("jsonfilepath"));
		System.out.println(jsonfilepath);
		DataReader reader =new DataReader();
		System.out.println(reader.getjsontohashmap(jsonfilepath));
		
		
	}

	public List<HashMap<String, String>> getjsontohashmap(String jsonfilepath) throws IOException
	{
		String jsoncontent=FileUtils.readFileToString
		(new File(jsonfilepath),StandardCharsets.UTF_8);
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> datas=
		mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>() {});
		return datas;
	}
}
