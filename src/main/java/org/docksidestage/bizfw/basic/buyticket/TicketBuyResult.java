package org.docksidestage.bizfw.basic.buyticket;

public class TicketBuyResult {

    private final int handedMoney;
    private final int change;
    private final Ticket myTicket;

    public TicketBuyResult(int price, int handedMoney) {
        this.handedMoney = handedMoney;
        this.change = handedMoney - price;
        this.myTicket = new Ticket(price);
    }

    public Ticket getTicket(){
        return myTicket;
    };

    public int getChange(){
        return change;
    };
}
