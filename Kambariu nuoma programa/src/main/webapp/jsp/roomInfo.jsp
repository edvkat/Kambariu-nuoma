<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>

<head>
    <%@include file="stylesheetsHeader.jsp"%>
    <title>
        <%out.println(String.format("%s | %s", resources.getString("msg.userProfile"), resources.getString("msg.appName")));%>
    </title>
</head>

<body>
<%@include file="header.jsp"%>
<%@include file="userMenuHeader.jsp"%>
<td class="user-content-container">

    <div class="form-container">
        <h3 class="form-header">
            <%out.println(resources.getString("msg.roomInfo"));%>
        </h3>
        <table class="form-container-with-margin">
            <tr>
                <td class="form-label">
                    <%out.println(resources.getString("msg.accomodationLocation"));%>
                </td>
                <td class="form-entry-bold">
                    <c:out value="${room.location}"/>
                </td>
            </tr>
            <tr>
                <td class="form-label">
                    <%out.println(resources.getString("msg.accomodationNumber"));%>
                </td>
                <td class="form-entry-bold">
                    <c:out value="${room.number}"/>
                </td>
            </tr>
            <tr>
                <td class="form-label">
                    <%out.println(resources.getString("msg.accomodationType"));%>
                </td>
                <td class="form-entry-bold">
                    <c:choose>
                        <c:when test="${room.type == 'ROOM'}">
                            <%out.println(resources.getString("msg.typeRoom"));%>
                        </c:when>
                        <c:when test="${room.type == 'CONFERENCE_HALL'}">
                            <%out.println(resources.getString("msg.typeConferenceHall"));%>
                        </c:when>
                        <c:otherwise>
                            <%out.println(resources.getString("msg.typeConcertHall"));%>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td class="form-label">
                    <%out.println(resources.getString("msg.accomodationMaxNumberOfPeople"));%>
                </td>
                <td class="form-entry-bold">
                    <c:out value="${room.maxNumberOfPeople}"/>
                </td>
            </tr>
            <tr>
                <td class="form-label">
                    <%out.println(resources.getString("msg.accomodationPrice"));%>
                </td>
                <td class="form-entry-bold">
                    <%--<fmt:setLocale value="en_US"/>--%>
                    <%--<fmt:formatNumber type="currency" value="${room.priceForDay}">--%>
                        <%--<c:out value="${room.priceForDay}"/>--%>
                    <%--</fmt:formatNumber>--%>
                    <fmt:formatNumber type="number" pattern="####.00" value="${room.priceForDay}" />
                    <%out.println(resources.getString("msg.euro"));%>
                </td>
            </tr>
            <tr>
                <td class="form-label" valign="top">
                    <%--<%out.println(resources.getString("msg.accomodationAttributes"));%>--%>
                    Kiti atributai
                </td>
                <td>
                    <ul class="attribute-container">
                        <fmt:setLocale value="lt_LT"/>
                        <fmt:setBundle basename="messages" var="msg" />
                        <c:forEach items="${room.roomAttributes}" var="attribute">
                            <li>
                                <fmt:message key="msg.roomAttribute${attribute.id}" bundle="${msg}"/>
                            </li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </table>
        <c:if test="${room.available}">
            <div class="form-bottom">
                <a class="submit" href="/createContract/id=${room.id}">
                    <% out.println(resources.getString("msg.submitRent")); %>
                </a>
            </div>
        </c:if>
    </div>

</td>
</table>

<%@include file="footer.jsp"%>

</body>
</html>