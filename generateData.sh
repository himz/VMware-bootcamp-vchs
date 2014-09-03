#!/bin/bash

sh apiscript.sh http localhost 8080 vApp/power/action/powerOn POST 20
sh apiscript.sh http localhost 8080 vApp/power/action/powerOff PUT 20
sh apiscript.sh http localhost 8080 vApp/power/action/reset POST 150
sh apiscript.sh http localhost 8080 admin/extension/action/register POST 200
sh apiscript.sh http localhost 8080 admin/extension/action/deregister DELETE 10
sh apiscript.sh http localhost 8080 admin/extension/action/getAdmin GET 856
