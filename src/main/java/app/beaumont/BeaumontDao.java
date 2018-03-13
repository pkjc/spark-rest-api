package app.beaumont;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import utils.FileOpsUtil;
/*
 * This is the Data Access class for BM data. 
 * It accesses the data store and brings the requested data
 */
public class BeaumontDao {

	//Reads JSON file and reads all data into the model object
	public BeaumontModel[] getAllBeaumontRecords() {
		BeaumontModel[] annData = readJsonFile("dataSource/bmd.json");
		return annData;
	}
	//File reading utility method
	private BeaumontModel[] readJsonFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		ObjectMapper mapper = new ObjectMapper();
		BeaumontModel[] annData = null;
		try {
			String data = FileOpsUtil.readFromInputStream(inputStream);
			annData = mapper.readValue(data, BeaumontModel[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return annData;
	}
	//Returns a single record by ID
	public BeaumontModel getOneBeaumontRecord(Request req) {
		BeaumontModel[] annData = getAllBeaumontRecords();
		return annData[Integer.parseInt(req.params("id"))];
	}
	//Searches records by given keyword
	public List<BeaumontModel> searchBeaumontRecord(Request req) {
		BeaumontModel[] annData = getAllBeaumontRecords();
		List<BeaumontModel> searchResults = new ArrayList<>();

		for (BeaumontModel d : annData) {
			if (d.getAnnStatus() != null && d.getAnnStatus().equalsIgnoreCase(req.queryParams("s"))) {
				searchResults.add(d);
			} else if (d.getAnnLoc() != null && d.getAnnLoc().equalsIgnoreCase(req.queryParams("s"))) {
				searchResults.add(d);
			} else if (d.getAnnType() != null && d.getAnnType().equalsIgnoreCase(req.queryParams("s"))) {
				searchResults.add(d);
			}
		}
		return searchResults;
	}
}
