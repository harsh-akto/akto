package com.akto.action.billing;

import com.akto.action.UserAction;
import com.akto.listener.InitializerListener;
import com.akto.stigg.StiggReporterClient;
import com.mongodb.BasicDBObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsageAction extends UserAction {

    public static final ExecutorService ex = Executors.newFixedThreadPool(1);

    String customerId;
    String planId;
    String billingPeriod;
    String successUrl;
    String cancelUrl;

    BasicDBObject checkoutResult = new BasicDBObject();
    public String provisionSubscription() {
        String ret = StiggReporterClient.instance.provisionSubscription(customerId, planId, billingPeriod, successUrl, cancelUrl);

        checkoutResult = BasicDBObject.parse(ret);

        return SUCCESS.toUpperCase();
    }

    public String calcUsage() {
        InitializerListener.calcUsage();

        return SUCCESS.toUpperCase();
    }
    public String syncWithAkto() {
        InitializerListener.syncWithAkto();

        return SUCCESS.toUpperCase();
    }

    @Override
    public String execute() {
        throw new UnsupportedOperationException();
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public void setBillingPeriod(String billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public BasicDBObject getCheckoutResult() {
        return checkoutResult;
    }

}