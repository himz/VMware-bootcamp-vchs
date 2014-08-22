#!/bin/bash

sh apiscript.sh http localhost 8080 api/getItems GET 200
sh apiscript.sh http localhost 8080 api/postItems POST 50
sh apiscript.sh http localhost 8080 api/putItems PUT 5
sh apiscript.sh http localhost 8080 api/postItems DELETE 16
