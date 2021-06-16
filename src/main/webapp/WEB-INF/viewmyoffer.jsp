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
        Her kan du se de custom carporte du har sendt en forespørgsel på:
        <c:if test="${sessionScope.user.role == 'customer'}">

            <table class="table table-success table-striped">
                <thead>
                <tr>
                    <th scope="col">Request ID</th>
                    <th scope="col">User email</th>
                    <th scope="col">Width</th>
                    <th scope="col">Length</th>
                    <th scope="col">ShedLength</th>
                    <th scope="col">ShedWidth</th>
                    <th scope="col">Status</th>
                    <th scope="col">Price</th>
                </tr>
                </thead>
                <c:forEach var="var" items="${requestScope.requestList_customer}" varStatus="status">
                    <c:if test="${requestScope.requestList_customer.get(status.index).user.id == sessionScope.user.id}">
                        <tr>
                            <td>${var.request_id}</td>
                            <td>${var.user.email}</td>
                            <td>${var.carport.width}</td>
                            <td>${var.carport.length}</td>
                            <td>${var.carport.shed_length}</td>
                            <td>${var.carport.shed_width}</td>
                            <td>${var.status}</td>
                            <td>${var.carport.price}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/fc/createorder" method="post">
                                    <button type="submit" class=" btn btn-danger" name="accept"
                                            value="${var.request_id}">Accept offer -> (${var.request_id})
                                    </button>
                                </form>
                                <form action="${pageContext.request.contextPath}/fc/removeoffer" method="post">
                                    <button type="submit" class=" btn btn-danger" name="remove"
                                            value="${var.request_id}">Remove offer -> (${var.request_id})
                                    </button>
                                </form>
                            </td>
                        </tr>

                    </c:if>
                </c:forEach>
            </table>

        </c:if>


        <c:if test="${not empty requestScope.requestList_customer}">

            TEGNING:
            <p><a href="${pageContext.request.contextPath}/fc/ViewSketch">Tegning</a><br>
            <h4>Her kan du se en liste med materialer:</h4> <br>
            <table class="table table-success table-striped">
                <thead>
                <tr>
                    <th scope="col">Item Name</th>
                    <th scope="col">Item ID</th>
                    <th scope="col">Item Price</th>
                    <th scope="col">Item Width</th>
                    <th scope="col">Item Length</th>
                    <th scope="col">Item Info</th>
                    <th scope="col">Quantity</th>
                </tr>
                </thead>

                <c:forEach var="item" items="${requestScope.itemlist_customer}" varStatus="status">

                    <tr>
                        <td>${item.name}<br></td>
                        <td>${item.item_id}</td>
                        <td>${item.price}</td>
                        <td>${item.width}</td>
                        <td>${item.length}</td>
                        <td>${item.info}</td>
                        <td>${item.quantity}</td>
                    </tr>

                </c:forEach>

            </table>
        </c:if>





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