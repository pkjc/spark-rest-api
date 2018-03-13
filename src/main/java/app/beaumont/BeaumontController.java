package app.beaumont;

import static app.Main.beaumontDao;
import spark.*;

public class BeaumontController {

	public static Route getAllBeaumontRecords = (Request request, Response response) -> {
		if(request.queryParams("s") != null){
			return beaumontDao.searchBeaumontRecord(request);
		}
		return beaumontDao.getAllBeaumontRecords();
	};
	
	public static Route getOneBeaumontRecord = (Request request, Response response) -> {
		System.out.println("The user requested " + request.params("id"));
		return beaumontDao.getOneBeaumontRecord(request);
	};
	
	public static Route searchBeaumontRecord = (Request request, Response response) -> {
		System.out.println("The user: " + request.params("id"));
		return beaumontDao.searchBeaumontRecord(request);
	};

}
