#!/usr/bin/env python

"""

Use Twitter API to grab tweets using hashtags; 
export text file

Uses Twython module to access Twitter API

"""

import sys
import string
import simplejson #install simplejson at https://pypi.python.org/pypi/simplejson/
from twython import Twython #install Twython at https://github.com/ryanmcgrath/twython

#WE WILL USE THE VARIABLES DAY, MONTH, AND YEAR FOR OUR OUTPUT FILE NAME
import datetime
now = datetime.datetime.now()
day=int(now.day)
month=int(now.month)
year=int(now.year)

'''
#FOR OAUTH AUTHENTICATION -- NEEDED TO ACCESS THE TWITTER API
t = Twython(app_key='qtW8Q4270j67gooVD19tvAGo9', #REPLACE 'APP_KEY' WITH YOUR APP KEY, ETC., IN THE NEXT 4 LINES
    app_secret='0Jnd7nIrLYv3BhZdiT98iKcaQKZBEipXziib0CitV2RZ6zXATQ',
    oauth_token='2652772872-W9GTB3c973ayomnFPW1qEFgieNpskT5yJAD0c29',
    oauth_token_secret='nHdpiqLHzQVGSfVqgM7JgFN89FSpdkJpLdTNX1YYskx0G')

'''

#FOR OAUTH AUTHENTICATION -- NEEDED TO ACCESS THE TWITTER API
t = Twython(app_key='SNHP4nuGP5KdjKjbH1tOygHCc', #REPLACE 'APP_KEY' WITH YOUR APP KEY, ETC., IN THE NEXT 4 LINES
    app_secret='eq9XeCvckFUPFVhmmkziXYKjcKidTgHZDDgr4EonO1KeN7BzWe',
    oauth_token='239916326-JUUgaZtFtFmfRNjtPihtHGJx2bAUF7n8c2jH3Oey',
    oauth_token_secret='C3eH01hk4ctgGpOBPgBBProkS7pK6CqpashaHYDOihCM2')

teams  = [ 'east_Celtics', 'east_Knicks', 'east_76ers', 'east_Nets', 'east_Raptors', 'east_Bulls', 'east_Pacers', 'east_Bucks', 'east_Pistons', 
 'east_Cavs', 'east_MiamiHeat', 'east_OrlandoMagic', 'east_Hawks', 'east_Bobcats', 'east_Wizards', 
 'west_okcthunder', 'west_Nuggets', 'west_TrailBlazers', 'west_UtahJazz', 'west_TWolves', 'west_Lakers', 'west_Suns', 'west_GSWarriors', 'west_Clippers', 
 'west_NBAKings', 'west_GoSpursGo', 'west_Mavs', 'west_Hornets', 'west_Grizzlies', 'west_Rockets' ]

for team in teams:

    hashtag = team[5:] ##### this line need to change
    print('hashtag')
    print(hashtag)
    delimiter = ','
    data = t.search(q='#'+hashtag, count=100)
    tweets = data['statuses']

    #print("Tweets-")
    #print(data)

    #NAME OUR OUTPUT FILE - %i WILL BE REPLACED BY CURRENT MONTH, DAY, AND YEAR
    outfn = hashtag+".csv"

    #NAMES FOR HEADER ROW IN OUTPUT FILE
    fields = "created_at text".split()

    #INITIALIZE OUTPUT FILE AND WRITE HEADER ROW   
    outfp = open(outfn, "w")
    #outfp.write(string.join(fields, ",") + "\n")  # comment out if don't need header

    for entry in tweets:
       
        r = {}
        for f in fields:
            r[f] = ""
        #ASSIGN VALUE OF 'ID' FIELD IN JSON TO 'ID' FIELD IN OUR DICTIONARY
        r['created_at'] = entry['created_at']
        r['text'] = entry['text']
        
        print (r)
        #CREATE EMPTY LIST
        lst = []
        #ADD DATA FOR EACH VARIABLE
        for f in fields:
            s=unicode(r[f]).replace("\/", "/")
            s=s.replace("\r", "")
            s=s.replace("\n", "")
            s=s.replace(",", "")
            s=s.replace(";", "")
            s=s.replace("\"", "")
            lst.append(s)
        '''       
        for f in fields:
            lst.append(unicode(r[f]).replace("\/", "/"))
        '''
        #WRITE ROW WITH DATA IN LIST
        outfp.write(string.join(lst, delimiter).encode("utf-8") + "\n")

    outfp.close()  
