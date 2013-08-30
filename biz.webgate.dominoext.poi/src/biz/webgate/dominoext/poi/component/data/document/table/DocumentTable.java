package biz.webgate.dominoext.poi.component.data.document.table;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import biz.webgate.dominoext.poi.component.data.document.table.cell.DocCellValue;
import biz.webgate.dominoext.poi.component.data.document.table.cell.DocColumnDefinition;
import biz.webgate.dominoext.poi.component.data.ss.AbstractDataExporter;

import com.ibm.xsp.util.StateHolderUtil;

public class DocumentTable extends AbstractDataExporter {

	/*
	 * private String m_Var; private String m_Index; private Integer m_StepSize;
	 * private IExportSource m_DataSource; private String m_DataSourceVar;
	 */
	private Integer m_StartRow;
	private List<DocColumnDefinition> m_DocColumns;
	private String m_Name;
	private Integer m_MaxRow;
	private Integer m_TableNr;
	private List<DocCellValue> m_DocCellValues;


	public String getName() {
		if (m_Name != null) {
			return m_Name;
		}
		ValueBinding vb = getValueBinding("name");
		if (vb != null) {
			return (String) vb.getValue(getFacesContext());
		}
		return null;
	}

	public void setName(String name) {
		m_Name = name;
	}

	public List<DocCellValue> getDocCellValues() {
		return m_DocCellValues;
	}

	public void setDocCellValues(List<DocCellValue> docCellValues) {
		m_DocCellValues = docCellValues;
	}

	public void addDocCellValue(DocCellValue icvCurrent) {
		if (m_DocCellValues == null) {
			m_DocCellValues = new ArrayList<DocCellValue>();
		}
		m_DocCellValues.add(icvCurrent);
	}

	public int getStartRow() {
		if (m_StartRow != null) {
			return m_StartRow;
		}
		ValueBinding vb = getValueBinding("startRow");
		if (vb != null) {
			Integer intValue = (Integer) vb.getValue(getFacesContext());
			if (intValue != null)
				return intValue;
		}
		return 0;

	}

	public void setStartRow(int startRow) {
		m_StartRow = startRow;
	}


	public List<DocColumnDefinition> getDocColumns() {
		return m_DocColumns;
	}

	public void setDocColumns(List<DocColumnDefinition> docColumns) {
		m_DocColumns = docColumns;
	}

	public void addDocColumn(DocColumnDefinition cdCurrent) {
		if (m_DocColumns == null) {
			m_DocColumns = new ArrayList<DocColumnDefinition>();
		}
		m_DocColumns.add(cdCurrent);
	}

	public int getMaxRow() {
		if (m_MaxRow != null) {
			return m_MaxRow;
		}
		ValueBinding vb = getValueBinding("maxRow");
		if (vb != null) {
			Integer intValue = (Integer) vb.getValue(getFacesContext());
			if (intValue != null)
				return intValue;
		}
		return 100;

	}

	public void setMaxRow(int maxRow) {
		m_MaxRow = maxRow;
	}

	public int getTableNr() {
		return m_TableNr;
	}

	public void setTableNr(int tableNr) {
		m_TableNr = tableNr;
	}
	/*
	 * public IExportSource getDataSource() { return m_DataSource; }
	 * 
	 * public void setDataSource(IExportSource dataSource) { m_DataSource =
	 * dataSource; }
	 * 
	 * public int getStepSize() { if (m_StepSize != null) { return m_StepSize; }
	 * ValueBinding vb = getValueBinding("stepSize"); if (vb != null) { Integer
	 * intValue = (Integer) vb.getValue(getFacesContext()); if (intValue !=
	 * null) return intValue; } return 1; }
	 * 
	 * public void setStepSize(int stepSize) { m_StepSize = stepSize; }
	 * 
	 * public String getVar() { return m_Var; }
	 * 
	 * public void setVar(String var) { m_Var = var; }
	 * 
	 * public String getIndex() { return m_Index; }
	 * 
	 * public void setIndex(String index) { m_Index = index; }
	 * 
	 * public String getDataSourceVar() { if (m_DataSourceVar != null) { return
	 * m_DataSourceVar; } ValueBinding vb = getValueBinding("dataSourceVar"); if
	 * (vb != null) { String strValue = (String) vb.getValue(getFacesContext());
	 * if (strValue != null) return strValue; } return null;
	 * 
	 * }
	 * 
	 * public void setDataSourceVar(String dataSourceVar) { m_DataSourceVar =
	 * dataSourceVar; }
	 * 
	 * public DataSource getPageDataSource() { String strName =
	 * getDataSourceVar(); System.out.println(strName); if
	 * (StringUtil.isNotEmpty(strName)) {
	 * 
	 * UIViewRoot vrCurrent = getFacesContext().getViewRoot(); if (vrCurrent
	 * instanceof UIViewRootEx) { for (DataSource dsCurrent : ((UIViewRootEx)
	 * vrCurrent).getData()) { if (strName.equals(dsCurrent.getVar())) { return
	 * dsCurrent; } } } } System.out.println("Datasource name:" +
	 * m_DataSourceVar); return null;
	 * 
	 * }
	 */



	@Override
	public Object saveState(FacesContext context) {
		try {

			Object[] state = new Object[7];
			state[0] = super.saveState(context);
			state[1] = m_Name;
			state[2] = StateHolderUtil.saveList(context, m_DocCellValues);
			state[3] = m_StartRow;
			state[4] = StateHolderUtil.saveList(context, m_DocColumns);
			state[5] = m_MaxRow;
			state[6] = m_TableNr;
			/*
			 * state[5] = m_StepSize; state[6] =
			 * FacesUtil.objectToSerializable(getFacesContext(), m_DataSource);
			 * state[7] = m_DataSourceVar; state[8] = m_Var; state[9] = m_Index;
			 */
			return state;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void restoreState(FacesContext context, Object arg1) {
		Object[] state = (Object[]) arg1;
		super.restoreState(context, state[0]);
		m_Name = (String) state[1];
		m_DocCellValues = StateHolderUtil.restoreList(context, getComponent(), state[2]);
		m_StartRow = (Integer) state[3];
		m_DocColumns = StateHolderUtil.restoreList(context, getComponent(), state[4]);
		m_MaxRow = (Integer) state[5];
		m_TableNr = (Integer) state[6];
	}


}