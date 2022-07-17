package com.interview.project.beans;

import java.util.List;

/**
 * Contains the order information.
 */
public class Order {
    private String orderId;
    private List<Item> items;
    private long purchaseTime;
    private Address shipTo;
    private String status;
    private int packageBoxes;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItems() {
        return items;
    }

	 public int getPackageBoxes() {
			return packageBoxes;
		}

		public void setPackageBoxes(int packageBoxes) {
			this.packageBoxes = packageBoxes;
		}
		
    @Override
	public String toString() {
		return "Order [orderId=" + orderId + ", item=" + items + ", purchaseTime=" + purchaseTime + ", shipTo=" + shipTo
				+ ", status=" + status + ", packageBoxes=" + packageBoxes + "]";
	}

	public void setItems(List<Item> items) {
        this.items = items;
    }
    public long getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(long purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Address getShipTo() {
        return shipTo;
    }

    public void setShipTo(Address shipTo) {
        this.shipTo = shipTo;
    }
}
