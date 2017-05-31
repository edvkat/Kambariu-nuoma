<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<table class="user-menu-container">
    <td  class="user-menu-container">
        <div class="user-menu-container">
            <ul class="user-menu" >
                <li class="user-menu">
                    <a class="user-menu" href="/editProfile">
                        <%out.println(String.format("%s", resources.getString("msg.editProfile")));%>
                    </a>
                </li>
                <li class="user-menu">
                    <a class="user-menu" href="/availableRooms/8/0">
                        <%out.println(String.format("%s", resources.getString("msg.viewAllRooms")));%>
                    </a>
                </li>
                <li class="user-menu">
                    <a class="user-menu" href="/validContracts/8/0">
                        <%out.println(String.format("%s", resources.getString("msg.viewRentedRooms")));%>
                    </a>
                </li>
                <li class="user-menu">
                    <a class="user-menu" href="/voidContracts/8/0">
                        <%out.println(String.format("%s", resources.getString("msg.viewRentHistory")));%>
                    </a>
                </li>
            </ul>
        </div>
    </td>