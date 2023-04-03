package com.edhec.commonedhecapps.service;

import com.edhec.commonedhecapps.model.common.ComContact;
import com.edhec.commonedhecapps.model.common.ComCriteriaLView;

import java.util.List;

public interface CommonStudentAbroadService {
    public ComContact findContactById(String id);
    public List<ComCriteriaLView> findCriteriaByParameters(String crtCkaId, String lngId);
    public String generateId(String genkey);
}
