package computerStore.Order;

import computerStore.Inventroy.GlobalInventoryList;
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
    private long id;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        if (this.status == Status.PAID) {
            return "Paid";
        } else return "Unpaid!";

    }

    int getPayingMethod() {
        if (this.payingMethod == PayingMethod.CASH) {
            return 1;
        } else return 0;
    }

    OrderItemTable itemOrderList = new OrderItemTable();
    private long totalPrice;
    private PayingMethod payingMethod;
    private Status status;

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(long id) {
        this.id = id;
    }

    void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    void setPayingMethod(PayingMethod payingMethod) {
        this.payingMethod = payingMethod;
    }

    void setStatus(Status status) {
        this.status = status;
    }

    long calcTotalAmount() {
        long sum = 0;
        for (OrderItem temp : itemOrderList.getOrderList()) {
            sum = sum + temp.getTotalPrice();
        }
        return sum;
    }

    int calcPrice(ItemModel item, int amount) {
        return item.getPriceForUnit() * amount;
    }

    void removeOrderItems() {
        for (OrderItem temp : itemOrderList.getOrderList()) {
            int index = GlobalInventoryList.getInstance().searchByCatalogNumber(temp.getCatalogNumber());
            ItemModel item = GlobalInventoryList.getInstance().getList().getItemList().get(index);
            item.setAmountInStock(item.getAmountInStock() - temp.getOrderAmount());
        }
    }
}
