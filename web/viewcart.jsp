<%-- 
    Document   : viewcart
    Created on : Oct 27, 2020, 9:49:49 AM
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
        <h1>ABOUT-TO-BOOK ROOMS</h1>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.items}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Image</th>
                        <th>Hotel Name</th>
                        <th>Room Type</th>
                        <th>Price</th>
                        <th>Total</th>
                        <th>Amount</th>
                        <th>Remove item</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="g" items="${cart.items}" varStatus="counter">
                        <tr>
                            <td style="text-align:center">
                                ${counter.count}
                            </td>
                            <td style="text-align:center">
                                <img src="${g.key.roomImg}" width="200px" height="170px"/>
                            </td>
                            <td style="text-align:center">
                                ${g.key.hotelId.hotelName}
                            </td>
                            <td style="text-align:center">
                                ${g.key.roomTypeId.typeName}
                            </td>
                            <td style="text-align:center">
                                ${g.key.price}
                            </td>
                            <td style="text-align:center">
                                ${g.key.price * g.value}
                            </td>
                            <td style="text-align:center">                                
                                <form action="UpdateCart" method="POST">
                                    <input type="hidden" name="txtIdUpdate" value="${g.key.roomId}"/>
                                    <input type="hidden" name="txtRoomQuantity" value="${g.key.amount}"/>
                                    <input type="number" name="txtCartQuantity" value="${g.value}"/> 
                                    <button type="submit" name="action">Update</button>
                                </form>
                                <c:if test="${g.key.roomId eq requestScope.ID}">      
                                    <font color="tomato">${requestScope.INVALID}</font>
                                </c:if>
                                <c:if test="${g.key.roomId eq requestScope.ID1}">
                                    <font color=#3CD57A>${requestScope.SUCCESS}</font>
                                </c:if>
                            </td>
                            <td style="text-align:center">
                                <form id="del" action="RemoveCart" method="POST">
                                    <input type="hidden" name="txtIdRemove" value="${g.key.roomId}"/>
                                    <button type="button" name="RemoveCart" onclick="checkDel()">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="8">
                            &thinsp; Total money have to pay: ${sessionScope.TOTAL}
                        </td>
                    </tr>
                <form action="Search" method="POST">
                    <input type="submit" value="Continue Shopping"/>
                </form> 
            </tbody>
        </table>
        <form action="AddOrder" method="POST">
            <input type="date" name="txtCheckInDate" required=""/>
            <input type="date" name="txtCheckOutDate" required=""/>
            <font color="tomato">${requestScope.WRONG}</font> 
            <input type="text" name="txtCoupon" placeholder="Discound coupon (if any)..."/>
            <button type="submit">Confirm</button>
        </form>
    </c:if>
    <c:if test="${empty cart.items}">
        You have not added anything to your cart
        <form action="Search" method="POST">
            <input type="submit" value="Back To Home"/>
        </form> 
    </c:if>
    <script>
        function checkDel() {
            let confirmed = confirm("Do you really want to remove this room from your cart?");
            if (confirmed) {
                document.getElementById("del").submit();
            }
        }
    </script>
</body>
</html>
