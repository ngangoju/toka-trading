package toka.dao.generic;

import toka.domain.UserCategory;
import toka.domain.Users;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class AbstractDao<K, T> implements IRootDao<K, T> {

	private static SessionFactory factory;
	protected Session session = null;
	public Transaction t = null;
	protected Class<T> entityClass;
	private String CLASSNAME = "AbstractDao :: ";
	private static final java.util.logging.Logger LOGGER = java.util.logging.Logger
			.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());
	private static final int TRIM_CYCLE = 4;
	private static final String PERSISTANT_CLASS_IS_NULL = "Persistent Class is null";
	private static final String WHERE_CLAUSE = " where ";
	private static final String FROM_CLAUSE = " from ";
	private static final String WHERE_PROPERTY_HOLDER = "=?";
	private static final String ORDER_CLAUSE = " order by  ";

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];

	}

	public void configConnection() {

		try {

			factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(entityClass)
					.buildSessionFactory();
			session = factory.openSession();
			System.out.println("Create Hibernate  sessionFactory  Started.. for " + entityClass.getSimpleName());
		} catch (Throwable ex) {
			System.err.println("Failed to create Hibernate sessionFactory :::" + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public List<Object[]> reportList(String AnySQLQuerry) {
		configConnection();
		Transaction t = session.beginTransaction();
		Query q = session.createQuery(AnySQLQuerry);
		List<Object[]> recs = q.list();
		session.close();
		factory.close();
		return recs;
	}

	public List<Object[]> reportListInt(String AnySQLQuerry) {
		configConnection();
		Transaction t = session.beginTransaction();
		Query q = session.createQuery(AnySQLQuerry);
		System.out.println("ds");
		List<Object[]> recs = (List<Object[]>) q.list();
		session.close();
		factory.close();
		return recs;
	}

	public Long selectByCount(String AnySQLQuerry) {
		configConnection();
		Transaction t = session.beginTransaction();
		long q = (Long) session.createQuery(AnySQLQuerry).uniqueResult();

		session.close();
		factory.close();
		return q;
	}

	public int selectByCountDays(String AnySQLQuerry) {
		configConnection();
		Transaction t = session.beginTransaction();
		int q = (Integer) session.createQuery(AnySQLQuerry).uniqueResult();

		session.close();
		factory.close();
		return q;
	}

	/*
	 * protected Session getMyCurrentSession() { session.close();
	 * System.out.println("Create   Hibernate   session Started.. "); Session
	 * session = factory.openSession();
	 * 
	 * System.out.println("Hibernate session Created.. "); factory.close();
	 * session.setFlushMode(FlushMode.AUTO); return session;
	 * 
	 * }
	 */

	// @SuppressWarnings("rawtypes")
	public List getGenericListWithHQLParameter(final String[] propertyName, final Object[] value,
			final String hqlStatement, final String orderSatment) throws Exception {
		configConnection();
		Transaction t = session.beginTransaction();
		if (entityClass == null) {
			throw new Exception(PERSISTANT_CLASS_IS_NULL);
		}
		try {
			StringBuilder strQuery = new StringBuilder();
			strQuery.append(FROM_CLAUSE);
			strQuery.append(hqlStatement);
			strQuery.append(WHERE_CLAUSE);

			for (int i = 0; i < propertyName.length; i++) {
				strQuery.append(propertyName[i]);
				strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
			}

			strQuery.delete(strQuery.length() - TRIM_CYCLE, strQuery.length() - 1);
			strQuery.append(ORDER_CLAUSE);
			strQuery.append(orderSatment);
			strQuery.append(") ");

			Query query = session.createQuery(strQuery.toString());
			LOGGER.info("the querry ::" + strQuery.toString());
			for (int i = 0; i < value.length; i++) {
				query.setParameter(i, value[i]);
			}
			if (!t.wasCommitted()) {
				t.commit();
			}
			// session.close();
			// factory.close();
			LOGGER.info("list size :::" + query.list().size());
			return query.list();

		} catch (HibernateException hibex) {
			if (!t.wasCommitted()) {
				t.begin();
			}
			// session.close();
			// factory.close();
			LOGGER.info(hibex.getMessage() + hibex.getCause());
			throw new Exception(hibex);

		} catch (Exception ex) {
			LOGGER.info("System Error has occured. " + ex);
			throw new Exception(ex);
		} finally {
			session.close();
			factory.close();
		}
	}

	public List<T> getListWithHQL(final String hqlStatement) throws Exception {
		return getListWithHQL(hqlStatement, 0, 0);
	}

	@SuppressWarnings("unchecked")
	public List<T> getListWithHQL(final String hqlStatement, final int startRecord, final int numberOfRecord)
			throws Exception {
		try {
			configConnection();
			Transaction t = session.beginTransaction();
			Query query = this.session.createQuery(hqlStatement);

			if (numberOfRecord != 0) {
				query.setFirstResult(startRecord);
				query.setMaxResults(numberOfRecord);
			}
			return query.list();

		} catch (HibernateException hibex) {
			LOGGER.info(hibex.getMessage() + hibex.getCause());
			throw new Exception(hibex);
		} catch (Exception ex) {
			LOGGER.info("System Error has occured. {}" + ex);
			System.out.println("System Error has occured. ::" + ex.getMessage().toString());
			// ex.printStackTrace();
			throw new Exception(ex);
		}
	}

	public List getGenericListWithHQLParameterEndByEnd(final String[] propertyName, final Object[] value,
			final String hqlStatement, final String[] propertyName2, final Object[] value2, final String orderSatment)
			throws Exception {
		configConnection();
		Transaction t = session.beginTransaction();
		if (entityClass == null) {
			throw new Exception(PERSISTANT_CLASS_IS_NULL);
		}
		try {
			StringBuilder strQuery = new StringBuilder();
			strQuery.append(FROM_CLAUSE);
			strQuery.append(hqlStatement);
			strQuery.append(WHERE_CLAUSE);

			for (int i = 0; i < propertyName.length; i++) {
				strQuery.append(propertyName[i]);
				strQuery.append(WHERE_PROPERTY_HOLDER + " or ");
			}
			strQuery.delete(strQuery.length() - TRIM_CYCLE, strQuery.length() - 1);
			for (int i2 = 0; i2 < propertyName2.length; i2++) {
				strQuery.append(" and ");
				strQuery.append(propertyName2[i2]);
				strQuery.append(WHERE_PROPERTY_HOLDER + " and ");

			}

			strQuery.delete(strQuery.length() - TRIM_CYCLE, strQuery.length() - 1);
			strQuery.append(ORDER_CLAUSE);
			strQuery.append(orderSatment);
			strQuery.append(") ");

			Query query = session.createQuery(strQuery.toString());
			int i = 0;
			for (i = 0; i < value.length; i++) {
				query.setParameter(i, value[i]);
			}
			for (int i2 = 0; i2 < value2.length; i2++) {
				query.setParameter(i, value2[i2]);
			}
			if (!t.wasCommitted()) {
				t.begin();
			}
			// session.close();
			// factory.close();
			return query.list();

		} catch (HibernateException hibex) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();
			LOGGER.info(hibex.getMessage() + hibex.getCause());
			throw new Exception(hibex);

		} catch (Exception ex) {
			LOGGER.info("System Error has occured. " + ex);
			throw new Exception(ex);
		} finally {
			session.close();
			factory.close();
		}
	}

	public List getGenericListWithHQLParameteroOfOrCondition(final String[] propertyName, final Object[] value,
			final String hqlStatement, final String orderSatment) throws Exception {
		configConnection();
		Transaction t = session.beginTransaction();
		if (entityClass == null) {
			throw new Exception(PERSISTANT_CLASS_IS_NULL);
		}
		try {
			StringBuilder strQuery = new StringBuilder();
			strQuery.append(FROM_CLAUSE);
			strQuery.append(hqlStatement);
			strQuery.append(WHERE_CLAUSE);

			for (int i = 0; i < propertyName.length; i++) {
				strQuery.append(propertyName[i]);
				strQuery.append(WHERE_PROPERTY_HOLDER + " or ");
			}

			strQuery.delete(strQuery.length() - TRIM_CYCLE, strQuery.length() - 1);
			strQuery.append(ORDER_CLAUSE);
			strQuery.append(orderSatment);
			strQuery.append(") ");

			Query query = session.createQuery(strQuery.toString());

			for (int i = 0; i < value.length; i++) {
				query.setParameter(i, value[i]);
			}
			if (!t.wasCommitted()) {
				t.begin();
			}
			// session.close();
			// factory.close();
			return query.list();

		} catch (HibernateException hibex) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();
			LOGGER.info(hibex.getMessage() + hibex.getCause());
			throw new Exception(hibex);

		} catch (Exception ex) {
			LOGGER.info("System Error has occured. " + ex);
			throw new Exception(ex);
		} finally {
			session.close();
			factory.close();
		}
	}

	public List<T> getListByDateBewteenOtherCriteria(String dateColumn, Date startDate, Date endDate,
			final String[] propertyName, final Object[] value) throws ParseException {
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date fromDate = df.parse(startDate);
		// Date toDate = df.parse(endDate);

		// Session session = factory.getCurrentSession();
		configConnection();
		Transaction t = session.beginTransaction();
		try {

			Criteria cr = session.createCriteria(entityClass);
			cr.add(Restrictions.between(dateColumn, startDate, endDate));
			for (int i = 0; i < propertyName.length; i++) {
				for (int ii = 0; ii < value.length; ii++) {
					cr.add(Restrictions.eq(propertyName[i], value[ii]));
				}
			}
			if (!t.wasCommitted()) {
				t.commit();
			}

			return cr.list();
		} catch (Exception mm) {
			mm.printStackTrace();
			if (!t.wasCommitted()) {
				t.commit();
			}

		} finally {
			session.close();
			factory.close();
		}
		return null;
	}

	public T getModelWithMyHQL(final String[] propertyName, final Object[] value, final String hqlStatement)
			throws Exception {

		// Session session = factory.getCurrentSession();
		configConnection();
		System.out.println("select method start::::");
		Transaction t = session.beginTransaction();
		if (entityClass == null) {
			throw new Exception(PERSISTANT_CLASS_IS_NULL);
		}
		try {

			StringBuilder strQuery = new StringBuilder();
			strQuery.append(hqlStatement);
			strQuery.append(WHERE_CLAUSE);

			for (int i = 0; i < propertyName.length; i++) {
				strQuery.append(propertyName[i]);
				strQuery.append(WHERE_PROPERTY_HOLDER + " and ");
			}

			strQuery.delete(strQuery.length() - TRIM_CYCLE, strQuery.length() - 1);

			Query query = session.createQuery(strQuery.toString());

			for (int i = 0; i < value.length; i++) {
				query.setParameter(i, value[i]);
			}

			return (T) query.uniqueResult();
		} catch (HibernateException hibex) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			// session.close();
			System.out.println("select method start done ::::");
			factory.close();
			System.out.println("select method start:::: session status for select" + session.isOpen());
			LOGGER.info(hibex.getMessage() + hibex);
			throw new Exception(hibex);
		} catch (Exception ex) {
			LOGGER.info("System Error has occured. " + ex);
			throw new Exception(ex);
		} finally {

			if (!t.wasCommitted()) {
				t.commit();
				session.close();
				factory.close();
			}

		}
	}

	public T saveIntable(T model) {
		configConnection();
		LOGGER.info(CLASSNAME + "saveIntable  start :::");
		System.out.println("Transaction Started.. for save config session status>>>" + session.isOpen());

		// Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {

			session.save(model);
			t.commit();

			LOGGER.info(CLASSNAME + "Save done::");
			return model;
		} catch (Exception k) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			// session.close();
			factory.close();

			k.printStackTrace();
			if (t != null) {
				t.rollback();
			}
		} finally {
			if (!t.wasCommitted()) {
				// t.commit();
			}

			session.close();
			factory.close();

		}

		return model;

	}

	public long getLastRecordId(String sql) {
		// final String sql = "SELECT max( i.id ) FROM Item i";

		// Session session = factory.getCurrentSession();
		configConnection();
		Transaction t = session.beginTransaction();
		try {

			return (Long) session.createQuery(sql).uniqueResult();
		} catch (Exception k) {
		} finally {
			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();
			return 0;
		}

	}

	public T updateIntable(T model) {

		// Session session = factory.getCurrentSession();
		configConnection();
		Transaction t = session.beginTransaction();
		try {
			LOGGER.info("Transaction Started.. for update config");

			session.update(model);
			t.commit();

			return model;

		} catch (Exception j) {
			if (!t.wasCommitted()) {
				// t.commit();
			}
			// session.close();
			// factory.close();

		} finally {
			if (!t.wasCommitted()) {
				// t.commit();
			}
			session.close();
			factory.close();
		}

		return model;
	}

	public T deleteIntable(T model) {

		// Session session = factory.getCurrentSession();
		configConnection();
		Transaction t = session.beginTransaction();
		try {
			System.out.println("Transaction Started.. for delete config");

			session.delete(model);
			t.commit();

			return model;

		} catch (Exception j) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			// session.close();
			// factory.close();

		} finally {
			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();
		}

		return model;
	}

	public Object getLongIn(String userName, String password) {
		configConnection();

		Criteria cr;

		// Session session = factory.getCurrentSession();

		Transaction t = session.beginTransaction();

		Object val = new Object();

		try {

			cr = session.createCriteria(entityClass.getClass());
			cr.add(Restrictions.eq("password", password));
			cr.add(Restrictions.eq("userName", userName));

			List a = cr.list();
			// System.out.println("Here we are!!!" + session.isOpen());
			// System.out.println("Here we are!!!" + factory.isClosed());
			for (Iterator iterator = a.iterator(); iterator.hasNext();) {
				val = (Class<T>) iterator.next();

			}
			// System.out.println("Here we are2!!!" + session.isOpen());
			// System.out.println("Here we are2!!!" + factory.isClosed());
			return val;
		} catch (Exception jj) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			factory.close();
			session.close();
			System.out.println("IN check paasword METHOD ERROR ");
			System.out.println(jj.getMessage());
			jj.printStackTrace();

		} finally {

			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();
			return val;
		}

	}

	public List<T> getListByDateBewteen(String dateColumn, Date startDate, Date endDate) throws ParseException {
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// Date fromDate = df.parse(startDate);
		// Date toDate = df.parse(endDate);

		// Session session = factory.getCurrentSession();
		configConnection();
		Transaction t = session.beginTransaction();

		try {

			Criteria criteria = session.createCriteria(entityClass)
					.add(Restrictions.between(dateColumn, startDate, endDate));

			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			if (!t.wasCommitted()) {
				t.commit();
			}
			factory.close();
			session.close();

		} finally {

			if (!t.wasCommitted()) {
				t.commit();
			}
			factory.close();
			session.close();

		}
		return null;
	}

	public boolean creatingNewTable() {
		configConnection();
		// Session session = getMyCurrentSession();

		return true;
	}

	public List<Object> getModelList() {
		configConnection();
		// Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List ite = null;

		try {
			// t = session.beginTransaction();
			ite = session.createQuery("FROM " + entityClass.getName()).list();

		} catch (HibernateException e) {

			if (!t.wasCommitted()) {
				t.commit();
			}
			factory.close();
			session.close();

			e.printStackTrace();
		} finally {

			if (!t.wasCommitted()) {
				t.commit();
			}
			// factory.close();
			// session.close();

		}
		return ite;

	}

	public Object getModelById(int modelId, String PrimaryKeycolumnName) {
		configConnection();
		Criteria cr;
		Object ob = null;

		// Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		LOGGER.info("here i am in getModelById!!");

		try {

			cr = session.createCriteria(entityClass.getName());
			cr.add(Restrictions.eq(PrimaryKeycolumnName, modelId));
			List a = cr.list();
			for (Iterator iterator = a.iterator(); iterator.hasNext();) {
				ob = iterator.next();
				System.out.println("here i am!! test");
				LOGGER.info("here in for loop!!");
			}

		} catch (Exception jj) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();

			jj.printStackTrace();
			// t.rollback();
			if (t != null) {
				t.rollback();
			}
		} finally {

			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();

		}

		return ob;
	}

	public Object getLongInUserDeails(String userName) {
		configConnection();

		Criteria cr;

		// Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		Object val = new Object();

		try {
			// t= session.beginTransaction();
			cr = session.createCriteria(entityClass);

			cr.add(Restrictions.eq("userName", userName));

			List a = cr.list();
			for (Iterator iterator = a.iterator(); iterator.hasNext();) {
				val = (Class<T>) iterator.next();

			}

		} catch (Exception jj) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();

			jj.printStackTrace();
		} finally {

			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();
		}

		return val;
	}

	public List<Users> getUserListByCat(UserCategory category) {
		configConnection();
		Criteria cr;
		List a = null;

		// Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			// t= session.beginTransaction();
			cr = session.createCriteria(entityClass);

			cr.add(Restrictions.eq("userCategory", category));

			a = cr.list();

		} catch (Exception jj) {
			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();

			jj.printStackTrace();
		} finally {

			if (!t.wasCommitted()) {
				t.commit();
			}
			session.close();
			factory.close();
		}

		return a;
	}

	public void persist(T entity) throws Exception {
		// TODO Auto-generated method stub

	}

	public T findById(K id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getEntitiesWithPagination() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public T getModel(K modelId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public T insert(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public T update(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> getList(int startRecord, int numberOfRecord) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(T model) throws Exception {
		// TODO Auto-generated method stub

	}

	public int getNumberOfRecords(T model) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public T saveOrUpdate(T model) throws Exception {
		try {
			configConnection();

			session.saveOrUpdate(model);
			session.flush();
			return model;
		} catch (HibernateException hibex) {
			LOGGER.info(hibex.getMessage() + hibex.getCause());

		} catch (Exception ex) {
			LOGGER.info("System Error has occured." + ex);

		}
		return model;
	}

	public T saveOnFlush(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public T merge(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public T mergeT(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public T refresh(T model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
