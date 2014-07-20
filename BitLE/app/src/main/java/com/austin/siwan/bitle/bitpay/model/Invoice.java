package com.austin.siwan.bitle.bitpay.model;

import org.json.JSONException;
import org.json.simple.JSONObject;

/**
 * 
 * @author Chaz Ferguson
 * @date 11.11.2013
 *
 */
public class Invoice {
	private String id;
	private String url;
	private String status;
	private String btcPrice;
	private String price;
	private String currency;
    private Long currentTime;

	public Invoice(JSONObject finalResult) throws JSONException{
		
		this.id = (String) finalResult.get("id");
		this.url = (String) finalResult.get("url");
		this.status = (String) finalResult.get("status");
		this.btcPrice = (String) finalResult.get("btcPrice");
		this.price = finalResult.get("price").toString();
		this.currency = (String) finalResult.get("currency");
        this.currentTime = (Long) finalResult.get("currentTime");
	}
	
	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getStatus() {
		return status;
	}

	public double getBtcPrice() {
		double val = Double.parseDouble(this.btcPrice);
		return val;
	}

	public double getPrice() {
		double val = Double.parseDouble(this.price);
		return val;
	}

	public String getCurrency() {
		return currency;
	}

    public Long getCurrentTime() {
        return currentTime;
    }
}
