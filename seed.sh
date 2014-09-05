sh apiscript.sh http localhost 8080 vApp/owner POST 10 B1 OS1
sh apiscript.sh http localhost 8080 vApp/question POST 20 B2 OS3
sh apiscript.sh http localhost 8080 vApp/productSections POST 25 B3 OS3
sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/memory POST 30 B2 OS2
sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/cpu POST 45 B1 OS3
sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/disks POST 30 B2 OS2

sh apiscript.sh http localhost 8080 vApp/power/work/powerOn POST 35 B1 OS1
sh apiscript.sh http localhost 8080 vApp/power/work/powerOff POST 40 B2 OS3
sh apiscript.sh http localhost 8080 vApp/power/work/reset POST 22 B3 OS2
sh apiscript.sh http localhost 8080 admin/extension/action/register POST 32 B2 OS3
sh apiscript.sh http localhost 8080 admin/extension/action/deregister POST 15 B3 OS1
sh apiscript.sh http localhost 8080 admin/extension/action/getAdmin POST 30 B1 OS3

sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/cpu GET 45 B1 OS3
sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/disks GET 30 B2 OS2

sh apiscript.sh http localhost 8080 vApp/power/work/powerOn GET 35 B1 OS1
sh apiscript.sh http localhost 8080 vApp/power/work/powerOff GET 40 B2 OS3
sh apiscript.sh http localhost 8080 vApp/power/work/reset GET 22 B3 OS2
sh apiscript.sh http localhost 8080 admin/extension/action/register GET 32 B2 OS3
sh apiscript.sh http localhost 8080 admin/extension/action/deregister GET 15 B3 

sh apiscript.sh http localhost 8080 admin/extension/action/register PUT 32 B2 OS3
sh apiscript.sh http localhost 8080 admin/extension/action/deregister PUT 15 B3 OS1
sh apiscript.sh http localhost 8080 admin/extension/action/getAdmin PUT 30 B1 OS3

sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/cpu PUT 45 B1 OS3
sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/disks PUT 30 B2 OS2

sh apiscript.sh http localhost 8080 vApp/power/work/powerOn PUT 35 B1 OS1


sh apiscript.sh http localhost 8080 vApp/owner DELETE 10 B1 OS1
sh apiscript.sh http localhost 8080 vApp/question DELETE 20 B2 OS3
sh apiscript.sh http localhost 8080 vApp/productSections DELETE 25 B3 OS3
sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/memory DELETE 30 B2 OS2
sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/cpu DELETE 45 B1 OS3
sh apiscript.sh http localhost 8080 vApp/virtualHardwareSection/disks DELETE 30 B2 OS2

sh apiscript.sh http localhost 8080 vApp/power/work/powerOn POST 35 B1 OS1