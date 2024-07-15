/*
 * Used to get Act seq num,linked doc seq num,next audit seq num,address details,address details ins dep
 * to insert action,linked doc
 * to update audit trail
 * to get user info,policy details,producers details,legal entity details,source entity,file num,policy num,
 * complaint,complaint details
 * */

package com.sunlife.us.service.pdf;

import com.sunlife.us.entities.*;
import com.sunlife.us.exception.CMSException;
import com.sunlife.us.exception.CMSRunTimeException;

import java.util.List;
import java.util.Map;

public interface GeneratePdfService {

    Integer getActSeqNum(Integer cmplId) throws CMSRunTimeException;

    Integer insertAction(ActionTaken actionTaken) throws CMSRunTimeException;

    Integer getLnkDocSeqNum(Integer cmplId, Integer actSeqNum) throws CMSRunTimeException;

    Integer insertLinkedDoc(LinkedDocument linkedDocument) throws CMSRunTimeException;



    Integer getNextAuditSeqNum(Integer cmplId) throws CMSRunTimeException;

    Integer updateAuditTrail(AuditTrail auditTrail) throws CMSRunTimeException;


    //Map<String, List> getLetterContact(Integer cmplId) throws CMSException;


    List<Contact> getAddressDetails(Integer ctctId, Integer cmplId) throws CMSRunTimeException;

    /*List<InsuranceDepartmentContact> getAddressDetailsInsDep(Integer ctctId, String stprCd, Integer insdNum) throws CMSRunTimeException;*/

    List<Contact> getAddressDetailsInsDep(Integer ctctId, String stprCd, Integer insdNum) throws CMSRunTimeException;

    List<HrdmEmployeeV> getUserInfo(String empleId) throws CMSRunTimeException;

    List<Policy> getPolicyDetails(Integer cmplId, String polId, String polNm) throws CMSRunTimeException;

    List<SellingAgreement> getProducerDetails(Integer prodId) throws CMSRunTimeException;

    String getLegalEntityDetails(String legCd) throws CMSRunTimeException;

    String getSourceDetails(Integer srcId) throws CMSRunTimeException;

    String getFileNum(Integer cmplId, Integer ctctId, String stprCd, Integer insdNum) throws CMSRunTimeException;

    List<String> getPoliciesNumber(Integer cmplId) throws CMSRunTimeException;

    List<Contact> getComplainant(Integer cmplId) throws CMSRunTimeException;

    List<LegalEntity> getComplaintDetails (Integer cmplId) throws CMSRunTimeException;

}
