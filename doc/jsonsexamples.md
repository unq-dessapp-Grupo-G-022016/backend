###########
# http://localhost:8080/rest/user/create
##########
{
	"userName": "momo24",
	"attendedEvents":
	[
	],
	"personalEvent":
	[
	],
	"profile":
	{
	    "categories":
	    [
	    ]
	},
	"friends":
	{
	    "friends":
	    [
	    ]
	},
	"lowCostTrip":
	{
	    "ammount": 50
	}
}
######
# http://localhost:8080/rest/event/create/
#####
{
    "startTime":
    "2011-12-03T10:15:30",
    "category":
    {
        "name": "warm places"
    },
    "endTime":"2011-12-03T10:15:30",
    "price":
    {
        "ammount": 20
    },
            "attenders":
    [
    ],
    "details": "Devils house",
    "address": "666",
    "name": "goingToHell"
}
########
# to update event
#######
{
	"id":1,
    "startTime":
    "2011-12-03T10:15:30",
    "category":
    {
        "name": "warm places"
    },
    "endTime":"2011-12-03T10:15:30",
    "price":
    {
        "ammount": 20
    },
            "attenders":
    [
    ],
    "details": "Devils house",
    "address": "666",
    "name": "goingToHell"
}
#####
# user updated with an attended event
#####
{
	"userName": "momo24",
	"attendedEvents":
	[
	{
	"id":1,
    "startTime":
    "2011-12-03T10:15:30",
    "category":
    {
        "name": "warm places"
    },
    "endTime":"2011-12-03T10:15:30",
    "price":
    {
        "ammount": 20
    },
            "attenders":
    [
    ],
    "details": "Devils house",
    "address": "666",
    "name": "goingToHell"
	}
	],
	"personalEvent":
	[
	],
	"profile":
	{
	    "categories":
	    [
	    ]
	},
	"friends":
	{
	    "friends":
	    [
	    ]
	},
	"lowCostTrip":
	{
	    "ammount": 50000
	}
}
