package TicketServices;

import Interfaces.IPrintInterface;

public class TicketPrinter  implements IPrintInterface
{
    public void print(String content){
        System.out.println(content.toString());
    }
}
