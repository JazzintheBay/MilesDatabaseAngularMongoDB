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
            // currently only adds one object to the shows collection
            // Things to work on: Work on Multiple objects parsing through
            // Create import for parse from Json File and not manually hardcode it in
            // for new imports to work everytime
            DBObject dbObject = (DBObject) JSON
                    .parse("{{\n" +
                            "  \"EventGroup\": {\n" +
                            "    \"EventGroupID\": {},\n" +
                            "    \"Event\": [\n" +
                            "      {\n" +
                            "        \"EventID\": {},\n" +
                            "        \"EventName\": {},\n" +
                            "        \"Headline\": {},\n" +
                            "        \"MusicFestival\": {\n" +
                            "          \"FestivalID\": {},\n" +
                            "          \"FestivalName\": {},\n" +
                            "          \"Year\": {},\n" +
                            "          \"Image\": [],\n" +
                            "          \"doorTime\": {},\n" +
                            "          \"startTime\": {},\n" +
                            "          \"endTime\": {}\n" +
                            "        },\n" +
                            "        \"Performer\": {\n" +
                            "          \"MusicGroup\": [\n" +
                            "            {\n" +
                            "              \"MusicGroupID\": {},\n" +
                            "              \"MusicGroupName\": {},\n" +
                            "              \"Weblinks\": {\n" +
                            "                \"URLType\": {},\n" +
                            "                \"URL\": {}\n" +
                            "              },\n" +
                            "              \"MusicCategory\": [],\n" +
                            "              \"MusicGroupType\": [],\n" +
                            "              \"MusicGroupTags\": [],\n" +
                            "              \"GroupLead\": [\n" +
                            "                {\n" +
                            "                  \"ArtistID\": {},\n" +
                            "                  \"ArtistName\": {},\n" +
                            "                  \"ArtistRole\": [],\n" +
                            "                  \"Instruments\": [],\n" +
                            "                  \"Awards\": [\n" +
                            "                    {\n" +
                            "                      \"Type\": {},\n" +
                            "                      \"AwardName\": {},\n" +
                            "                      \"AwardCategory\": {},\n" +
                            "                      \"Year\": {}\n" +
                            "                    }\n" +
                            "                  ]\n" +
                            "                }\n" +
                            "              ],\n" +
                            "              \"MusicGroupMember\": [\n" +
                            "                {\n" +
                            "                  \"ArtistID\": {},\n" +
                            "                  \"ArtistName\": {},\n" +
                            "                  \"ArtistRole\": [],\n" +
                            "                  \"Instruments\": [],\n" +
                            "                  \"Awards\": [\n" +
                            "                    {\n" +
                            "                      \"Type\": {},\n" +
                            "                      \"AwardName\": {},\n" +
                            "                      \"AwardCategory\": {},\n" +
                            "                      \"Year\": {}\n" +
                            "                    }\n" +
                            "                  ]\n" +
                            "                }\n" +
                            "              ]\n" +
                            "            }\n" +
                            "          ],\n" +
                            "          \"Awards\": [\n" +
                            "            {\n" +
                            "              \"Type(award or nomination)\": {},\n" +
                            "              \"AwardName\": {},\n" +
                            "              \"AwardCategory\": {},\n" +
                            "              \"Year\": {}\n" +
                            "            }\n" +
                            "          ],\n" +
                            "          \"Review\": [\n" +
                            "            {\n" +
                            "              \"Excerpt\": {},\n" +
                            "              \"Reviewer\": {},\n" +
                            "              \"URL\": {}\n" +
                            "            }\n" +
                            "          ]\n" +
                            "        },\n" +
                            "        \"Location\": {\n" +
                            "          \"VenueID\": {},\n" +
                            "          \"VenueName\": {},\n" +
                            "          \"VenueTags(array)\": {},\n" +
                            "          \"Weblinks\": {\n" +
                            "            \"URL\": {}\n" +
                            "          },\n" +
                            "          \"Address\": {\n" +
                            "            \"Street\": {},\n" +
                            "            \"Building\": {},\n" +
                            "            \"City\": {},\n" +
                            "            \"State\": {},\n" +
                            "            \"ZipCode\": {},\n" +
                            "            \"Country\": {},\n" +
                            "            \"MapsURL\": []\n" +
                            "          }\n" +
                            "        },\n" +
                            "        \"Ticket\": {\n" +
                            "          \"TicketURL\": {},\n" +
                            "          \"TicketPrice\": {\n" +
                            "            \"LowPrice\": {},\n" +
                            "            \"HighPrice\": {},\n" +
                            "            \"DiscountTags\": []\n" +
                            "          }\n" +
                            "        },\n" +
                            "        \"EventSourceURL\": {},\n" +
                            "        \"ShowcaseContent\": {\n" +
                            "          \"ContentType\": {},\n" +
                            "          \"ContentURL\": {}\n" +
                            "        },\n" +
                            "        \"EventDescription\": {},\n" +
                            "        \"EventAlbum\": [\n" +
                            "          {\n" +
                            "            \"AlbumID\": {},\n" +
                            "            \"AlbumName\": {},\n" +
                            "            \"AlbumURL\": {}\n" +
                            "          }\n" +
                            "        ]\n" +
                            "      }\n" +
                            "    ]\n" +
                            "  },\n" +
                            "  \"URLType(Website/FB/Spotify etc.)\": {}\n" +
                            "}}");

            collection.insert(dbObject);

            //Object Schema looks like:
            

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