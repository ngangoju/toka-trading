/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.dao.interfc;

import toka.domain.Documents;
import java.util.List;

/**
 *
 * @author Emmanuel
 */
public interface IDocuments {
	public Documents saveDocuments(Documents document);

	public List<Documents> getListDocuments();

	public Documents getDocumentById(int DocId, String primaryKeyclomunName);

	public Documents UpdateDocuments(Documents document);

	public Documents getDocumentWithQuery(final String[] propertyName, final Object[] value, final String hqlStatement);
}