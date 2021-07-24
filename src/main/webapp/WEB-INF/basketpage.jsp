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


            <c:if test="${sessionScope.Selected_Carport.id != null }">
                <img class="img-fluid w-25"
                     src="<c:url value='/IMAGE/ProductPage${sessionScope.Selected_Carport.id}.png'/>"
                     alt="carport" type="submit"/><br>

            </c:if>
            <table class="table table-success table-striped">
                <thead>
                <tr>
                    <th scope="col">CarportID</th>
                    <th scope="col">Price(kr)</th>
                    <th scope="col">Width(mm)</th>
                    <th scope="col">Length(mm)</th>
                    <th scope="col">ShedLength(mm)</th>
                    <th scope="col">ShedWidth(mm)</th>
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
                            <form action="${pageContext.request.contextPath}/fc/removefrombasket" method="post">
                                <button type="submit" class=" btn btn-danger" name="remove"
                                        value="${sessionScope.Selected_Carport.id}">Remove
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </table>
            <c:if test="${sessionScope.Selected_Carport != null}">

                <p>tryk her for at gå acceptere købet</p>
                <form action="${pageContext.request.contextPath}/fc/createorder_standard" method="post">
                    <button type="submit" class=" btn btn-danger" name="accept"
                            value="${sessionScope.Selected_Carport.id}">Accept
                    </button>
                </form>


            </c:if>

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