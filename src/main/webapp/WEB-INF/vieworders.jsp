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
                    <th scope="col">Order ID</th>
                    <th scope="col">User email</th>
                    <th scope="col">Price</th>
                    <th scope="col">Time</th>
                    <th scope="col">Request ID</th>
                    <th scope="col">Status</th>
                </tr>
                </thead>
                <c:forEach var="order" items="${sessionScope.orderList_admin}" varStatus="status">
                    <tr>
                        <td>${order.id}</td>
                        <td>${order.user_name}</td>
                        <td>${order.price}</td>
                        <td>${order.time}</td>
                        <td>${order.request_id}</td>
                        <td>${order.status}</td>

                        <td>
                            <form action="${pageContext.request.contextPath}/fc/viewOrderSpecs" method="post">
                                <button type="submit" class=" btn btn-danger" name="viewOrderSpecs"
                                        value="${order.id}">View Carport specs
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