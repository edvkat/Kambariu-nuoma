<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>--%>
<%@include file="tagHeader.jsp"%>
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
            <%out.println(resources.getString("msg.rentRoom"));%>
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
                    <%--<fmt:setLocale value="eu_EU"/>--%>
                    <%--<fmt:formatNumber type="currency">--%>
                        <%--<c:out value="${room.priceForDay}"/>--%>
                    <%--</fmt:formatNumber>--%>
                    <fmt:formatNumber type="number" pattern="####.00" value="${room.priceForDay}" />
                    <%out.println(resources.getString("msg.euro"));%>
                </td>
            </tr>

            <form:form action="/createContract/id=${room.id}" method="POST" commandName="rentContract">
            <tr>
                <td class="form-label">
                    <%out.println(resources.getString("msg.specifyRentStartDate"));%>
                </td>
                <td class="form-entry">
                    <fmt:formatDate var="datosFormatas" value="${rentContract.fromDate}" pattern="yyyy-MM-dd"/>
                    <form:input type="text" id="datepicker" path="fromDate" value="${datosFormatas}"
                                class="form-entry"/>
                </td>
            </tr>
            <tr>
                <td class="form-label">
                    <%out.println(resources.getString("msg.specifyRentEndDate"));%>
                </td>
                <td class="form-entry">
                    <fmt:formatDate var="datosFormatas" value="${rentContract.toDate}" pattern="yyyy-MM-dd"/>
                    <form:input type="text" id="datepicker2" path="toDate" value="${datosFormatas}" class="form-entry"/>
                </td>
            </tr>
        </table>

        <form:hidden path="roomID"/>
        <form:hidden path="signingTime"/>
        <form:hidden path="username"/>
        <h4 class="failed">
            <c:if test="${rentFailure != null}">
                <c:choose>
                    <c:when test="${rentFailure == -1}">
                        <%out.println(resources.getString("msg.startDateNotSpecified"));%>
                    </c:when>
                    <c:when test="${rentFailure == -2}">
                        <%out.println(resources.getString("msg.endDateNotSpecified"));%>
                    </c:when>
                    <c:when test="${rentFailure == -3}">
                        <%out.println(resources.getString("msg.endDateIsEarlierThanFromDate"));%>
                    </c:when>
                    <c:otherwise>
                        <%out.println(resources.getString("msg.unexpectedError"));%>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <form:errors path="fromDate" />
            <form:errors path="toDate" />
        </h4>
        <div class="form-bottom">
            <input type="submit" value="<%out.println(resources.getString("msg.createRentContract"));%>"/>
        </div>
        </form:form>
    </div>

</td>
</table>

<%@include file="footer.jsp"%>

</body>
</html>