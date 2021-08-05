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

        <h6>Hej ${sessionScope.user.name} her kan du se dine information om dine ordre.</h6>

        <table class="table table-success table-striped">
            <thead>
            <tr>
                <th scope="col">Order ID</th>
                <th scope="col">User Name</th>
                <th scope="col">Price</th>
                <th scope="col">Status</th>
                <th scope="col">Time</th>
                <th scope="col">Type</th>

            </tr>
            </thead>
            <c:forEach var="var" items="${requestScope.orderlist}" varStatus="status">

                <tr>
                    <td>${var.id}</td>
                    <td>${var.user_name}</td>
                    <td>${var.price}</td>
                    <td>${var.status}</td>
                    <td>${var.time}</td>
                    <td>${var.carport.type}</td>
                    <td>
                        <form action="${pageContext.request.contextPath}/fc/orderInfoCommand" method="post">
                            <button type="submit" class=" btn btn-danger" name="info"
                                    value="${var.id}">Se mere
                            </button>
                        </form>
                    </td>
                </tr>


            </c:forEach>
        </table>


        <c:if test="${requestScope.error != null }">
            <p style="color:red">
                    ${requestScope.error}
            </p>
        </c:if>

        <c:if test="${not empty param.msg}">
            <p style="font-size: large">${param.msg}</p>
        </c:if>


    </jsp:body>
</t:genericpage>