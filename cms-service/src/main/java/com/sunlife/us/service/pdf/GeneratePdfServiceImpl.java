/*
 * to update generated pdf  and to get required data on pdf such as Act seq num,linked doc seq num,next audit seq num,address details,address details ins dep
 * to get user info,policy details,producer details,legal entity details,source details,file num,list of policies,complaint and complaint details
 * to insert action,linked doc num
 * update audit trails
 * */

package com.sunlife.us.service.pdf;

import com.sunlife.us.constants.CMSConstants;
import com.sunlife.us.entities.*;
import com.sunlife.us.exception.CMSException;
import com.sunlife.us.exception.CMSRunTimeException;
import com.sunlife.us.mybatis.dao.impl.GeneratePdfDaoImpl;
import sunlife.us.dc.utility.log.LogMethodCalls;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@LogMethodCalls
public class GeneratePdfServiceImpl implements GeneratePdfService{

    @Inject
    private GeneratePdfDaoImpl generatePdfDao;

    @Override
    public Integer getActSeqNum(Integer cmplId) throws CMSRunTimeException{
        return generatePdfDao.getActSeqNum(cmplId);
    }

    @Override
    public Integer insertAction(ActionTaken actionTaken) throws CMSRunTimeException{
        return generatePdfDao.insertAction(actionTaken);
    }

    @Override
    public Integer getLnkDocSeqNum(Integer cmplId, Integer actSeqNum) throws CMSRunTimeException{
        return generatePdfDao.getLnkDocSeqNum(cmplId, actSeqNum);
    }

    @Override
    public Integer insertLinkedDoc(LinkedDocument linkedDocument) throws CMSRunTimeException{
        return generatePdfDao.insertLinkedDoc(linkedDocument);
    }

    @Override
    public Integer getNextAuditSeqNum(Integer cmplId) throws CMSRunTimeException{
        return generatePdfDao.getNextAuditSeqNum(cmplId);
    }

    @Override
    public Integer updateAuditTrail(AuditTrail auditTrail) throws CMSRunTimeException{
        return generatePdfDao.updateAuditTrail(auditTrail);
    }

    /*@Override
    public Map<String, List> getLetterContact(Integer cmplId) throws CMSException {
        try {

            return null;
        } catch (Exception e) {
            throw new CMSException(CMSConstants.SERVICE_ERROR, e);
        }
    }*/

    @Override
    public List<Contact> getAddressDetails(Integer ctctId, Integer cmplId) throws CMSRunTimeException {
        return generatePdfDao.getAddress(ctctId, cmplId);
    }

/*    @Override
    public List<InsuranceDepartmentContact> getAddressDetailsInsDep(Integer ctctId, String stprCd, Integer insdNum) throws CMSRunTimeException {
        return generatePdfDao.getAddressInsDep(ctctId, stprCd, insdNum);
    }*/

    @Override
    public List<Contact> getAddressDetailsInsDep(Integer ctctId, String stprCd, Integer insdNum) throws CMSRunTimeException {
        return generatePdfDao.getAddressInsDep(ctctId, stprCd, insdNum);
    }



    @Override
    public List<HrdmEmployeeV> getUserInfo(String empleId) throws CMSRunTimeException {
        return generatePdfDao.getUserInfo(empleId);
    }

    @Override
    public List<Policy> getPolicyDetails(Integer cmplId, String polId, String polNm) throws CMSRunTimeException {
        return generatePdfDao.getPolicy(cmplId, polId, polNm);
    }

    @Override
    public List<SellingAgreement> getProducerDetails(Integer prodId) throws CMSRunTimeException {
        return generatePdfDao.getProducer(prodId);
    }

    @Override
    public String getLegalEntityDetails(String legCd) throws CMSRunTimeException {
        return generatePdfDao.getLegalEntity(legCd);
    }

    @Override
    public String getSourceDetails(Integer srcId) throws CMSRunTimeException {
        return generatePdfDao.getSource(srcId);
    }

    @Override
    public String getFileNum(Integer cmplId, Integer ctctId, String stprCd, Integer insdNum) throws CMSRunTimeException {
        return generatePdfDao.getFileNum(cmplId, ctctId, stprCd, insdNum);
    }

    @Override
    public List<String> getPoliciesNumber(Integer cmplId) throws CMSRunTimeException{
        return generatePdfDao.getPoliciesNumber(cmplId);
    }

    @Override
    public List<Contact> getComplainant(Integer cmplId) throws CMSRunTimeException{
        return generatePdfDao.getComplainant(cmplId);

    }

    @Override
    public List<LegalEntity> getComplaintDetails (Integer cmplId) throws CMSRunTimeException{
        return generatePdfDao.getComplaintDetails(cmplId);
    }

}
