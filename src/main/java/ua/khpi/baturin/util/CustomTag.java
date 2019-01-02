package ua.khpi.baturin.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

import ua.khpi.baturin.entity.Role;
import ua.khpi.baturin.entity.User;

public class CustomTag implements Tag {

    private List<User> users;
    private List<Role> roles;
    private PageContext pageContext;
    private Tag parent;

    public CustomTag() {

    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
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
        if (users != null) {
            StringBuilder stringBuilder = new StringBuilder("<table>");
            stringBuilder
                    .append("<tr><th>Логин</th><th>Имя</th><th>Фамилия</th<th>Роль</th>" + "<th>Actions</th></tr>");
            for (User user : users) {
                stringBuilder.append("<tr>").append("<td>");
                stringBuilder.append(user.getLogin());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append(user.getFirstName());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append(user.getLastName());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append(user.getRole().getName());
                stringBuilder.append("</td>").append("<td>");
                stringBuilder.append("<a id=\"remove\" onclick=\"removeUser(" + user.getId() + ")\">удалить</a>");
                stringBuilder.append("<p/>");
                stringBuilder.append("<a id=\"edt\" onclick=\"updateUser(" + user.getId() + ")\">изменить</a>");
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
