/*
 * Used to delete the policy
 * to get issue state,product line,product desc
 * to insert man new policy
 * */

package com.sunlife.us.service.nc;

import com.sunlife.us.entities.GWPolicy;
import com.sunlife.us.entities.Policy;
import com.sunlife.us.entities.RefData;
import com.sunlife.us.entities.SellingAgreement;
import com.sunlife.us.exception.CMSException;
import com.sunlife.us.util.Pair;

import java.util.List;

public interface PolicySearchService {

    List<GWPolicy> searchGroupPolicy(String polNum, Integer cmplId) throws CMSException;

    Pair<List<Policy>, List<SellingAgreement>> searchIndivPolicy(String polNum, Integer cmplId) throws CMSException;

    Pair<List<Policy>, List<SellingAgreement>> searchAnnuityPolicy(String polNum, Integer cmplId) throws CMSException;

    /**
     * "Add Selected Row to Complaint"
     *
     * @param polNum
     * @return
     */
    List<GWPolicy> searchEvent(String polNum, String srcSystCD, Integer cmplId);

    List<GWPolicy> searchPolicy(String polNum);

    boolean deletePolicy(Policy policy);

    boolean insertManNewPolicy(Policy policy);

    List<RefData> getIssueState();

    List<RefData> getProductLine();

    List<RefData> getProductDesc();


}
