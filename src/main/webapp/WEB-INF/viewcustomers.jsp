<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>

    <jsp:attribute name="header">

    </jsp:attribute>

    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>

        <c:if test="${sessionScope.user.role == 'employee'}">

            <table class="table table-success table-striped">

                <thead>
                <tr>
                    <th scope="col">User ID</th>
                    <th scope="col">User email</th>
                    <th scope="col">Name</th>
                    <th scope="col">Number</th>
                    <th scope="col">Address</th>
                    <th scope="col">role</th>

                </tr>
                </thead>
                <c:forEach var="user" items="${sessionScope.userList_admin}" varStatus="status">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.email}</td>
                        <td>${user.name}</td>
                        <td>${user.number}</td>
                        <td>${user.adress}</td>
                        <td>${user.role}</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/fc/user_orderhistory" method="post">
                                <button type="submit" class=" btn btn-danger" name="user_orderhistory"
                                        value="${user.id}">View users orderhistory
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>


        </c:if>

        <c:if test="${requestScope.error != null}">
            <p style="color: red">${requestScope.error}</p>
        </c:if>

    </jsp:body>
</t:genericpage>