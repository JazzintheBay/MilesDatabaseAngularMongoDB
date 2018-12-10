package umm3601.Shows;

import java.net.UnknownHostException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;

/**
 * JSON file consisting of listings of shows from python scrapper
 * to be used as an input to the collection EventGroup in MongoDB
 * Dated: December 9th 2018
 * Author: Ahnaf Prio
 */

public class ShowsCollectionImporter {
    public static void main(String[] args) {

        try {

            Mongo mongo = new Mongo("localhost", 27017);
            DB db = mongo.getDB("Miles");
            DBCollection collection = db.getCollection("Shows");

            // convert JSON to DBObject directly
            // currently only adds object to collection Specified
            DBObject dbObject = (DBObject) JSON
                    .parse("{'event':'JazzInTheBay', 'EventID':'Forever'}");

            collection.insert(dbObject);

            DBCursor cursorDoc = collection.find();
            while (cursorDoc.hasNext()) {
                System.out.println(cursorDoc.next());
            }

            System.out.println("Done");

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
}