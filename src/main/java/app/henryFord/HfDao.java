package app.henryFord;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import utils.FileOpsUtil;

public class HfDao {

	public HfModel[] getAllHfRecords() {
		HfModel[] annData = readJsonFile("dataSource/hf.json");
		return annData;
	}

	private HfModel[] readJsonFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		ObjectMapper mapper = new ObjectMapper();
		HfModel[] annData = null;
		try {
			String data = FileOpsUtil.readFromInputStream(inputStream);
			annData = mapper.readValue(data, HfModel[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return annData;
	}

	public HfModel getOneHfRecord(Request req) {
		HfModel[] annData = getAllHfRecords();
		if(Integer.parseInt(req.params("id")) < annData.length){
			return annData[Integer.parseInt(req.params("id"))];
		}else{
			return null;
		}
	}

	public List<HfModel> searchHfRecord(Request req) {
		HfModel[] annData = getAllHfRecords();
		List<HfModel> searchResults = new ArrayList<>();

		for (HfModel d : annData) {
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
