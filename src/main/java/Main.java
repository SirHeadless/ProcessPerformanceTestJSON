import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		// parsing file "JSONExample.json"
		Object obj = new JSONParser().parse(new FileReader(
				"C:\\TemporaryBackupsAndCo\\PerformanceTest\\A12_Platform.postman_test_run_20180927.json"));

		// typecasting obj to JSONObject
		JSONObject jo = (JSONObject) obj;

//		// getting firstName and lastName
//		String results = (String) jo.get("results");
//		String lastName = (String) jo.get("lastName");
//
//		System.out.println(results);
//		System.out.println(lastName);
//
//		// getting age
//		Long age = (Long)jo.get("age");
//		System.out.println(age);
//
		// getting address
//		Map address = ((Map)jo.get("results"));
//
//		// iterating address Map
//		Iterator<Map.Entry> itr1 = address.entrySet().iterator();
//		while (itr1.hasNext()) {
//			Map.Entry pair = itr1.next();
//			System.out.println(pair.getKey() + " : " + pair.getValue());
//		}

		// getting phoneNumbers
		JSONArray ja = (JSONArray) jo.get("results");

		// iterating phoneNumbers
		Iterator itr2 = ja.iterator();

		while (itr2.hasNext())
		{
			Map result = (Map)itr2.next();

			Object name = result.get("name");
			JSONArray jsonTimes = (JSONArray) result.get("times");
			ArrayList<Long> times = convertJSONArrayToJavaArray(jsonTimes);
//			System.out.println(times);
			Long min = times.get(times.indexOf(Collections.min(times)));
			Long max = times.get(times.indexOf(Collections.max(times)));
			double sum = 0;
			for (Long i : times) {
				sum += i;
			}
			double averageD = sum / times.size();
			int average = (int) averageD;
			System.out.println(String.format("| %s | %s | %s | %s |", name , min, max, average));

//			itr1 = ((Map) itr2.next()).entrySet().iterator();
//			while (itr1.hasNext()) {
//				Map.Entry pair = itr1.next();
//				System.out.println(pair.getKey() + " : " + pair.getValue());
//			}
		}
	}

	private static ArrayList<Long> convertJSONArrayToJavaArray(JSONArray jsonArray) {
		ArrayList<Long> list = new ArrayList<Long>();
		if (jsonArray != null) {
			int len = jsonArray.size();
			for (int i = 0; i < len; i++) {
				list.add((Long) jsonArray.get(i));
			}
		}
		return list;
	}
}