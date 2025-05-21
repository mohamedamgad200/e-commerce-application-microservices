package com.ecommerce.template;

import lombok.Getter;

public enum EmailTemplates {
    PAYMENT_CONFIRMATION("payment-confirmation.html","Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation")
    ;
    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String subject, String template)
    {
        this.template = template;
        this.subject=subject;
    }

}
