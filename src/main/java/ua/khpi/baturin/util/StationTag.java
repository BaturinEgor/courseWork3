package ua.khpi.baturin.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import ua.khpi.baturin.entity.Station;

public class StationTag implements Tag {

    private List<Station> stations;
    private PageContext pageContext;
    private Tag parent;

    public StationTag() {

    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
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
        if (stations != null) {
            StringBuilder stringBuilder = new StringBuilder("<table>");
            stringBuilder.append("<tr><th>Название станции</th><th>Действия</th></tr>");
            for (Station carrier : stations) {
                System.out.println(carrier.getTitle());
                stringBuilder.append("<tr>").append("<td>");
                stringBuilder.append(carrier.getTitle());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append("<a id=\"remove\" onclick=\"removeStation(" + carrier.getId() + ")\">удалить</a>");
                stringBuilder.append("<p/>");
                stringBuilder.append("<a id=\"edit\" onclick=\"updateStation(" + carrier.getId() + ")\">изменить</a>");
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
