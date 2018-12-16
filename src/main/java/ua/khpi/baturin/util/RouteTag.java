package ua.khpi.baturin.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import org.springframework.stereotype.Service;

import ua.khpi.baturin.entity.Driving;
import ua.khpi.baturin.entity.RouteForm;

@Service
public class RouteTag implements Tag {

    private List<RouteForm> routeForm;
    private PageContext pageContext;
    private Tag parent;

    public RouteTag() {

    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public List<RouteForm> getRouteForm() {
        return routeForm;
    }

    public void setRouteForm(List<RouteForm> routeForm) {
        this.routeForm = routeForm;
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
        if (routeForm != null) {
            StringBuilder stringBuilder = new StringBuilder("<table>");
            stringBuilder
                    .append("<tr><th>Номер мршрута</th><th>Номер автобуса</th><th>Маршрут</th><th>Действия</th></tr>");
            for (RouteForm rout : routeForm) {
                stringBuilder.append("<tr>").append("<td>");
                stringBuilder.append(rout.getRout().getRouteNumber());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append(rout.getRout().getBus().getBusNumber());
                stringBuilder.append("</td>").append("<td>");
                for (Driving driving : rout.getDrivings()) {
                    stringBuilder.append(
                            driving.getDepartureStation().getTitle() + " - " + driving.getArrivalStation().getTitle()
                                    + "<p/>" + driving.getDepartureDate() + " - " + driving.getArrivalDate() + "<p/>"
                                    + driving.getDepartureTime() + " - " + driving.getArrivalTime() + "<p/>" + "<p/>");
                }
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append(
                        "<a id=\"remove\" onclick=\"deleteRoute(" + rout.getRout().getId() + ")\">удалить маршрут</a>");
                stringBuilder.append("<p/>");
                stringBuilder.append(
                        "<a id=\"edt\" onclick=\"updateRoute(" + rout.getRout().getId() + ")\">изменить маршрут</a>");
                stringBuilder.append("</td>").append("</tr>");
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
