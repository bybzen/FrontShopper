package controllers;

import service.AccountManagement;
import service.ShopperApplicationAPIService;

public class CheckOutController {

    AccountManagement accountManagement;
    ShopperApplicationAPIService service;

    public void setAccountManagement(AccountManagement accountManagement){
        this.accountManagement = accountManagement;
    }

    public void setService(ShopperApplicationAPIService service){
        this.service = service;
    }
}
