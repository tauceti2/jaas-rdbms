NTLogin
{
	com.tagish.auth.win32.NTSystemLogin required returnNames=true returnSIDs=false;
};

FileLogin
{
	com.tagish.auth.FileLogin required debug=true pwdFile="D:\\Works\\Takhini\\TagishAuth\\passwd";
};

DBLogin
{
	com.tagish.auth.DBLogin required debug=true dbDriver="com.mysql.jdbc.Driver"
dbURL="jdbc:mysql://hostname/dbname" dbUser="username" dbPassword="password"
userTable="table_name" userColumn="user_name" passColumn="user_passwd";
};
