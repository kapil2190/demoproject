package com.sunlife.us.service.util;

import com.sunlife.us.backing.UserInfo;
import com.sunlife.us.entities.BaseEntity;
import com.sunlife.us.exception.CMSRunTimeException;
import com.sunlife.us.util.Pair;
import sunlife.us.dc.utility.log.LogMethodCalls;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named
public class EntityUtils {

    @Inject
    private UserInfo userInfo;

    @LogMethodCalls
    public static Timestamp getTimeStamp(String date) {
        if(date == null || date.isEmpty()){
            return null;
        }
        SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("dd MMM yyyy");
        Date lFromDate1 = null;
        try {
            lFromDate1 = datetimeFormatter1.parse(date);
        } catch (ParseException e) {
            datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            try {
                lFromDate1 = datetimeFormatter1.parse(date);
            } catch (ParseException ex) {
                throw new CMSRunTimeException("Parsing error", e);
            }
        }
        Timestamp fromTS1 = new Timestamp(lFromDate1.getTime());
        return fromTS1;
    }

    /**
     * This will return pair  where key  = zipcd
     * and value  = zip4Cd
     *
     * @param zip
     * @return
     */
    @LogMethodCalls
    public static Pair<String, String> zipHandler(String zip) {
        Pair<String, String> pair = new Pair<>();
        if (zip.length() > 6) {
            pair.setKey(zip.substring(0, 6));
            if (zip.length() == 10) {
                pair.setVal(zip.substring(6));
            }
        } else {
            if (zip == null) {
                pair.setKey("");
            } else {
                pair.setKey(zip);
            }
            pair.setVal("");
        }
        return pair;
    }

    public <T extends BaseEntity> void setDefaultEntityFields(T object) {

        object.setEmpleId(userInfo.getEmpleId().trim());
        object.setLstUpdUserId(userInfo.getEmpleSlAcf2Id().trim());
        object.setLstUpdDtm(currentTimeStamp());

    }

    public Timestamp getCurrentTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public String currentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public String getTimeStampAsString(Timestamp timestamp) {
        Date date = new Date();
        date.setTime(timestamp.getTime());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
    }
}
