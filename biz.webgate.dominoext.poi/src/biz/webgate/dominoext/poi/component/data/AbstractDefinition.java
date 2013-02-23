package biz.webgate.dominoext.poi.component.data;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;

import biz.webgate.dominoext.poi.utils.exceptions.POIException;
import biz.webgate.dominoext.poi.utils.logging.LoggerFactory;

import com.ibm.xsp.binding.MethodBindingEx;
import com.ibm.xsp.complex.ValueBindingObjectImpl;

public class AbstractDefinition extends ValueBindingObjectImpl {

	private MethodBinding m_ComputeValue;

	public MethodBinding getComputeValue() {
		return m_ComputeValue;
	}

	public void setComputeValue(MethodBinding computeValue) {
		m_ComputeValue = computeValue;
	}

	public Object executeComputeValue(FacesContext context, Object objAction,
			int count, String varObject, String varIndex) throws POIException {
		Logger logCurrent = LoggerFactory.getLogger(this.getClass()
				.getCanonicalName());

		try {
			logCurrent.info("ExecuteComputeValue started.");
			ArrayList<String> lstVars = new ArrayList<String>();
			lstVars.add(varObject);
			logCurrent.info("Var Name: " + varObject);
			lstVars.add(varIndex);
			logCurrent.info("Index Name:" + varIndex);
			ArrayList<Object> lstObject = new ArrayList<Object>();
			lstObject.add(objAction);
			lstObject.add(new Integer(count));
			logCurrent.info("Var Object: " + varObject);
			logCurrent.info("Index Object: " + count);
			if (m_ComputeValue != null) {
				if (m_ComputeValue instanceof MethodBindingEx) {
					((MethodBindingEx) m_ComputeValue)
							.setComponent(getComponent());
					((MethodBindingEx) m_ComputeValue).setParamNames(lstVars
							.toArray(new String[lstVars.size()]));
				}
				Object objRC = m_ComputeValue.invoke(context,
						lstObject.toArray());
				return objRC;
			}
		} catch (Exception e) {
			throw new POIException("Error during computeValue:" +e.getMessage(), e);
		}
		return null;
	}

}