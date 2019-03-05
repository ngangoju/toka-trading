package toka.dao.generic;

import java.util.List;

public interface IRootDao<K, T> {
	int NO_OF_RECORDS = 10;
	int PAGE_INDEX = 1;

	/**
	 * 
	 * @param entity
	 * @throws Exception
	 */
	void persist(T entity) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T findById(K id) throws Exception;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	List<T> getEntitiesWithPagination() throws Exception;

	/**
	 * @param modelId
	 * @return
	 * @throws Exception
	 */
	T getModel(K modelId) throws Exception;

	/**
	 * @param modelId
	 * @return
	 * @throws Exception
	 */

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	T insert(T model) throws Exception;

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	T update(T model) throws Exception;

	/**
	 * @param startRecord
	 * @param numberOfRecord
	 * @return
	 * @throws Exception
	 */
	List<T> getList(int startRecord, int numberOfRecord) throws Exception;

	/**
	 * @param model
	 * @throws Exception
	 */
	void delete(T model) throws Exception;

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	int getNumberOfRecords(T model) throws Exception;

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	T saveOrUpdate(final T model) throws Exception;

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */

	T saveOnFlush(final T model) throws Exception;

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	T merge(final T model) throws Exception;

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	T mergeT(final T model) throws Exception;

	/**
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	T refresh(T model) throws Exception;

}
