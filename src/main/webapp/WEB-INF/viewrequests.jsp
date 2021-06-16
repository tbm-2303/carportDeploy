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
            <form action="${pageContext.request.contextPath}/fc/updateRequestCommand" method="post">
            <table class="table table-success table-striped">

                <thead>
                <tr>
                    <th scope="col">status_index</th>
                    <th scope="col">RequestID</th>
                    <th scope="col">CarportID</th>
                    <th scope="col">UserID</th>
                    <th scope="col">Status</th>
                    <th scope="col">Price</th>
                    <th scope="col">Width</th>
                    <th scope="col">Length</th>
                    <th scope="col">ShedLength</th>
                    <th scope="col">ShedWidth</th>
                    <th scope="col">NewPrice</th>

                </tr>
                </thead>
                <c:forEach var="var" items="${sessionScope.requestList_admin}" varStatus="status">
                    <tr>
                        <td> ${status.index}</td>
                        <td> ${var.request_id}</td>
                        <td> ${var.carport.id}</td>
                        <td> ${var.user.id}</td>
                        <td> ${var.status}</td>
                        <td> ${var.carport.price}</td>
                        <td>${var.carport.width}</td>
                        <td>${var.carport.length}</td>
                        <td>${var.carport.shed_width}</td>
                        <td>${var.carport.shed_length}</td>
                        <td><label for="new_price"></label><input type="number" class="" id="new_price" name="new_price" min="0" step="1"
                                                              value=""></td>
                        <td>

                            <button type="submit" class=" btn btn-danger" name="update"
                                    value="${var.request_id}">Finalize changes(${var.request_id})
                            </button>

                    </td>

                    </tr>
                </c:forEach>
            </table>
            </form>
        </c:if>










        <c:if test="${requestScope.error != null}">
            <p style="color: red">${requestScope.error}</p>
        </c:if>

    </jsp:body>
</t:genericpage>