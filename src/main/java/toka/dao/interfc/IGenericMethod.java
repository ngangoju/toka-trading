/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.dao.interfc;

import java.util.List;

/**
 *
 * @author Eric
 */
public interface IGenericMethod<Long, Object> {
	public Object saveObject(Object object);

	public List<Object> getListOfObjects();

	public Object getObjectById(int objectId, String primaryKeyclomunName);

	public Object updateObject(Object object);
}
