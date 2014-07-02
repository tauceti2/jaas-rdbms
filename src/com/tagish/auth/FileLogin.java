// $Id: FileLogin.java,v 1.4 2002/05/21 19:46:40 andy Exp $
package com.tagish.auth;

import java.util.Map;
import java.io.*;
import java.util.*;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;

public class FileLogin extends SimpleLogin
{
	private String              pwdFile;
	private long                lastModified    = 0;
	private Hashtable           users           = null;

	private class User
	{
		char        password[];
		Vector      principals;
	}

	private void load(File f) throws Exception
	{
		//System.out.println("Reading " + f);
		lastModified = f.lastModified();
		BufferedReader r = new BufferedReader(new FileReader(f));
		users = new Hashtable();
		String l = r.readLine();
		while (l != null)
		{
			int hash = l.indexOf('#');
			if (hash != -1) l = l.substring(0, hash);
			l = l.trim();
			if (l.length() != 0)
			{
				StringTokenizer t = new StringTokenizer(l, ":");
				User u = new User();
				u.principals = new Vector();
				String user = t.nextToken();
				u.password = t.nextToken().toCharArray();
				u.principals.add(new TypedPrincipal(user, TypedPrincipal.USER));
				while (t.hasMoreTokens())
					u.principals.add(new TypedPrincipal(t.nextToken(), TypedPrincipal.GROUP));
				users.put(user, u);
			}
			l = r.readLine();
		}
		r.close();
	}

	private void reload() throws Exception
	{
		File f = new File(pwdFile);
		if (users == null || f.lastModified() != lastModified)
		   load(f);
	}

	protected synchronized Vector validateUser(String username, char password[]) throws LoginException
	{
		try {
			reload();
		} catch (Exception e) {
			throw new LoginException("Error reading " + pwdFile + " (" + e.getMessage() + ")");
		}

		if (users == null || !users.containsKey(username))
		   throw new AccountExpiredException("Unknown user");
		User u = (User) users.get(username);
		char pwd[];
		try {
			pwd = Utils.cryptPassword(password);
		} catch (Exception e) {
			throw new LoginException("Error encoding password (" + e.getMessage() + ")");
		}
		int c;
		for (c = 0; c < pwd.length && c < u.password.length; c++)
			if (pwd[c] != u.password[c])
			   break;
		if (c != pwd.length || c != u.password.length)
		   throw new FailedLoginException("Bad password");
		return u.principals;
	}

	public void initialize(Subject subject, CallbackHandler callbackHandler, Map sharedState, Map options)
	{
		super.initialize(subject, callbackHandler, sharedState, options);

		pwdFile = getOption("pwdFile", null);
		if (null == pwdFile)
		   throw new Error("A password file must be named (pwdFile=?)");
	}
}
