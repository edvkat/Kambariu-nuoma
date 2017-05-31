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
        <c:when test="${offeredRooms != null && offeredRooms.size() > 0}">
            <div class="table-and-name-container">
                <div class="table-name-container">
                    <h3 class="table-title">
                        <% out.println(resources.getString("msg.offeredRoomList")); %>
                    </h3>
                </div>
                <div class="table-container">

                    <table class="event-table">
                        <tr class="event-table">
                            <th class="event-table"><% out.println(resources.getString("msg.accomodationLocation")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.accomodationNumber")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.accomodationType")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.accomodationMaxNumberOfPeople")); %></th>
                            <th class="event-table"><% out.println(resources.getString("msg.accomodationPrice")); %></th>
                            <th class="event-table"/>
                        </tr>
                        <c:forEach items="${offeredRooms}" var="room">
                            <tr class="event-table">
                                <td class="event-table"><c:out value="${room.location}"/></td>
                                <td class="event-table"><c:out value="${room.number}"/></td>
                                <td class="event-table">
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
                                <td class="event-table"><c:out value="${room.maxNumberOfPeople}"/></td>
                                <td class="event-table">
                                    <%--<fmt:setLocale value="lt_Lt"/>--%>
                                    <%--<fmt:formatNumber type="currency">--%>
                                        <%--<c:out value="${room.priceForDay}"/>--%>
                                    <%--</fmt:formatNumber>--%>
                                    <fmt:formatNumber type="number" pattern="####.00" value="${room.priceForDay}" />
                                    <%out.println(resources.getString("msg.euro"));%>
                                </td>
                                <td class="event-table-time">
                                    <a class="image" href="/createContract/id=${room.id}">
                                        <img class="remove" src="/resources/images/checkmark.png"/>
                                    </a>
                                   <a class="image" href="/roomInfo/id=${room.id}">
                                        <img class="remove" src="/resources/images/info.png"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div>
                    <c:forEach begin="1" step="${numberOfRoomsInPage}" end="${numberOfRooms}" var="i">
                        <a class="page-number" href="/availableRooms/${numberOfRoomsInPage}/${i}">
                            <fmt:formatNumber type="number" maxFractionDigits="0">
                                <c:out value="${i / numberOfRoomsInPage + 1}" />
                            </fmt:formatNumber>
                        </a>
                    </c:forEach>
                </div>
                <c:if test="${rentSuccess != null}">
                    <div class="system-message">
                        <c:choose>
                            <c:when test="${rentSuccess == 1}">
                                <h4 class="success">
                                    <% out.println(resources.getString("msg.rentSuccess")); %>
                                </h4>
                            </c:when>
                            <c:otherwise>
                                <h4 class="failed">
                                    <% out.println(resources.getString("msg.unexpectedError")); %>
                                </h4>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </div>
        </c:when>
        <c:otherwise>
            <div class="no-elements">
                <h4 class="no-elements">
                    <%out.println(resources.getString("msg.noRoomsToOffer"));%>
                </h4>
            </div>
        </c:otherwise>
    </c:choose>
</td>
</table>

<%@include file="footer.jsp"%>

</body>
</html>