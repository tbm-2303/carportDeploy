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

        <c:if test="${sessionScope.user.role == 'customer'}">

            <table class="table table-success table-striped">
                <thead>
                <tr>
                    <th scope="col">CarportID</th>
                    <th scope="col">Price</th>
                    <th scope="col">Width</th>
                    <th scope="col">Length</th>
                    <th scope="col">ShedLength</th>
                    <th scope="col">ShedWidth</th>
                </tr>
                </thead>
                <tr>
                    <td>${sessionScope.Selected_Carport.id}</td>
                    <td>${sessionScope.Selected_Carport.price}</td>
                    <td>${sessionScope.Selected_Carport.width}</td>
                    <td>${sessionScope.Selected_Carport.length}</td>
                    <td>${sessionScope.Selected_Carport.shed_length}</td>
                    <td>${sessionScope.Selected_Carport.shed_width}</td>

        <c:if test="${sessionScope.Selected_Carport != null}">

                    <td>
                        <form action="${pageContext.request.contextPath}/fc/createorder_standard" method="post">
                            <button type="submit" class=" btn btn-danger" name="accept"
                                    value="${sessionScope.Selected_Carport.id}">Accept
                            </button>
                        </form>
                    </td>

                    <td>
                        <form action="${pageContext.request.contextPath}/fc/removefrombasket" method="post">
                            <button type="submit" class=" btn btn-danger" name="remove"
                                    value="${sessionScope.Selected_Carport.id}">Remove
                            </button>
                        </form>
                    </td>

                </tr>

                </c:if>

            </table>

        </c:if>


        <c:if test="${not empty sessionScope.Selected_Carport_itemlist && sessionScope.Selected_Carport_itemlist != null }">
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

                    <c:forEach var="item" items="${sessionScope.Selected_Carport_itemlist}" varStatus="status">

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