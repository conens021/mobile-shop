<%@include file="begin.jsp" %>
<!--Start main-content -->
<div class="main-content">

    <!--Start products -->
    <div class="freshHeader"><h2>Models</h2></div>

    <c:if test = "${prodByMan.isEmpty() || prodByMan == null}">
        <div class="products">
            <h4 id='noContentHeader' style="margin-left: 25em;margin-top: 1em">Sorry,no models from this manufacturer...</h4>
        </div>
    </c:if>
    <div class="products">

      

     

                <c:forEach items = "${prodByMan}" var = "f">
                    <div class="product">
                        <div class="productImg"><img src="resources/picts/${f.getProductPicture()}" ></div>
                        <div class="productContent">
                            <h3>${f.getProductName()}</h3><span style="margin-top:0;">Price : $${f.getProductPrice()}</span><br/>

                            <form method="post" action="manuf">
                                <input type="hidden" name="productID" value="${f.getProductId()}"/>
                                <input id="buyButton" type="submit" value="BUY NOW">
                            </form>
                        </div>
                    </div>

                </c:forEach>
      

        <!--ENd products -->
    </div>

</div>
<!--End main-content -->

<%@include  file="end.jsp" %>