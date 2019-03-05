/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package toka.dao.impl;

import java.util.List;
import java.util.logging.Logger;
import toka.dao.generic.AbstractDao;
import toka.dao.interfc.IUploadingFiles;
import toka.domain.UploadingFiles;

/**
 *
 * @author Ngango
 */

public class UploadingFilesImpl extends AbstractDao<Long, UploadingFiles> implements IUploadingFiles {
	private static final Logger LOGGER = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

	public UploadingFiles saveUploadedFile(UploadingFiles upload) {
		return saveIntable(upload);
	}

	@SuppressWarnings("unchecked")
	public List<UploadingFiles> getListUploadedFiles() {
		return (List<UploadingFiles>) (Object) getModelList();
	}

	public UploadingFiles getUploadedFileById(int UploadId, String primaryKeyclomunName) {
		return (UploadingFiles) getModelById(UploadId, primaryKeyclomunName);
	}

	public UploadingFiles UpdateUploadedFile(UploadingFiles upload) {
		return updateIntable(upload);
	}

	public String myName() {
		// TODO Auto-generated method stub
		return "Emma";
	}

	public UploadingFiles getUploadedFilesWithQuery(String[] propertyName, Object[] value, String hqlStatement) {
		try {
			return (UploadingFiles) getModelWithMyHQL(propertyName, value, hqlStatement);
		} catch (Exception ex) {
			LOGGER.info("getUsersWithQuery  Query error ::::" + ex.getMessage());
		}
		return null;
	}

}
