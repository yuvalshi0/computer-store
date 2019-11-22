package computerStore.Order;

import computerStore.Inventroy.InventoryModel;
import computerStore.db.ItemModel;

public class OrderModel {
    enum Status {
        PAID,
        UNPAID
    }

    enum PayingMethod {
       CREDIT,
        CASH
    }

    private String firstName;
    private String lastName;
    private double id;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
       if (this.status == Status.PAID) {
           return "Paid";
       } else return "Unpaid!";

    }

    OrderItemTable itemOrderList = new OrderItemTable();
    private double totalPrice;
    private PayingMethod payingMethod;
    private Status status;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(double id) {
        this.id = id;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPayingMethod(PayingMethod payingMethod) {
        this.payingMethod = payingMethod;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double calcTotalAmount() {
        double sum = 0;
        for(OrderItem temp : itemOrderList.getOrderList()) {
            sum = sum + temp.getTotalPrice();
        }
        return sum;
    }

    public void removeOrderItems() {
        for(OrderItem temp : itemOrderList.getOrderList()) {
            int index = InventoryModel.getInstance().searchByCatalogNumber(temp.getCatalogNumber());
            ItemModel item = InventoryModel.getInstance().getList().getItemList().get(index);
            item.setAmountInStock(item.getAmountInStock() - temp.getOrderAmount());
        }
    }
}
