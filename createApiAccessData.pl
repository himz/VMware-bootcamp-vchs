#!/usr/bin/perl
if($#ARGV != 0) {
	print "Usage: $0 <numRecords>\n";
	exit;
}

# generate random data for mongoDB 
# Format
#{
#    "timestamp":"YYYY/MM/DD/HH/MM/SS",
#    "browser":"chrome/safari/firefox/ie/other",
#    "OS":"",
#    "user":"",
#    "tenant":"",
#    "http_verb":"",
#    "api_path":"",
#    "api_identifier":"",
#    "client_ip":"",
#    "response_time":"",
#    "response_code":"",
#    "server_id":""
#}

my(@browsers) = (chrome,safari,firefox,ie,other);
my(@OSs) = (Mac,Windows,Linux,other);
my(@users) = (Varun,Prashanth,Sabs,Himanshu);
my(@verbs) = (POST,GET,HEAD);
my(@apis) = (A1,A2,A3,A4,A5);


for(my($i) = 0; $i < $ARGV[0]; $i++) {

	($sec,$min,$hour,$mday,$mon,$year,$wday,$yday,$isdst) = localtime();
	my($browser) = $browsers[int(rand($#browser))];
	my($OS) = $OSs[int(rand($#OSs))];
	my($user) = $users[int(rand($#users))];
	my($verb) = $verbs[int(rand($#verbs))];
	my($api) = $apis[int(rand($#api))];
	my($rt) = int(rand(100));

	print "{\n";

	printf( "\"timestamp\":\"%04d/%02d/%02d/%02d/%02d/%02d\",\n",$year+1900,$mon,$mday,$hour,$min,$sec);
	print "\"browser\":\"$browser\"\n";
	print "\"OS\":\"$OS\"\n";
	print "\"users\":\"$user\"\n";
	print "\"http_verb\":\"$verb\"\n";
	print "\"api_path\":\"$api\"\n";
	print "\"response_time\":\"$rt\"\n";

	print "}\n";
}
