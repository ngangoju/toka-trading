package toka.dao.impl;

import java.util.List;

import toka.dao.generic.AbstractDao;
import toka.dao.interfc.ICountry;
import toka.domain.Country;

public class CountryImpl extends AbstractDao<Long, Country> implements ICountry {

	public Country saveCountry(Country country) {
		return saveIntable(country);
	}

	public List<Country> getListCountrys() {
		return (List<Country>) (Object) getModelList();
	}

	public Country getCountryById(int countryId, String primaryKeyclomunName) {
		return (Country) getModelById(countryId, primaryKeyclomunName);
	}

	public Country UpdateCountry(Country country) {
		return updateIntable(country);
	}

}
