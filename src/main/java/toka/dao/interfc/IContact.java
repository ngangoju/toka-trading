package toka.dao.interfc;
/**
 * author James
 * */

import java.util.List;

import org.hibernate.HibernateException;

import toka.domain.Contact;

public interface IContact {
	public Contact saveContact(Contact contact) throws HibernateException;

	public List<Contact> getListContacts();

	public Contact getContactById(int contactId, String primaryKeyclomunName);

	public Contact UpdateContact(Contact contact);
}
