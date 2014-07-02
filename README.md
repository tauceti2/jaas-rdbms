jaas-rdbms
==========

JAAS authentication module using RDBMS as a backend

= Compilation =

./make

= Installtion =

copy tagishauth.jar into /var/lib/tomcat/webapps/application/WEB-INF/lib/

= Usage =

example of usage of the module is in tagish.login

Usage of DBLogin, parameters:
userColumn = column where is user id
userPaaswd = columnt where is user password

com.tagish.auth.DBLogin required debug=true dbDriver="com.mysql.jdbc.Driver" dbURL="jdbc:mysql://hostname/dbname" dbUser="username" dbPassword="password" userTable="table_name" userColumn="user_name" userPasswd="user_passwd";

