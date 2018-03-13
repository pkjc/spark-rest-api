package app.beaumont;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import utils.FileOpsUtil;

public class BeaumontDao {

	public BeaumontModel[] getAllBeaumontRecords() {
		BeaumontModel[] annData = readJsonFile("dataSource/bmd.json");
		return annData;
	}

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

	public BeaumontModel getOneBeaumontRecord(Request req) {
		BeaumontModel[] annData = getAllBeaumontRecords();
		return annData[Integer.parseInt(req.params("id"))];
	}

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
