package computerStore.Order;

import computerStore.db.ItemModel;

class OrderItem extends ItemModel {

    private int orderAmount;
    private int totalPrice;

    OrderItem(int catalogNumber, int price, int amount, String name, int orderAmount) {
        super(catalogNumber, price, amount, name);
        this.orderAmount = orderAmount;
        this.totalPrice = orderAmount * price;
    }

    int getOrderAmount() {
        return orderAmount;
    }

    int getTotalPrice() {
        return totalPrice;
    }
}
