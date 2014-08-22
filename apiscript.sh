#!/bin/bash
# Script to simulate REST API requests

if [ $# -lt 6 ] 
then
	echo "CORRECT USAGE: sh apiscript.sh <protocol> <host> <port> <REST url> <http method> <no. of requests>"
else
	count=0;
	url="$1://$2:$3/$4"
	echo $url
	while [ $count -lt $6 ]
	do
		curl -i -H "Accept: application/json" -X $5 $url > log.txt
		echo " "
		sleep 0.025;
		((count+=1));
	done	
fi
