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
public class RoutesList2 implements Tag {

	private List<TicketToBuyForm> ticketToBuyForm;
	private String departureStation;
	private String arrivalStation;
	private String date;
	private PageContext pageContext;
	private Tag parent;

	public RoutesList2() {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepartureStation() {
		return departureStation;
	}

	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}

	public String getArrivalStation() {
		return arrivalStation;
	}

	public void setArrivalStation(String arrivalStation) {
		this.arrivalStation = arrivalStation;
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
				boolean start = false;
				boolean finish = false;
				for (Driving driving : rout.getDrivings()) {
					if (driving.getDepartureStation().getTitle().equals(departureStation)) {
						start = true;
					}
					if (start && !finish) {
						stringBuilder.append(driving.getDepartureStation().getTitle() + " - "
								+ driving.getArrivalStation().getTitle() + "<p/>" + driving.getDepartureTime() + " - "
								+ driving.getArrivalTime() + "<p/>" + "________________________________" + "<p/>");
					}
					if (driving.getArrivalStation().getTitle().equals(arrivalStation)) {
						finish = true;
					}
				}
				stringBuilder.append("</td>").append("<td>");
				stringBuilder.append(rout.getAmountOfSeats());
				stringBuilder.append("</td>").append("<td>");
				System.out.println("date in rout = " + this.getDate());
				stringBuilder.append("<a id=\"remove\" onclick=\"bookTicket(" + rout.getRoute().getId() + ", " + "'"
						+ this.getDate() + "'" + ")\">купить билет</a>");
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
