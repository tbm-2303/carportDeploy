<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">

        <style>

            .test1 {
                position: relative;
                display: inline-block;
            }
            .point1 {
               margin-left: 100px;
                background-color: #2143fd;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;


            }
            .dropbtn {

                background-color: #2143fd;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            /* The container <div> - needed to position the dropdown content */
            .dropdown {
                position: relative;
                display: inline-block;
            }

            /* Dropdown Content (Hidden by Default) */
            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #eae5e5;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }

            /* Links inside the dropdown */
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            /* Change color of dropdown links on hover */
            .dropdown-content a:hover {
                background-color: #f1f1f1
            }

            /* Show the dropdown menu on hover */
            .dropdown:hover .dropdown-content {
                display: block;
            }

            /* Change the background color of the dropdown button when the dropdown content is shown */
            .dropdown:hover .dropbtn {
                background-color: #2143fd;
            }


            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
                z-index: 1;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            .desc {
                padding: 15px;
                text-align: center;
            }
        </style>


    </jsp:attribute>
    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>


        <c:if test="${sessionScope.role == 'employee' }">
            <h5>Admin email: ${sessionScope.email} </h5>
            <p><a href="${pageContext.request.contextPath}/fc/viewrequestspage">process requests as admin</a>
        </c:if>

        <c:if test="${sessionScope.role == 'employee' }">
            <h5> On the OrderPage you can view all orders and you can general information about the orders in the
                system.</h5>
            <h5> ** there should be a list of usefull information. And there should be a link to a more in depth
                page </h5>
            <h5> You can access information about specific orders by clicking them </h5>
            <p><a href="${pageContext.request.contextPath}/fc/vieworderspage">Go to OrderPage </a>
        </c:if>

        <c:if test="${sessionScope.role == 'employee' }">
            <h5> On the UserPage you can view all users in the system. You access the orderhistory of the a user by
                clicking them</h5>
            <p><a href="${pageContext.request.contextPath}/fc/viewuserpage">Go to UserPage</a>
        </c:if>
        <div class="dropdown">
            <button class="dropbtn">Carporte</button>
            <div class="dropdown-content">
                <a href="${pageContext.request.contextPath}/fc/standardcarportpage">STANDARD CARPORTE
                </a>
                <a href="${pageContext.request.contextPath}/fc/sendrequestpage">CARPORT EFTER EGNE MÅL
                </a>
            </div>
        </div>

        <div class="test1">
            <form action="${pageContext.request.contextPath}/fc/orderhistory" method="post">
                <button type="submit" class="point1" name="orders" id="orders"
                        value="${sessionScope.user.id}">Ordre historik
                </button>
            </form>
        </div>
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