package com.interview.project.rules.impl;

import com.interview.project.beans.Dimension;
import com.interview.project.beans.Item;
import com.interview.project.beans.Order;
import com.interview.project.beans.TypeOfOrder;
import com.interview.project.inventory.Inventory;
import com.interview.project.rules.RulesEngine;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Ideally rules engine product can be used, but because of time constraint going through this approach.
 */
public class RulesEngineImpl implements RulesEngine {

	private static final String COUNTRY = "US";
	private static final int PACKAGE_LENGTH = 6;
	private static final int PACKAGE_BREADTH = 6;
	private static final int PACKAGE_HEIGHT = 4;
	private static final int PACKAGE_VOLUME = 144;
	private static  int totalBoxesReq =0;
	private static final String OUT_OF_STOCK = "Cannot ship, as Inventory shortage ";
	private static final String OVERSIZED_PACKAGE = "Dimensions have exceeded the package box Dimensions";
	   private static final String CAN_BE_SHIPPED = "Can be shipped automatically.";
	private final Inventory inventory;

	public RulesEngineImpl(Inventory inventory) {
		this.inventory = inventory;
	}

	/**
	 * Check if the order can be shipped.
	 * If shipment is not present in inventory then status is updated to OUT_OF_STOCK
	 * If shipment dimensions have exceeded the dimensions of the package then DIMENSIONS EXCEEDED
	 * If all the rules are satisfied then status is updated to CAN BE SHIPPED 
	 * @param order details.
	 * @return
	 */
	public boolean canShip(final Order order) {
		boolean ship = true;
		for (Item item : order.getItems()) {
			Set<Item> visited = new HashSet<>();

			if(!checkDependentShipments(item, visited)){
				System.out.println("The product " +item.getProductId() +"  cannot be shipped as item or its dependent items are not available in Inventory ");
				order.setStatus(OUT_OF_STOCK);
				ship = false;
				break;
			}
			else {
				if (!canFit(item.getDimension())) {
					System.out.println("The product " + item.getProductId() + " is oversized and cannot be shipped");
					order.setStatus(OVERSIZED_PACKAGE);
				} else {
					System.out.println("The product " + item.getProductId() + " can be shipped");
					order.setStatus(CAN_BE_SHIPPED);
				}
			}
		}
		return ship;
	}

	/**
	 * Validates if the shipment belongs to outside of the USA
	 * If USA then type of order is updated to AUTOMATIC
	 * else type of order is updated to MANUAL.
	 *
	 * @param order details.
	 * @return status if the item can be shipped outside.
	 */
	public TypeOfOrder canShipOutside(final Order order) {
		return COUNTRY.equalsIgnoreCase(order.getShipTo().getCountry()) ? TypeOfOrder.AUTOMATIC : TypeOfOrder.MANUAL;
	}

	/**
	 * Get the number of packages.
	 *
	 * @param order
	 * @return number of packages.
	 */
	@Override
	public int getNumberOfPackages(final Order order) {
		final List<Dimension> dimensions = getDimensions(order.getItems());
		Collections.sort(dimensions);
		int numberOfBoxes = 1;
		Dimension check = new Dimension(0, 0, 0);
		for (final Dimension dimension : dimensions) {
			check.addDimension(dimension);
			if (!canFit(check)) {
				numberOfBoxes += 1;
				//reset calculating
				check = new Dimension(0, 0, 0);
			}
		}
		return numberOfBoxes;
	}

	private boolean canFit(final Dimension dimension) {
		return dimension.getLength() <= PACKAGE_LENGTH && dimension.getBreadth() <= PACKAGE_BREADTH
				&& dimension.getHeight() <= PACKAGE_HEIGHT;
	}

	private List<Dimension> getDimensions(final List<Item> items) {
		final List<Dimension> list = new ArrayList<>();
		for (final Item item : items) {
			list.add(item.getDimension());
		}
		return list;
	}

	/**
	 * Check recursively if all the dependents items are also present in the inventory.
	 * @param item
	 * @param visited
	 * @return
	 */
	private boolean checkDependentShipments(final Item item, final Set<Item> visited) {
		if (item == null || visited.contains(item)) {
			return true;
		}

		if (!inventory.hasInventory(item)) {
			return false;
		}
		visited.add(item);

		return checkDependentShipments(item.getDependsOn(), visited);
	}

	/**
	 * Validates if the shipment can fit inside a box.
	 *
	 * @param visited details.
	 * @param placedItem 
	 * @param placedItem 
	 * @return status if the item can fit inside a box.
	 */

	private boolean canFitInBox(final Set<Item> visited, final Item placedItem,final Order order) {
		int volume =0 ;
		double lengthItems =0;
		double breathItems =0;
		double heightItems =0;
		for(Item item : visited) {
			System.out.println(item.getQuantity() + " product " + item.getProductId());
			if(item.getDimension().getLength() <= PACKAGE_LENGTH && item.getDimension().getBreadth() <= PACKAGE_BREADTH 
					&& item.getDimension().getHeight() <= PACKAGE_HEIGHT) {
				 lengthItems += item.getDimension().getLength();
				breathItems +=item.getDimension().getBreadth();
				heightItems += item.getDimension().getHeight();
				volume+=item.calculateVolume();
			}
			else {
				return false;
			}
			volume = volume * item.getQuantity();
			lengthItems = lengthItems*item.getQuantity();
			breathItems = breathItems*item.getQuantity();
			heightItems = heightItems*item.getQuantity();
		}
	
        int totalBoxesReq = (int)((lengthItems/PACKAGE_LENGTH )* (breathItems/PACKAGE_BREADTH)*(heightItems/PACKAGE_HEIGHT));
        if(lengthItems % PACKAGE_LENGTH != 0 || breathItems % PACKAGE_BREADTH  !=0 || heightItems % PACKAGE_HEIGHT !=0)
        {
            totalBoxesReq = totalBoxesReq + 1;
        }
        if(totalBoxesReq > visited.size()) {
        	 totalBoxesReq = (volume/PACKAGE_VOLUME);
            if(volume % PACKAGE_VOLUME != 0)
            {
                totalBoxesReq = totalBoxesReq + 1;
            }
        }
		order.setPackageBoxes(totalBoxesReq);
		
		return true;
		
	}
}
