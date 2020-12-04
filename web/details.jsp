<%-- 
    Document   : details
    Created on : Oct 24, 2020, 1:36:02 PM
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
        <h1>Room</h1>
        <c:if test="${requestScope.ROOM != null}">
            <c:if test="${not empty requestScope.ROOM}" var="checkList">
                <c:forEach var="dto" items="${requestScope.ROOM}" varStatus="counter">
                    <div>
                        <div>Price: ${dto.price}</div>
                        <div>Available Rooms: ${dto.amount}</div>
                        <div>Room type: ${dto.roomTypeId.typeName}</div>
                        <div>Room status: ${dto.status}</div>
                    </div>
                    <div><img src="images/${dto.roomImg}" style="width: 20%; height: 20%;">
                    </div>
                    <c:if test="${sessionScope.ROLE eq 'customer'}">
                        <form action="AddToCart" method="POST">
                            <input type="hidden" name="roomid" value="${dto.roomId}"/>
                            <button type="submit" name="action">Book this room</button>
                        </form>
                        <br/>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${!checkList}">
                No record found
            </c:if>
        </c:if>

        <div>
            <c:forEach begin="1" end="${requestScope.ROOM_COUNT}" var="page" varStatus="counter">
                <c:url value="Search" var="pageNum">
                    <c:param name="pageIDPaging1" value="${counter.count}"/>
                    <c:param name="txtSearch" value="${param.txtSearch}"/>
                </c:url>
                <a href="${pageScope.pageNum}">
                    <span class="page">${page}</span></a>
                </c:forEach>
        </div>
        <form action="Search" method="POST">
            <button type="submit">Back to Home</button>
        </form>
    </body>
</html>
