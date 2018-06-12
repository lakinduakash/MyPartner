package mypartner.ultimatex.com.mypartner.model;

import java.util.ArrayList;

public class PartnerIdList {


    private ArrayList<PartnerId> partnerIds;

    public PartnerIdList(ArrayList<PartnerId> ids) {
        partnerIds = ids;
    }

    public ArrayList<PartnerId> getPartnerIds() {
        return partnerIds;
    }

    public void setPartnerIds(ArrayList<PartnerId> partnerIds) {
        this.partnerIds = partnerIds;
    }

}
