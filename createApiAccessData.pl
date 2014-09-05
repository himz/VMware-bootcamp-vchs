#!/usr/bin/perl
if($#ARGV < 0) {
	print "Usage: $0 <numRecords> [<numDays> <gapSeconds>]\n";
	print "Creates #numrecords with timestamps + or - numDays relative to the current time and are spaced gapSeconds apart\n";
	exit;
}

#numdays before/after today
my($numDays) = 0;
if($#ARGV  >= 1) {
	$numDays = $ARGV[1];
}
my($gapSeconds) = 1;
if($#ARGV  >= 2) {
	$gapSeconds = $ARGV[1];
}


# generate random data for mongoDB 
# Format
#{
#    "timestamp":"14358670023",
#    "browser":"chrome/safari/firefox/ie/other",
#    "OS":"",
#    "http_verb":"",
#    "api_path":"",
#    "client_ip":"",
#    "response_time":"",
#}

my(@browsers) = (chrome,safari,firefox,ie,other);
my(@OSs) = ("Mac OS X","Windows 8.1",Linux,other);
my(@verbs) = (POST,GET,HEAD);
my(@apis) = ("vApp/power/action/powerOn", "vApp/power/action/powerOff", "vApp/power/action/reset", "admin/extension/action/register", "admin/extension/action/deregister","admin/extension/action/getAdmin");

#offset by correct number of days
my($timeStamp) = time() + $numDays * 3600 * 24;

#print "Generating data with initial timestamp:$timestamp and gapSeconds:$gapSeconds\n";
for(my($i) = 0; $i < $ARGV[0]; $i++) {

	$timeStamp += $gapSeconds;
	my($browser) = $browsers[int(rand($#browser))];
	my($OS) = $OSs[int(rand($#OSs))];
	my($verb) = $verbs[int(rand($#verbs))];
	my($api) = $apis[int(rand($#apis))];
	my($rt) = int(rand(100));
	if($api =~ /powerOn/) {
	} elsif($api =~ /poweroff/) {
		$rt /= 2;
	} elsif($api =~ /reset/) {
		$rt *= 2;
	} elsif($api =~ /action.register/) {
		$rt *= 5;
	} elsif($api =~ /action.deregister/) {
		$rt /= 5;
	} elsif($api =~ /getAdmin/) {
		$rt /= 10;
	}

	print "{";

	printf( "\"timestamp\":\"%d\", ",$timeStamp);
	print "\"browser\":\"$browser\", ";
	print "\"OS\":\"$OS\", ";
	print "\"http_verb\":\"$verb\", ";
	print "\"api_path\":\"${api}\", ";
	print "\"client_ip\":\"0:0:0:0:0:0:0:1\", ";
	print "\"response_time\":\"$rt\" ";

	print "}\n";
}
