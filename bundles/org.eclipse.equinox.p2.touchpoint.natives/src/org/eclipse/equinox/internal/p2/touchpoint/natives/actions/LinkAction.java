/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 ******************************************************************************/
package org.eclipse.equinox.internal.p2.touchpoint.natives.actions;

import java.io.IOException;
import java.util.Map;
import org.eclipse.core.runtime.*;
import org.eclipse.equinox.internal.p2.touchpoint.natives.Messages;
import org.eclipse.equinox.internal.p2.touchpoint.natives.Util;
import org.eclipse.equinox.internal.provisional.p2.engine.ProvisioningAction;
import org.eclipse.osgi.util.NLS;

public class LinkAction extends ProvisioningAction {
	public static final String ID = "ln"; //$NON-NLS-1$
	public static final String PARM_TARGET_DIR = "targetDir"; //$NON-NLS-1$
	public static final String PARM_LINK_NAME = "linkName"; //$NON-NLS-1$
	public static final String PARM_LINK_TARGET = "linkTarget"; //$NON-NLS-1$
	public static final String PARM_LINK_FORCE = "force"; //$NON-NLS-1$

	public IStatus execute(Map parameters) {
		String targetDir = (String) parameters.get(PARM_TARGET_DIR);
		if (targetDir == null)
			return Util.errorStatus(NLS.bind(Messages.param_not_set, PARM_TARGET_DIR, ID), null);

		String linkTarget = (String) parameters.get(PARM_LINK_TARGET);
		if (linkTarget == null)
			return Util.errorStatus(NLS.bind(Messages.param_not_set, PARM_LINK_TARGET, ID), null);

		String linkName = (String) parameters.get(PARM_LINK_NAME);
		if (linkName == null)
			return Util.errorStatus(NLS.bind(Messages.param_not_set, PARM_LINK_NAME, ID), null);

		String force = (String) parameters.get(PARM_LINK_FORCE);

		ln(targetDir, linkTarget, linkName, Boolean.valueOf(force).booleanValue());
		return Status.OK_STATUS;
	}

	public IStatus undo(Map parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	private void ln(String targetDir, String linkTarget, String linkName, boolean force) {
		Runtime r = Runtime.getRuntime();
		try {
			r.exec(new String[] {"ln", "-s" + (force ? "f" : ""), linkTarget, targetDir + IPath.SEPARATOR + linkName}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
