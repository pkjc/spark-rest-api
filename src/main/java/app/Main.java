package app;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import app.beaumont.BeaumontController;
import app.beaumont.BeaumontDao;
import app.dmc.DmcController;
import app.dmc.DmcDao;
import app.henryFord.HfController;
import app.henryFord.HfDao;
import utils.Filters;
import utils.JsonTransformer;

/*
 * This is the application starting point. 
 * Starts the embedded Jetty server on port 4567
 * Project has been deployed to Heroku can be accessed at http://spark-rest-api.herokuapp.com
 */
public class Main {

	public static BeaumontDao beaumontDao;
	public static DmcDao dmcDao;
	public static HfDao hfDao;

	public static void main(String[] args) {

		port(getHerokuAssignedPort());

		beaumontDao = new BeaumontDao();
		dmcDao = new DmcDao();
		hfDao = new HfDao();
		
		String indexResponse = 
				"<h1 style=\"text-align:center;margin-top:10vh;font-family:sans-serif;\">"
				+ "Please request one of the following end points: <hr>"
				+ "/, /bm-data, /bm-data/?s={search keyword},<br>"
				+ " /dmc-data, /dmc-data/?s={search keyword},<br>"
				+ "/hf-data, /hf-data/?s={search keyword}</h1>";
		
		//Filters to run before every request
		before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
		before("*", Filters.addTrailingSlashes);
		
		//Routes for accessing BM data
		get("/", (req, resp) -> {return indexResponse;});
		get("/bm-data/", "application/json", BeaumontController.getAllBeaumontRecords, new JsonTransformer());
		get("/bm-data/:id/", "application/json", BeaumontController.getOneBeaumontRecord, new JsonTransformer());
		
		//Routes for accessing DMC data
		get("/dmc-data/", "application/json", DmcController.getAllDmcRecords, new JsonTransformer());
		get("/dmc-data/:id/", "application/json", DmcController.getOneDmcRecord, new JsonTransformer());
		
		//Routes for accessing HF data
		get("/hf-data/", "application/json", HfController.getAllHfRecords, new JsonTransformer());
		get("/hf-data/:id/", "application/json", HfController.getOneHfRecord, new JsonTransformer());
		
		//Just experimenting with other methods
		post("/hf-data/", (req,  resp) -> {
			System.out.println(req.body() + req.queryParams());
			return req.body() + req.queryParams() + req.headers("thisheader");
		});

	}
	
	//Get port from environment variable on Heroku
	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; // return default port if heroku-port isn't set (i.e. on localhost)
	}

}