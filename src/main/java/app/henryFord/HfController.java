package app.henryFord;

import static app.Main.hfDao;
import spark.*;

public class HfController {

	public static Route getAllHfRecords = (Request request, Response response) -> {
		if(request.queryParams("s") != null){
			return hfDao.searchHfRecord(request);
		}
		return hfDao.getAllHfRecords();
	};
	
	public static Route getOneHfRecord = (Request request, Response response) -> {
		System.out.println("The user requested " + request.params("id"));
		return hfDao.getOneHfRecord(request);
	};
	
	public static Route searchHfRecord = (Request request, Response response) -> {
		System.out.println("The user: " + request.params("id"));
		return hfDao.searchHfRecord(request);
	};

}
