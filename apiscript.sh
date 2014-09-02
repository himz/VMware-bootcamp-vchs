#!/bin/bash
# Script to simulate REST API requests

if [ $# -lt 6 ] 
then
	echo "CORRECT USAGE: sh apiscript.sh <protocol> <host> <port> <REST url> <http method> <no. of requests> <browser> <operating system>"
	echo "Codes for Browser and Operating Systems: "
	echo "Chrome - B1, Firefox - B2, Safari - B3"
	echo "Windows - OS1, MacOS - OS2, Linux - OS3"
else
	$ua = "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2049.0 Safari/537.36";
	if [ $7 -eq "B1" ]
	then
		if [$8 -eq "OS2" ]
		then
			$ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1944.0 Safari/537.36";
		elif [$8 -eq "OS3" ]
		then
			$ua = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1636.0 Safari/537.36";
		fi
	elif [ $7 -eq "B2" ]
        then
                if [ $8 -eq "OS1" ]
                then
			$ua = "Mozilla/5.0 (Windows NT 6.1; rv:23.0) Gecko/20100101 Firefox/23.0";
                elif [$8 -eq "OS2" ]
                then
			$ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:23.0) Gecko/20100101 Firefox/23.0";
                elif [$8 -eq "OS3" ]
                then
			$ua = "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:24.0) Gecko/20100101 Firefox/24.0";
                fi
        elif [ $7 -eq "B3" ]
        then
                if [ $8 -eq "OS1" ]
                then
			$ua = "Mozilla/5.0 (Windows; U; Windows NT 6.1; tr-TR) AppleWebKit/533.20.25 (KHTML, like Gecko) Version/5.0.4 Safari/533.20.27";
                elif [$8 -eq "OS2" ]
                then
			$ua = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/534.59.8 (KHTML, like Gecko) Version/5.1.9 Safari/534.59.8";
                elif [$8 -eq "OS3" ]
                then
			$ua = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.32 (KHTML, like Gecko) Chromium/25.0.1349.2 Chrome/25.0.1349.2 Safari/537.32 Epiphany/3.8.2";
                fi
        fi
		
	count=0;
	url="$1://$2:$3/$4"
	echo $url
	while [ $count -lt $6 ]
	do
		curl -i -H "Accept: application/json" --user-agent $ua -X $5 $url > log.txt
		echo " "
		sleep 0.025;
		((count+=1));
	done	
fi
