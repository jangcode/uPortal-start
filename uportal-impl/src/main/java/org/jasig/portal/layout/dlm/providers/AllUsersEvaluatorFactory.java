/* Copyright 2005 The JA-SIG Collaborative.  All rights reserved.
*  See license distributed with this file and
*  available online at http://www.uportal.org/license.html
*/

package org.jasig.portal.layout.dlm.providers;

import org.w3c.dom.Node;
import javax.persistence.Entity;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.QName;

import org.jasig.portal.layout.dlm.Evaluator;
import org.jasig.portal.layout.dlm.EvaluatorFactory;
import org.jasig.portal.layout.dlm.FragmentDefinition;
import org.jasig.portal.security.IPerson;

/**
 * Used to target a fragment to all users of the system including guest users.
 * 
 * @version $Revision$ $Date$
 * @since uPortal 2.5
 */
@Entity
public class AllUsersEvaluatorFactory extends Evaluator implements EvaluatorFactory
{
    public static final String RCS_ID = "@(#) $Header$";

    public Evaluator getEvaluator( Node audience )
    {
        return this;
    }
    
    public boolean isApplicable( IPerson p )
    {
        return true;
    }

    @Override
    public void toElement(Element parent) {

        // Assertions.
        if (parent == null) {
            String msg = "Argument 'parent' cannot be null.";
            throw new IllegalArgumentException(msg);
        }

        Element rslt = null;
        QName q = new QName("audience", FragmentDefinition.NAMESPACE);
        rslt = DocumentHelper.createElement(q);
        rslt.addAttribute("evaluatorFactory", this.getFactoryClass().getName());
        parent.add(rslt);

    }
    
    @Override
    public Class<? extends EvaluatorFactory> getFactoryClass() {
        return this.getClass();
    }

}
