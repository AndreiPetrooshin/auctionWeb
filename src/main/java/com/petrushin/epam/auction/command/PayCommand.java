package com.petrushin.epam.auction.command;

import com.petrushin.epam.auction.constants.Pages;
import com.petrushin.epam.auction.domain.Payment;
import com.petrushin.epam.auction.exceptions.EntityDAOException;
import com.petrushin.epam.auction.services.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayCommand implements Command {


    private static final Logger LOGGER = LogManager.getLogger(PayCommand.class);

    private static final String PARAM_WIN_ID = "winId";
    private static final String ATTR_ERROR = "error_payment";
    private PaymentService paymentService;

    public PayCommand(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String idValue = request.getParameter(PARAM_WIN_ID);
            Long id = null;
            if (idValue != null && !idValue.isEmpty()) {
                id = Long.valueOf(idValue);
            }

            Payment payment = paymentService.findById(id);
            if (payment != null) {
                payment.setPaid(true);
                paymentService.update(payment);
            }
        } catch (EntityDAOException e) {
            LOGGER.error("Payment error", e);
            request.setAttribute(ATTR_ERROR, true);
        }
        return Pages.PROFILE_PAGE;
    }
}
