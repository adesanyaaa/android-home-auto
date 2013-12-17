package app.davols.home.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public class DataManager {

	public DataManager() {

	}

	public List<HomeUnit> getHomeUnits() throws JsonParseException, IOException {
		return getHomeUnits(new URL("http://192.168.0.14/"));
	}
	
	private List<HomeUnit> getHomeUnits(URL url) throws JsonParseException, IOException{
		List<HomeUnit> mList = Lists.newArrayList();
		ObjectMapper mapper = new ObjectMapper();
		JsonFactory f = new JsonFactory();
	
		JsonParser jp = f.createParser(url);
		// advance stream to START_ARRAY first:
		jp.nextToken();
		
		// and then each time, advance to opening START_OBJECT
		while (jp.nextToken() == JsonToken.START_OBJECT) {
			HomeUnit foobar = mapper.readValue(jp, HomeUnit.class);
			// process
			// after binding, stream points to closing END_OBJECT
			mList.add(foobar);
		}
		MyLog.d("DataManager","list:"+mList.size());
		return mList;
	}
	public List<HomeUnit> setStatus(int id,int status) throws JsonParseException, MalformedURLException, IOException {

		//http://192.168.0.14/set=0&value=255
		 return getHomeUnits(new URL("http://192.168.0.14/set="+id+"&value="+status));
		
	}
}
