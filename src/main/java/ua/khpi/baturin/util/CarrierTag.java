package ua.khpi.baturin.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import ua.khpi.baturin.entity.Carrier;

public class CarrierTag implements Tag {

    private List<Carrier> carriers;
    private PageContext pageContext;
    private Tag parent;

    public CarrierTag() {

    }

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
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
        if (carriers != null) {
            StringBuilder stringBuilder = new StringBuilder("<table>");
            stringBuilder.append("<tr><th>Название</th><th>Действия</th></tr>");
            for (Carrier carrier : carriers) {
                stringBuilder.append("<tr>").append("<td>");
                stringBuilder.append(carrier.getTitle());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append("<a id=\"remove\" onclick=\"removeCarrier(" + carrier.getId() + ")\">удалить</a>");
                stringBuilder.append("<p/>");
                stringBuilder.append("<a id=\"edt\" onclick=\"updateCarrier(" + carrier.getId() + ")\">изменить</a>");
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
