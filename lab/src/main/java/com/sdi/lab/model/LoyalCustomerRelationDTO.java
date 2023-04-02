package com.sdi.lab.model;

public class LoyalCustomerRelationDTO {
    private Integer customerId;
    private Integer registrationYear;
    private Integer loyaltyPoints;
    private Integer carDealershipId;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRegistrationYear() {
        return registrationYear;
    }

    public void setRegistrationYear(Integer registrationYear) {
        this.registrationYear = registrationYear;
    }

    public Integer getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public Integer getCarDealershipId() {
        return carDealershipId;
    }

    public void setCarDealershipId(Integer carDealershipId) {
        this.carDealershipId = carDealershipId;
    }
}
