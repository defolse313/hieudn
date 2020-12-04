<%-- 
    Document   : index
    Created on : Oct 24, 2020, 12:11:51 PM
    Author     : hp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Booking Hotel</h1>
        <c:if test="${sessionScope.ROLE == null}">
            Use our full functions? <a href="login.jsp">Login</a>
        </c:if>
        <c:if test="${sessionScope.ROLE != null}">
            Welcome, ${sessionScope.USERNAME}
            <form action="Logout" method="POST">
                Not ${sessionScope.USERNAME}? <button type="submit" value="Logingout" name="action">Log Out</button>
            </form>
        </c:if>
        <br/>
        <c:if test="${sessionScope.ROLE eq 'customer'}">
            <a href="viewcart.jsp">View Your Cart</a>
        </c:if>
        <c:if test="${sessionScope.ROLE ne 'customer'}">
            <br/>
        </c:if>
        <c:if test="${sessionScope.ROLE eq 'customer'}">
            <form action="TrackHistory" method="POST">
                <button type="submit" name="acion">Tracking History</button>
            </form>
        </c:if>
        <c:if test="${requestScope.INFO != null}">
            <c:if test="${not empty requestScope.INFO}" var="checkList">
                <c:forEach var="dto" items="${requestScope.INFO}" varStatus="counter">
                    <form action="DetailsDisplay" method="POST">
                        <input type="hidden" name="hotel" value="${dto.hotelId}"/>
                        <button type="submit" value="Details" name="action">
                            <div>
                                <div>Name: ${dto.hotelName}</div>
                                <div>Location: ${dto.hotelArea}</div>
                            </div>
                            <div><img src="image/${dto.hotelImg}" style="width: 40%; height: 40%;"></div>
                        </button>
                    </form>
                </c:forEach>
            </c:if>
            <c:if test="${!checkList}">
                No record found
            </c:if>
        </c:if>

        <div>
            <c:forEach begin="1" end="${requestScope.HOTEL_COUNT}" var="page" varStatus="counter">
                <c:url value="Search" var="pageNum">
                    <c:param name="pageIDPaging" value="${counter.count}"/>
                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                </c:url>
                <a href="${pageScope.pageNum}">
                    <span class="page">${page}</span></a>
                </c:forEach>
        </div>
    </body>
</html>
