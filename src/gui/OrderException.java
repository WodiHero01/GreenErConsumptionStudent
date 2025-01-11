/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package gui;

/**
 *
 * @author matagrin
 */
public class OrderException extends Exception{

    /**
     * Creates a new instance of <code>OrderException</code> without detail
     * message.
     */
    public OrderException() {
    }

    /**
     * Constructs an instance of <code>OrderException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public OrderException(String msg) {
        super(msg);
    }
}
