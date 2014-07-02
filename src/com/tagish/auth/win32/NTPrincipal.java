// $Id: NTPrincipal.java,v 1.6 2003/02/17 20:13:23 andy Exp $
package com.tagish.auth.win32;

import com.tagish.auth.*;
import java.io.*;
import java.security.Principal;

/**
 * NTPrincipals are used to represent the Principals that are returned
 * by the com.tagish.auth.win32.NTSystemLogin module. They are derived
 * from, and can be treated like Principals but they also contain
 * extra information about the type of the Principal which can be
 * USER, GROUP or DOMAIN. I'm not 100% certain that this is a good way
 * of doing things. Suggestions welcome.
 *
 * @author Andy Armstrong, <A HREF="mailto:andy@tagish.com">andy@tagish.com</A>
 * @version 1.0.3
 */
public class NTPrincipal extends TypedPrincipal
{
	/**
	 * Create a NTPrincipal with a name and type.
	 *
	 * <p>
	 *
	 * @param name the name for this principal.
	 * @param type the type for this principal.
	 * @exception NullPointerException if the <code>name</code>
	 * 			is <code>null</code>.
	 */
	public NTPrincipal(String name, int type)
	{
		super(name, type);
	}

	/**
	 * Create a NTPrincipal with a name.
	 *
	 * <p>
	 *
	 * @param name the name for this Principal.
	 * @exception NullPointerException if the <code>name</code>
	 * 			is <code>null</code>.
	 */
	public NTPrincipal(String name)
	{
		super(name);
	}

    public NTPrincipal()
    {
		super();
	}
}