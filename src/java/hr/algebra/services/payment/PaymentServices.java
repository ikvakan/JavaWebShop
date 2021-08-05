/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.services.payment;

import com.paypal.api.openidconnect.Session;
import hr.algebra.model.CartItem;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
import hr.algebra.model.OrderModel;
import hr.algebra.model.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IgorKvakan
 */
public class PaymentServices {

    private static final String CLIENT_ID = "AdsvxFIPMBJWl6dcB6ZBm-PruuSslEo_bKIs3-fmDmnOTeECmuAH4VGdpxatW6B_CPvOBXgIhOGetGHR";
    private static final String CLIENT_SECRET = "EPNSWbeACFTYWDGcA8POImo6FyMndrnXQOQmF7rSZwtAAFNa66Fw5tH6aAO8BzElDEo2eo0yKdtCERlr";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderModel orderModel,  String cancelUrl, String returnUrl) throws PayPalRESTException {

        // ili order i foreach!!!!!
        Payer payer = getPayerInformation(orderModel);
        RedirectUrls redirectUrls = getRedirectUrls(cancelUrl, returnUrl);

        List<Transaction> listTransaction = getTransactionInformation(orderModel);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        Payment approvedPayment = requestPayment.create(apiContext);

        return getApprovalLink(approvedPayment);

    }

    private Payer getPayerInformation(OrderModel order) {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(order.getUser().getName())
                .setLastName(order.getUser().getSurname())
                .setEmail(order.getUser().getEmail());

        return payer;
    }

    private RedirectUrls getRedirectUrls(String cancelUrl, String returnUrl) {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(returnUrl);

        return redirectUrls;
    }

    private List<Transaction> getTransactionInformation(OrderModel orderModel) {

        List<Item> items = new ArrayList<>();
        ItemList itemList = new ItemList();
        List<Transaction> transactions= new ArrayList<>();
        
        for (CartItem cartItem : orderModel.getCartItems()) {
            
            Item item=new Item();
            item.setCurrency("USD");
            item.setName(cartItem.getProduct().getName());
            item.setPrice(cartItem.getProduct().getPrice().toString());
            item.setQuantity(String.valueOf(cartItem.getQuantity()));
            items.add(item);
            itemList.setItems(items);
            
        }
        

        //tax
        //shipping
        
        
        
        Amount amount= new Amount();
        amount.setCurrency("USD");
       // amount.setDetails(details);
        amount.setTotal(orderModel.getTotalPrice().toString());
        
        Transaction transaction= new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("PayPal transaction");
        
        transaction.setItemList(itemList);
        transactions.add(transaction);
        
        
        return transactions;
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }

        return approvalLink;
    }
}
