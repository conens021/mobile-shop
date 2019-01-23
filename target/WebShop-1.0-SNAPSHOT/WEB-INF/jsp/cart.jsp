<%@include file="begin.jsp" %>
<div class="productForm">
    <div class="nestedProductForm">
    

        <div style="border-bottom:solid 2px red">
        <c:forEach items = "${cartProducts}" var = "p">
            <div class="cartItems">
                <div class="cartProduct">
                    <img src="resources/picts/${p.getProductPicture()}" alt="${p.getProductName()}" />
                    <h4>${p.getProductName()}</h4>
                    <h4>$${p.getProductPrice()}</h4>
                    <h4>${p.getQuant()}</h4>
                    <h4>$${p.getSuperDiscount()}</h4>
                    <h4>$${p.totalProductPrice()}</h4>
                </div>
                    <div class="removeFCart"><a href="removeFromCart?prodId=${p.getProductId()}"><h4>X</h4></a></div>
                    
            </div>
            </c:forEach>
              
    </div>
         <div class="checkout">
       
             <form method="get" action="customer">
           <input type="submit" id="buyButton" value="Checkout"/>
       </form>
       <h4 style="margin-top: 1em">Cart total : $${total}</h4></div>
      
</div>
</div>
<!--ENd products -->
<%@include  file="end.jsp" %>