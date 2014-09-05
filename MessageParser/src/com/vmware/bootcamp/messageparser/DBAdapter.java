package com.vmware.bootcamp.messageparser;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

public class DBAdapter {
	
	public void storeInMongoDB(String json) {
		try {
			 
			Mongo mongo = new Mongo("localhost", 27017);
			DB db = mongo.getDB("vchs");
			DBCollection collection = db.getCollection("dummyColl");
 
			// convert JSON to DBObject directly
			DBObject dbObject = (DBObject) JSON
					.parse(json);
 
			collection.insert(dbObject);
 
			DBCursor cursorDoc = collection.find();
			while (cursorDoc.hasNext()) {
				System.out.println(cursorDoc.next());
			}
 
			System.out.println("Done");
			mongo.close();
 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
}
