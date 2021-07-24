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
        <p>Her kan du se information om forespørgslen:</p>
        <c:if test="${sessionScope.user.role == 'customer'}">


            Kunde: ${requestScope.request_object.user.name}<br>
            Kunde email: ${requestScope.request_object.user.email}<br>
            Mål: ${requestScope.request_object.carport.width}mm x ${requestScope.request_object.carport.length}mm<br>
            Skur mål: ${requestScope.request_object.carport.shed_width}mm x ${requestScope.request_object.carport.shed_length}mm<br>
            Pris: ${requestScope.request_object.carport.price}kr<br>


            <p>TEGNING: <a href="${pageContext.request.contextPath}/fc/ViewSketch">Tegning</a><br>

            <p>Her kan du se en liste med materialer:</p>
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

                <c:forEach var="item" items="${requestScope.itemlist}" varStatus="status">
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
            <td>
                <p> Du kan acceptere dine request gennem den email, du blev tilsendt da du sendte din request til os.
                    Følg ansvisningerne i emailen eller tryk her for at acceptere dette request.
                </p>
                <form action="${pageContext.request.contextPath}/fc/createorder" method="post">
                    <button type="submit" class=" btn btn-danger" name="accept"
                            value="${requestScope.request_object.request_id}">Accepter tilbud
                    </button>
                </form>
                <p> Du kan afvise dine request gennem den email, du blev tilsendt da du sendte din request til os.
                    Følg ansvisningerne i emailen eller tryk her for at afivse denne request.
                </p>
                <form action="${pageContext.request.contextPath}/fc/removeoffer" method="post">
                    <button type="submit" class=" btn btn-danger" name="remove"
                            value="${requestScope.request_object.request_id}">Fjern tilbud
                    </button>
                </form>
            </td>


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