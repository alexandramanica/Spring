package com.example.OrderProject_MVC.model.BuilderModel;

//CONSTRUCTOR PE PRIVATE OBLIGATORIU
public class OrderSummary {

    private final Long orderId;
    private final String clientEmail;
    private final String status;
    private final Double price;
    private final String displayMessage;

    //CONSTRUCTOR PE PRIVATE OBLIGATORIU
    private OrderSummary(Builder builder) {
        this.orderId = builder.orderId;
        this.clientEmail = builder.clientEmail;
        this.status = builder.status;
        this.price = builder.price;
        this.displayMessage = builder.displayMessage;
    }

    // doar getters
    public Long getOrderId() { return orderId; }
    public String getClientEmail() { return clientEmail; }
    public String getStatus() { return status; }
    public Double getPrice() { return price; }
    public String getDisplayMessage() { return displayMessage; }

    // 🔹 BUILDER
    public static class Builder {
        private Long orderId;
        private String clientEmail;
        private String status;
        private Double price;
        private String displayMessage;

        public Builder orderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder clientEmail(String clientEmail) {
            this.clientEmail = clientEmail;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder price(Double price) {
            this.price = price;
            return this;
        }

        public Builder displayMessage(String displayMessage) {
            this.displayMessage = displayMessage;
            return this;
        }

        public OrderSummary build() {
            if (orderId == null) {
                throw new IllegalStateException("orderId is mandatory");
            }
            if (status == null) {
                status = "UNKNOWN";
            }
            if (displayMessage == null) {
                displayMessage = "Order summary generated.";
            }
            return new OrderSummary(this);
        }
    }

    //Exemplu folosire builder - folosire pentru obiecte cu campuri optionale
//    OrderResponseDTO order = orderService.getOrderDetails(id);
//
//    OrderSummary summary = new OrderSummary.Builder()
//            .orderId(order.getId())
//            .clientEmail(order.getEmail())
//            .status(order.getOrderStatus().name())
//            .price(order.getPrice())
//            .displayMessage("Comanda este în starea: " + order.getOrderStatus())
//            .build();

}

