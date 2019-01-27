# JazzInTheBay

[![Build Status](https://travis-ci.com/JazzintheBay/JazzintheBay.svg?token=zHcMdCkA6PdsLZg2hsyF&branch=master)](https://travis-ci.org/JazzintheBay/JazzintheBay)

## Product Vision

For a user considering going to a jazz show in the bay area, we want to build the go-to destination for discovering, selecting, and (in the future) buying tickets for a show within 10 mins. For this, we need the following:

Database of structured, accurate and comprehensive information about curated shows and venues

### Easy to navigate front-end interface that allows user to quickly zoom in on a shortlist of shows based on user’s needs & preferences, in terms of:
* Logistics: dates, times, locations
* Musical preferences: music classifications, artist characteristics
* Experience preferences: e.g. kid-friendly, dancing-friendly, outdoors, etc.
* Accurate, relevant and comprehensive information available to be inspired to go to the show: e.g. artist and music details, audio/video previews, ticket pricing and links to ticketing
* Automation-assisted processes to enable tracking of new shows and collecting accurate information about upcoming shows and artists from various sources (e.g. venue websites, artist websites)

### Architecture
The architecture will have 3 key components:

A NoSQL (MongoDB) database will be the “heart” of jazzinthebay, with metadata about artists, bands, shows, venues to start with, but will also be extensible to be able to support future metadata such as user identities, music albums, etc. We can refer to this database as Miles henceforth (after the prolific Miles Davis who created music across various genres of jazz).

Miles will power all the content in the front-end UX. This will initially be a modification of the existing squarespace template, and in the future can be fully replaced by new code written in HTML5/JavaScript/React.js.

A backend mechanism of web scraping and curation using Python scripts and google spreadsheet: The web scraper will ingest event and artist information from specific sources (websites of key venues, regular artists, ticketing services) and insert the information either directly into Miles (for repeat shows / existing artists), or into a google spreadsheet where upcoming shows can be reviewed, and selected for listing or rejected. These scripts will be manually executed and do not need to be fully automated at this stage.

We will build an extensible architecture, which will allow us to launch an MVP quickly, and incrementally add features and new metadata over time.

### Requirements for Miles database
* Miles 1.0 should have the following metadata and schema:

### Schema for Shows:

```
{
  "EventGroup": {
    "EventGroupID": {},
    "Event": [
      {
        "EventID": {},
        "EventName": {},
        "Headline": {},
        "MusicFestival": {
          "FestivalID": {},
          "FestivalName": {},
          "Year": {},
          "Image": [],
          "doorTime": {},
          "startTime": {},
          "endTime": {}
        },
        "Performer": {
          "MusicGroup": [
            {
              "MusicGroupID": {},
              "MusicGroupName": {},
              "Weblinks": {
                "URLType": {},
                "URL": {}
              },
              "MusicCategory": [],
              "MusicGroupType": [],
              "MusicGroupTags": [],
              "GroupLead": [
                {
                  "ArtistID": {},
                  "ArtistName": {},
                  "ArtistRole": [],
                  "Instruments": [],
                  "Awards": [
                    {
                      "Type": {},
                      "AwardName": {},
                      "AwardCategory": {},
                      "Year": {}
                    }
                  ]
                }
              ],
              "MusicGroupMember": [
                {
                  "ArtistID": {},
                  "ArtistName": {},
                  "ArtistRole": [],
                  "Instruments": [],
                  "Awards": [
                    {
                      "Type": {},
                      "AwardName": {},
                      "AwardCategory": {},
                      "Year": {}
                    }
                  ]
                }
              ]
            }
          ],
          "Awards": [
            {
              "Type(award or nomination)": {},
              "AwardName": {},
              "AwardCategory": {},
              "Year": {}
            }
          ],
          "Review": [
            {
              "Excerpt": {},
              "Reviewer": {},
              "URL": {}
            }
          ]
        },
        "Location": {
          "VenueID": {},
          "VenueName": {},
          "VenueTags(array)": {},
          "Weblinks": {
            "URL": {}
          },
          "Address": {
            "Street": {},
            "Building": {},
            "City": {},
            "State": {},
            "ZipCode": {},
            "Country": {},
            "MapsURL": []
          }
        },
        "Ticket": {
          "TicketURL": {},
          "TicketPrice": {
            "LowPrice": {},
            "HighPrice": {},
            "DiscountTags": []
          }
        },
        "EventSourceURL": {},
        "ShowcaseContent": {
          "ContentType": {},
          "ContentURL": {}
        },
        "EventDescription": {},
        "EventAlbum": [
          {
            "AlbumID": {},
            "AlbumName": {},
            "AlbumURL": {}
          }
        ]
      }
    ]
  },
  "URLType(Website/FB/Spotify etc.)": {}
}

```

### Schema for MusicGroups

```
MusicGroup (array)
MusicGroupID
MusicGroupName
Image (array)
Weblinks
URLType (Website/FB/Twitter etc.)
URL
MusicCategory (array)
MusicGroupType (array)
MusicGroupTags (array)
GroupLead (array)
ArtistID
ArtistName
ArtistRole (array)
Instruments (array)
Awards (array)
Type (award or nomination)
AwardName
AwardCategory
Year
MusicGroupMember (array)
ArtistID
ArtistName
ArtistRole (array)
Instruments (array)
Awards (array)
Type (award or nomination)
AwardName
AwardCategory
Year
Awards (array)
Type (award or nomination)
AwardName
AwardCategory
Year
Reviews (array)
Excerpt
Reviewer
URL
GroupBio
Events
EventID
StartDate
StartTime
Location
ShowcaseContent
ContentType (Youtube, Spotify, SoundCloud etc.)
ContentURL
Album (array)
AlbumID
AlbumName
AlbumURL
Year
Awards (array)
Type (award or nomination)
AwardName
AwardCategory
Year
```

### Schema for Artists

```
Artist
ArtistID
ArtistName
Image (array)
ArtistRole (array)
Instruments (array)
PersonTags (array)
Awards (array)
Type (award or nomination)
AwardName
AwardCategory
Year
MusicGroups (array)
MusicGroupID
MusicGroupName
Years
Connections (array)
ConnectionType
PersonID, PersonName
StartYear
EndYear
Weblinks
URLType (Website/FB/Twitter etc.)
URL
MusicCategory (array)
Reviews (array)
Excerpt
Reviewer
URL
ArtistBio
Events
EventID
StartDate
StartTime
Location
ShowcaseContent
ContentType (Youtube, Spotify, SoundCloud etc.)
ContentURL
Album (array)
AlbumID
AlbumName
AlbumURL
Year
Awards (array)
Type (award or nomination)
AwardName
AwardCategory
Year
```




