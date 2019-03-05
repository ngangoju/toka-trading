package toka.dao.impl;

import java.util.List;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.ICell;
import toka.domain.Cell;

/**
 *
 * @author Emmanuel
 */

public class CellImpl extends AbstractDao<Long, Cell> implements ICell {

	public Cell saveCell(Cell cell) {
		return saveIntable(cell);
	}

	public List<Cell> getListCells() {
		return (List<Cell>) (Object) getModelList();
	}

	public Cell getCellById(int cellId, String primaryKeyclomunName) {
		return (Cell) getModelById(cellId, primaryKeyclomunName);
	}

	public Cell UpdateCell(Cell cell) {
		return updateIntable(cell);
	}

}
