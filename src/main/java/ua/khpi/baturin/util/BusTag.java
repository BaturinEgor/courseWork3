package ua.khpi.baturin.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import ua.khpi.baturin.entity.Bus;

public class BusTag implements Tag {

	private List<Bus> busses;
	private PageContext pageContext;
	private Tag parent;

	public BusTag() {

	}

	public List<Bus> getBusses() {
		return busses;
	}

	public void setBusses(List<Bus> busses) {
		this.busses = busses;
	}

	public PageContext getPageContext() {
		return pageContext;
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
		if (busses != null) {
			StringBuilder stringBuilder = new StringBuilder("<table>");
			stringBuilder.append(
					"<tr><th>Номер автобуса в системе</th><th>Количество мест</th><th>Перевозчик</th><th>Действия</th></tr>");
			for (Bus carrier : busses) {
				stringBuilder.append("<tr>").append("<td>");
				stringBuilder.append(carrier.getBusNumber());
				stringBuilder.append("</td>").append("<td>");
				stringBuilder.append(carrier.getSeats());
				stringBuilder.append("</td>").append("<td>");
				if (carrier.getCarrier() != null) {
					stringBuilder.append(carrier.getCarrier().getTitle());
				}
				stringBuilder.append("</td>").append("<td>");
				stringBuilder.append("<a id=\"remove\" onclick=\"removeBus(" + carrier.getId() + ")\">удалить</a>");
				stringBuilder.append("<p/>");
				stringBuilder.append("<a id=\"edit\" onclick=\"updateBus(" + carrier.getId() + ")\">изменить</a>");
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
