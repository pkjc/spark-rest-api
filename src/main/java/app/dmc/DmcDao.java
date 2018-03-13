package app.dmc;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import utils.FileOpsUtil;

public class DmcDao {

	public DmcModel[] getAllDmcRecords() {
		DmcModel[] annData = readJsonFile("dataSource/dmc.json");
		return annData;
	}

	private DmcModel[] readJsonFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		ObjectMapper mapper = new ObjectMapper();
		DmcModel[] annData = null;
		try {
			String data = FileOpsUtil.readFromInputStream(inputStream);
			annData = mapper.readValue(data, DmcModel[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return annData;
	}

	public DmcModel getOneDmcRecord(Request req) {
		DmcModel[] annData = getAllDmcRecords();
		return annData[Integer.parseInt(req.params("id"))];
	}

	public List<DmcModel> searchDmcRecord(Request req) {
		DmcModel[] annData = getAllDmcRecords();
		List<DmcModel> searchResults = new ArrayList<>();
		
		for (DmcModel d : annData) {
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
