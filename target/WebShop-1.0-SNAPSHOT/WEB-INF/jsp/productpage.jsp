<%@include file="begin.jsp" %>
<div class="productForm">
    <div class="nestedProductForm">
        <h3>${product.getProductName()}</h3>
        <img src="resources/picts/${product.getProductPicture()}"/>
        <p>${product.getProductDescription()}</p>

        <p>Price <b>$${product.getProductPrice()}</b></p>
        <p>Super discount on over 10 pieces <b>$${product.getSuperDiscount()}</b></p>
        
        <form method="post" action="productpage">
            Pieces <input type="number" name="quant" min="1" value="1"/>
            <input type="hidden" name="productID" value="${product.getProductId()}" />
            <input id = "buyButton" type="submit" value="Add to cart"/>
        </form>
    </div>

</div>
<!--ENd products -->
<%@include  file="end.jsp" %>