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
    <c:choose>
        <c:when test="${contracts != null && contracts.size() > 0}">
            <div class="table-and-name-container">
                <div class="table-name-container">
                    <h3 class="table-title">
                        <c:out value="${tableHeader}"/>
                    </h3>
                </div>
                <div class="table-container">

                    <table class="event-table">
                        <tr class="event-table">
                            <th class="event-table"><% out.println(resources.getString("msg.contractFromDate")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.contractToDate")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.contractTotalPrice")); %></th>
                            <th class="event-table"/>
                        </tr>
                        <c:forEach items="${contracts}" var="contract">
                            <tr class="event-table">
                                <td class="event-table"><c:out value="${contract.fromDate}"/></td>
                                <td class="event-table"><c:out value="${contract.toDate}"/></td>
                                <td class="event-table">
                                    <%--<fmt:setLocale value="en_US"/>--%>
                                    <%--<fmt:formatNumber type="currency">--%>
                                        <%--<c:out value="${contract.totalPrice}"/>--%>
                                    <%--</fmt:formatNumber>--%>
                                    <fmt:formatNumber type="number" pattern="####.00" value="${contract.totalPrice}" />
                                    <%out.println(resources.getString("msg.euro"));%>
                                </td>
                                <td class="event-table-time">
                                    <a class="image" href="/roomInfo/id=${contract.roomID}">
                                        <img class="remove" src="/resources/images/info.png"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div>
                    <c:forEach begin="1" step="${numberOfContractsInPage}" end="${numberOfContracts}" var="i">
                        <a class="page-number" href="/availableRooms/${numberOfContractsInPage}/${i}">
                            <fmt:formatNumber type="number" maxFractionDigits="0">
                                <c:out value="${i / numberOfContractsInPage + 1}" />
                            </fmt:formatNumber>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="no-elements">
                <h4 class="no-elements">
                    <c:out value="${noElements}"/>
                </h4>
            </div>
        </c:otherwise>
    </c:choose>
</td>
</table>

<%@include file="footer.jsp"%>

</body>
</html>