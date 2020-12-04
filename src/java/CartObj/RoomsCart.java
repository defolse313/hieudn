/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CartObj;

import hieudn.blos.BookingBLO;
import hieudn.entitites.Rooms;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hp
 */
public class RoomsCart {

    private String userName;
    private Map<Rooms, Integer> items;
    private int total;
    private int count = 0;
    int quantity = 1;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<Rooms, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Rooms, Integer> items) {
        this.items = items;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

    public void addItemToCart(Rooms r) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        if (this.items.containsKey(r)) {
            quantity = this.items.get(r) + 1;
        }
        this.items.put(r, quantity);
    }
    
    public void updateCart(Rooms r, int amount) {
        if(this.items.containsKey(r)){
            quantity = 0;
            quantity = this.items.get(r) + amount;
        }
        this.items.put(r,amount);
    }

    public int getTotal() {
        count++;
        if (count != 0) {
            total = 0;
        }
        for (Map.Entry<Rooms, Integer> m : items.entrySet()) {
            total += m.getValue() * m.getKey().getPrice();
        }

        return total;
    }

    public void removeFromCart(String roomId) {
        BookingBLO blo = new BookingBLO();
        Rooms r = blo.getRoomById(roomId);
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(r)) {
            this.items.remove(r);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

}
