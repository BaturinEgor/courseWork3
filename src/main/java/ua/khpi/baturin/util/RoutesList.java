package ua.khpi.baturin.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.stereotype.Service;

import ua.khpi.baturin.entity.Driving;
import ua.khpi.baturin.entity.TicketToBuyForm;

@Service
public class RoutesList implements Tag {

    private List<TicketToBuyForm> ticketToBuyForm;
    private PageContext pageContext;
    private Tag parent;

    public RoutesList() {

    }

    public List<TicketToBuyForm> getTicketToBuyForm() {
        return ticketToBuyForm;
    }

    public void setTicketToBuyForm(List<TicketToBuyForm> ticketToBuyForm) {
        this.ticketToBuyForm = ticketToBuyForm;
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public void setRouteForm(List<TicketToBuyForm> routeForm) {
        this.ticketToBuyForm = routeForm;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag tag) {
        this.parent = tag;
    }

    @Override
    public Tag getParent() {
        return this.parent;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        if (ticketToBuyForm != null) {
            StringBuilder stringBuilder = new StringBuilder("<table>");
            stringBuilder.append(
                    "<tr><th>Номер мршрута</th><th>Номер автобуса</th><th>Маршрут</th><th>Свободных мест</th><th>Действия</th></tr>");
            for (TicketToBuyForm rout : ticketToBuyForm) {
                stringBuilder.append("<tr>").append("<td>");
                stringBuilder.append(rout.getRoute().getRouteNumber());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append(rout.getRoute().getBus().getBusNumber());
                stringBuilder.append("</td>").append("<td>");
                for (Driving driving : rout.getDrivings()) {
                    stringBuilder.append(
                            driving.getDepartureStation().getTitle() + " - " + driving.getArrivalStation().getTitle()
                                    + driving.getDepartureTime() + " - " + driving.getArrivalTime() + "<p/>" + "<p/>");
                }
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append(rout.getAmountOfSeats());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append(
                        "<a id=\"remove\" onclick=\"bookTicket(" + rout.getRoute().getId() + ")\">купить билет</a>");
            }
            stringBuilder.append("</table>");
            try {
                out.print(stringBuilder.toString());
            } catch (IOException e) {
                throw new JspException("Error while custom tag processing", e);
            }
        }
        return Tag.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    @Override
    public void release() {
        pageContext = null;
        parent = null;
    }

}
