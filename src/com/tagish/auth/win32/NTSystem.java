// $Id: NTSystem.java,v 1.6 2003/02/17 20:13:23 andy Exp $
package com.tagish.auth.win32;

import javax.security.auth.login.LoginException;

/**
 * Proxy class that wraps up the native win32 functionality
 *
 * @author Andy Armstrong, <A HREF="mailto:andy@tagish.com">andy@tagish.com</A>
 * @version 1.0.3
 */
public class NTSystem
{
	private final static int VERSION = 101;
	private int _mem;			// 32 bit pointer to memory used by the native code

	static
	{
		System.loadLibrary("NTSystem");
	}

	public NTSystem()
	{
		_mem = 0;				// set to NULL;
	}

	public void checkVersion()
	{
		if (getVersion() != VERSION)
		   throw new Error("Bad DLL version (DLL is " + getVersion() +
										", I am " + VERSION + ")");
	}

	/**
	 * Return the DLL version
	 */
	public synchronized native int getVersion();

	/**
	 * Clean up NT resources during garbage collection, in case
	 * LoginContext.logout() was not called.
	 */
	protected synchronized native void finalize();

	/**
	 * Get the domain for the current NT user.
	 */
	public synchronized native String getDomain();

	/**
	 * Get a printable SID for the current NT user's domain.
	 */
	public synchronized native String getDomainSID();

	/**
	 * Get the printable group SIDs for the current NT user.
	 */
	public synchronized native String[] getGroupIDs();

	/**
	 * Get the printable group names for the current NT user.
	 */
	public synchronized native String[] getGroupNames(boolean bSidIfUnavailable);

	/**
	 * Get an impersonation token for the current NT user.
	 */
	public synchronized native int getImpersonationToken();

	/**
	 * Get the username for the current NT user.
	 */
	public synchronized native String getName();

	/**
	 * Get a printable primary group SID for the current NT user.
	 */
	public synchronized native String getPrimaryGroupID();

	/**
	 * Get a printable primary group name for the current NT user.
	 */
	public synchronized native String getPrimaryGroupName();

	/**
	 * Get a printable SID for the current NT user.
	 */
	public synchronized native String getUserSID();

	/**
	 * Attempt to log a user on. Once the user is logged on other methods
	 * in this class will return information about the specified user rather
	 * than the current user.
	 */
	public synchronized native void logon(String username, char password[], String domain) throws LoginException;

	/**
	 * Log the user off. This call returns this object to the state it was
	 * in before a call to logon()
	 */
	public synchronized native void logoff();
}
