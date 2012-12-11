package biz.webgate.dominoext.poi.component.sources;

import biz.webgate.dominoext.poi.component.data.ss.cell.IDefinition;

public interface IExportSource {

	public Object getValue(IDefinition idCurrent);

	public int accessNextRow();

	public int accessSource();

	public int closeSource();
}