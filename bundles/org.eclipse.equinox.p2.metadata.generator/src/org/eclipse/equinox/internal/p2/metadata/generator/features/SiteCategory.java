/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.internal.p2.metadata.generator.features;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;

/**
 * A category in an update site.
 * 
 * Based on org.eclipse.update.core.model.CategoryModel.
 */
public class SiteCategory {

	private static Comparator comp;
	private String description;
	private String label;
	private String name;

	/**
	 * Returns a comparator for category models.
	 * 
	 * @return comparator
	 * @since 2.0
	 */
	public static Comparator getComparator() {
		if (comp == null) {
			comp = new Comparator() {
				/*
				 * @see Comparator#compare(Object,Object)
				 * Returns 0 if versions are equal.
				 * Returns -1 if object1 is after than object2.
				 * Returns +1 if object1 is before than object2.
				 */
				public int compare(Object o1, Object o2) {

					SiteCategory cat1 = (SiteCategory) o1;
					SiteCategory cat2 = (SiteCategory) o2;

					if (cat1.equals(cat2))
						return 0;
					return cat1.getName().compareTo(cat2.getName());
				}
			};
		}
		return comp;
	}

	/**
	 * Creates an uninitialized model object.
	 * 
	 * @since 2.0
	 */
	public SiteCategory() {
		super();
	}

	/**
	 * Compare two category models for equality.
	 * 
	 * @see Object#equals(Object)
	 * @since 2.0
	 */
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof SiteCategory) {
			SiteCategory otherCategory = (SiteCategory) obj;
			result = getName().equalsIgnoreCase(otherCategory.getName());
		}
		return result;
	}

	/**
	 * Retrieve the detailed category description
	 * 
	 * @return category description, or <code>null</code>.
	 * @since 2.0
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Retrieve the non-localized displayable label for the category.
	 * 
	 * @return non-localized displayable label, or <code>null</code>.
	 * @since 2.0
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Retrieve the name of the category.
	 * 
	 * @return category name, or <code>null</code>.
	 * @since 2.0
	 */
	public String getName() {
		return name;
	}

	/**
	 * Compute hash code for category model.
	 * 
	 * @see Object#hashCode()
	 * @since 2.0
	 */
	public int hashCode() {
		return getName().hashCode();
	}

	/**
	 * Resolve the model object.
	 * Any URL strings in the model are resolved relative to the 
	 * base URL argument. Any translatable strings in the model that are
	 * specified as translation keys are localized using the supplied 
	 * resource bundle.
	 * 
	 * @param base URL
	 * @param bundleURL resource bundle URL
	 * @exception MalformedURLException
	 * @since 2.0
	 */
	public void resolve(URL base, URL bundleURL) throws MalformedURLException {
		// resolve local elements
		//		localizedLabel = resolveNLString(bundleURL, label);

		// delegate to references
		//		resolveReference(getDescriptionModel(), base, bundleURL);
	}

	/**
	 * Sets the category description.
	 * Throws a runtime exception if this object is marked read-only.
	 * 
	 * @param description category description
	 * @since 2.0
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the category displayable label.
	 * Throws a runtime exception if this object is marked read-only.
	 * 
	 * @param label displayable label, or resource key
	 * @since 2.0
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Sets the category name.
	 * Throws a runtime exception if this object is marked read-only.
	 * 
	 * @param name category name
	 * @since 2.0
	 */
	public void setName(String name) {
		this.name = name;
	}

}
