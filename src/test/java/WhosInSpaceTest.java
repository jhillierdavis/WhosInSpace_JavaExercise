import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.*;
import java.io.*;
import java.net.*;
import java.nio.file.Files; 

import com.fasterxml.jackson.databind.*;

public class WhosInSpaceTest {
	// TODO: Obtain test resource JSON file
	private static final String TEST_JSON_FILE = "astros_20151209.json";

	@Ignore	
	@Test
	public void foo() throws IOException	{
		InputStream is = getClass().getResourceAsStream("astros_20151209.json");
		assertNotNull(is);

		BufferedReader br = new BufferedReader(new InputStreamReader(is) );
		
		String line;
		while ((line = br.readLine()) != null) { 
			System.out.println("line = `" + line + "'");
		}
		
	}
	
	@Ignore
	@Test
	public void resourceFilesAvailable() throws IOException	{
		URL url = this.getClass().getResource("astros_20160127.json");
		assertNotNull(url);
		
		File resourceFile = new File(url.getFile());
		System.out.println( resourceFile ); 
		
		String input = new String(Files.readAllBytes( resourceFile.toPath() ));
		System.out.println( input );
	}
	
	@Test
	public void parseWithJackson() throws Exception	{
		
		// Given: Some JSON data & a Jackson object mapper
		InputStream jsonInput = getClass().getResourceAsStream(TEST_JSON_FILE);			
		assertNotNull( jsonInput );	
		ObjectMapper mapper = new ObjectMapper();		
		// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		// When: 
		Map<String,Object> map = new ObjectMapper().readValue(jsonInput, Map.class);
		
		// Then
		assertNotNull(map);
		assertThat(map.get("message"), is("success") );		
		assertThat(map.get("number"), is(6) );

		ArrayList<Map<String, String>> craftPeople = (ArrayList<Map<String, String>>) map.get("people");		
		
		for (Map<String, String> entry: craftPeople )	{
			System.out.println( "Spacecraft: " + entry.get("craft") );
			System.out.println( "Astronaut: " + entry.get("name") );			
		}
		
		/*
		AstronautInfo info = mapper.readValue(jsonInput, AstronautInfo.class);
		assertThat( info.number, is(6) );
		System.out.println(info);
		*/
	}
}

/*
class AstronautInfo	{
	public int number;	
	public ArrayList<Map<String,String>> people = new ArrayList<>();
	
	public String toString() {
	     StringBuilder sb =  new StringBuilder();
	     sb.append("number: " + number);
	     sb.append(", ");		
		 for(Map<String,String> pair: people)	{
			sb.append("person: " + pair.get(0));
	     	sb.append(", ");			
		}
		 return sb.toString();
	   }	
}

class Spacecraft	{
	public String name;
}

class Astornaut	{
	public String name;
}
*/