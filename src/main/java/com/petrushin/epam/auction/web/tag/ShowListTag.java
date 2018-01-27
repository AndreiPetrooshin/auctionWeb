package com.petrushin.epam.auction.web.tag;

import com.petrushin.epam.auction.domain.FlowerLot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Custom Tag for Iteration FlowerLot list
 *
 * @author Andrei Petrushin
 * @version 1.0.0
 */
public class ShowListTag extends TagSupport {

    private static final Logger LOGGER = LogManager.getLogger(ShowListTag.class);

    private List<FlowerLot> lotList;

    public void setlotList(List<FlowerLot> lots) {
        lotList = lots;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        if (lotList == null) {
            return SKIP_BODY;
        }
        JspWriter writer = pageContext.getOut();
        for (FlowerLot lot : lotList) {
            try {
                writer.write("<a href=\"" + request.getContextPath() + "/controller?command=showLot&id=" + lot.getId() + "\">\n" +
                        "            <section class=\"lot row main-lots\">\n" +
                        "                <div class=\"col-2\">\n" +
                        "                        " + lot.getName() + "\n" +
                        "                </div>\n" +
                        "                <div class=\"col-8\">\n" +
                        "                        " + lot.getDescription() + "\n" +
                        "                </div>\n" +
                        "                <div class=\"col-2\">\n" +
                        "                    PRICE: " + lot.getStartPrice() + "\n" +
                        "                </div>\n" +
                        "            </section>\n" +
                        "        </a>");
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return SKIP_BODY;
    }
}
